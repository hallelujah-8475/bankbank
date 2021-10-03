package com.example.demo.koinMaster;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.accesslog.AccessLogService;
import com.example.demo.helper.Common;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterRepository;
import com.example.demo.shitenMaster.ShitenMasterService;
import com.example.demo.systemUser.PagenationHelper;

@Controller
public class KoinMasterController {

	@Autowired
	private KoinMasterService koinMasterService;

	@Autowired
	private ShitenMasterService shitenMasterService;
	
	@Autowired
	private ShitenMasterRepository shitenMasterRepository;

	@Autowired
	private KoinMasterRepository koinMasterRepository;

	@Autowired
	private KoinMasterValidator koinMasterValidator;

	@Autowired
	private AccessLogService accessLogService;
	
    @Autowired
    HttpSession session;
    
	@Autowired
	Common common;

	@InitBinder("koinMasterForm")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(koinMasterValidator);
	}

	@RequestMapping(value = "/koinMaster/detail")
	private String detail(@RequestParam("id") int id,
			@ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm,
			HttpSession session, Model model) {

		BeanUtils.copyProperties(koinMasterRepository.findById(id), koinMasterForm);
		var entity = (ShitenMaster) shitenMasterRepository.findById(koinMasterForm.getShitenid());
		koinMasterForm.setShitenname(entity.getShitenname());
		
		if(koinMasterForm.getFiledata() != null) {

			model.addAttribute("image", Base64.getEncoder().encodeToString(koinMasterForm.getFiledata()));
		}

		session.setAttribute("koinMasterForm", koinMasterForm);

		return "koinMaster/detail";
	}
	
	
	   public void setShitenSelectTag(Model model) {

	        var list = shitenMasterService.findAll();

	        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

	        optionMap.put(0, "選択してください");

	        for(ShitenMaster entity : list) {

	        	optionMap.put(entity.getId(), entity.getShitenname());
	        }

	        model.addAttribute("shitenList", optionMap);
	    }
	   
	@RequestMapping(value = "/koinMaster/edit")
	private String edit(Model model, @RequestParam(name = "id", required = true, defaultValue = "0") int id, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm, HttpSession session) {

		if (id != 0) {
			
			// 更新
			BeanUtils.copyProperties(koinMasterRepository.findById(id), koinMasterForm);
			var entity = (ShitenMaster) shitenMasterRepository.findById(koinMasterForm.getShitenid());
			koinMasterForm.setShitenname(entity.getShitenname());
		}

		common.setBushoSelectTag(model);
		common.setYakushokuSelectTag(model);
		model.addAttribute("shitenList", common.setShitenSelectTag());
		
		session.setAttribute("koinMasterForm", koinMasterForm);

		return "koinMaster/edit";
	}

	@RequestMapping("/koinMaster/editCheck")
	public String editCheck(@RequestParam("filedata") MultipartFile file, HttpSession session, @Validated @ModelAttribute KoinMasterForm koinMasterForm, BindingResult result, Model model) throws IOException {

		koinMasterForm.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));

		koinMasterForm.setFiledataString(Base64.getEncoder().encodeToString(file.getBytes()));

		String contenttype = "";
		
		if(!org.apache.commons.lang3.StringUtils.isBlank(koinMasterForm.getFilename())) {
			
			String kakuchoshi = koinMasterForm.getFilename().substring(koinMasterForm.getFilename().lastIndexOf("."));	
			
			if(kakuchoshi.equals(".jpg")) {
				
				contenttype = "image/jpg";
			}else if(kakuchoshi.equals(".png")) {
				
				contenttype = "image/png";
			}
		}
			
		model.addAttribute("contentype", contenttype);
		model.addAttribute("image", koinMasterForm.getFiledataString());
		
		if(koinMasterForm.getShitenid() != 0) {
			
			var entity = (ShitenMaster) shitenMasterRepository.findById(koinMasterForm.getShitenid());
			koinMasterForm.setShitenname(entity.getShitenname());
		}

		session.setAttribute("koinMasterForm", koinMasterForm);
		
		model.addAttribute("shitenList", common.setShitenSelectTag());
		common.setBushoSelectTag(model);
		common.setYakushokuSelectTag(model);
		
//		if (result.hasErrors()) {
//			return "koinMaster/edit";
//		}
		
		return "koinMaster/editCheck";
	}

	@PostMapping("/koinMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm) {

		var koinMaster = new KoinMaster();

		koinMasterForm.setFiledata(Base64.getDecoder().decode(koinMasterForm.getFiledataString()));
		
		BeanUtils.copyProperties(koinMasterForm, koinMaster);

		this.koinMasterService.save(koinMaster);

		accessLogService.save(3, "更新", "成功");
		
		return "koinMaster/finish";
	}

	@RequestMapping(value = "/koinMaster/pagenate")
	public String pagenate(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		KoinMasterListForm koinMasterListForm = (KoinMasterListForm) session.getAttribute("koinMasterListForm");

		return this.list(model, koinMasterListForm, pageable);
	}

	@RequestMapping(value = "/koinMaster/list")
	public String list(Model model, @ModelAttribute("koinMasterListForm") KoinMasterListForm koinMasterListForm,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {

		session.setAttribute("koinMasterListForm", koinMasterListForm);

		Page<KoinMaster> list = koinMasterService.findUsers(koinMasterListForm, pageable);

		model.addAttribute("list", list.getContent());
		model.addAttribute("koinMasterListForm", koinMasterListForm);
		model.addAttribute("page", PagenationHelper.createPagenation(list));

		model.addAttribute("shitenList", common.setShitenSelectTag());

		return "koinMaster/list";
	}

	@RequestMapping("/koinMaster/delete")
	public String delete(@RequestParam("id") int id, Model model, @ModelAttribute("koinMasterListForm") KoinMasterListForm koinMasterListForm,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {

		this.koinMasterRepository.deleteById(id);

		return this.list(model, koinMasterListForm, pageable);
	}

	@RequestMapping("/koinMaster/returnEdit")
	public String returnEdit(Model model, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm,
			HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if (sessionEditForm == null) {

			return "redirect:/koinMaster/edit";
		}

		model.addAttribute("koinMasterForm", sessionEditForm);

		model.addAttribute("shitenList", common.setShitenSelectTag());
		common.setBushoSelectTag(model);
		common.setYakushokuSelectTag(model);

		return "koinMaster/edit";
	}

	@RequestMapping("/koinMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if (sessionEditForm == null) {

			return "redirect:koinMaster/detail";
		}

		var entity = (ShitenMaster) shitenMasterRepository.findById(sessionEditForm.getShitenid());

		sessionEditForm.setShitenname(entity.getShitenname());

		model.addAttribute("koinMasterForm", sessionEditForm);

		return "koinMaster/detail";
	}

}
