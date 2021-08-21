package com.example.demo.koinMaster;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.accesslog.AccessLogService;
import com.example.demo.constant.BushoKbn;
import com.example.demo.constant.Yakushoku;
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

	@InitBinder("koinMasterForm")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(koinMasterValidator);
	}

    private void setSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        optionMap.put(0, "選択してください");

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getId(), entity.getShitenname());
        }

        model.addAttribute("optionMapList", optionMap);
    }

    private void setYakushokuSelectTag(Model model) {

    	model.addAttribute("yakushokuList", Yakushoku.getOptionMap());
    }

    private void setBushoSelectTag(Model model) {

		model.addAttribute("bushoList", BushoKbn.getOptionMap());
	}

	@RequestMapping(value = "/koinMaster/detail")
	private String detail(@RequestParam("id") int id,
			@ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm,
			HttpSession session, Model model) {

		BeanUtils.copyProperties(koinMasterRepository.findById(id), koinMasterForm);
		var entity = (ShitenMaster) shitenMasterRepository.findById(koinMasterForm.getShitenid());
		koinMasterForm.setShitenname(entity.getShitenname());

		session.setAttribute("koinMasterForm", koinMasterForm);

		return "koinMaster/detail";
	}

	@RequestMapping(value = "/koinMaster/edit")
	private String edit(Model model, @RequestParam(name = "id", required = true, defaultValue = "0") int id,
			@ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm, HttpSession session) {

		if (id == 0) {
			// 新規登録
		} else {
			// 更新
			BeanUtils.copyProperties(koinMasterRepository.findById(id), koinMasterForm);
			var entity = (ShitenMaster) shitenMasterRepository.findById(koinMasterForm.getShitenid());
			koinMasterForm.setShitenname(entity.getShitenname());
		}

		session.setAttribute("koinMasterForm", koinMasterForm);

		this.setSelectTag(model);
		this.setBushoSelectTag(model);
		this.setYakushokuSelectTag(model);

		return "koinMaster/edit";
	}

	@RequestMapping("/koinMaster/editCheck")
	public String editCheck(@Validated @ModelAttribute KoinMasterForm koinMasterForm, BindingResult result,
			HttpSession session) {

		var entity = (ShitenMaster) shitenMasterRepository.findById(koinMasterForm.getShitenid());
		koinMasterForm.setShitenname(entity.getShitenname());
		session.setAttribute("koinMasterForm", koinMasterForm);

		if (result.hasErrors()) {
			return "koinMaster/edit";
		}

		return "koinMaster/editCheck";
	}

	@PostMapping("/koinMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm) {

		var koinMaster = new KoinMaster();

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

		this.setSelectTag(model);

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

		this.setSelectTag(model);
		this.setBushoSelectTag(model);
		this.setYakushokuSelectTag(model);

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
