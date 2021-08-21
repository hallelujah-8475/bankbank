package com.example.demo.shitenMaster;

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
import com.example.demo.systemUser.PagenationHelper;

@Controller
public class ShitenMasterController {

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	private ShitenMasterRepository shitenMasterRepository;

	@Autowired
	private ShitenMasterValidator shitenMasterValidator;
	
	@Autowired
	private AccessLogService accessLogService;

	@InitBinder("shitenMasterForm")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(shitenMasterValidator);
	}

    @Autowired
    HttpSession session;

	@RequestMapping(value = "/shitenMaster/detail")
	private String detail(@RequestParam("id") int id, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session) {

		var shitenMaster = shitenMasterRepository.findById(id);

		BeanUtils.copyProperties(shitenMaster, shitenMasterForm);

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		return "shitenMaster/detail";
	}

	@RequestMapping(value = "/shitenMaster/edit")
	private String edit(@RequestParam(name = "id", required = true, defaultValue = "0") int id, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session) {

		if(id != 0) {
			// 更新
			var shitenMaster = shitenMasterRepository.findById(id);

			BeanUtils.copyProperties(shitenMaster, shitenMasterForm);
		}

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		return "shitenMaster/edit";
	}

	@RequestMapping("/shitenMaster/editCheck")
	public String editCheck(@Validated @ModelAttribute ShitenMasterForm shitenMasterForm, BindingResult result, HttpSession session) {

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		if(result.hasErrors()) {
			return "shitenMaster/edit";
		}

		return "shitenMaster/editCheck";
	}

	@PostMapping("/shitenMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm) {

		var shitenMaster = new ShitenMaster();

		BeanUtils.copyProperties(shitenMasterForm, shitenMaster);

		this.shitenMasterService.save(shitenMaster);
		
		accessLogService.save(2, "更新", "成功");

		return "shitenMaster/finish";
	}

	@RequestMapping(value = "/shitenMaster/pagenate")
	public String pagenate(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		ShitenMasterListForm shitenMasterListForm = (ShitenMasterListForm)session.getAttribute("shitenMasterListForm");

		return this.list(model, shitenMasterListForm, pageable);
	}

	@RequestMapping(value = "/shitenMaster/list")
	public String list(Model model, @ModelAttribute("shitenMasterListForm") ShitenMasterListForm shitenMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		session.setAttribute("shitenMasterListForm", shitenMasterListForm);

		Page<ShitenMaster> list = shitenMasterService.findUsers(shitenMasterListForm, pageable);

		model.addAttribute("list", list.getContent());
        model.addAttribute("shitenMasterListForm",shitenMasterListForm);
        model.addAttribute("page",PagenationHelper.createPagenation(list));

        return "shitenMaster/list";
	}

	@RequestMapping("/shitenMaster/delete")
	public String delete(Model model, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session, @ModelAttribute("shitenMasterListForm") ShitenMasterListForm shitenMasterListForm, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		var sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		this.shitenMasterRepository.deleteById(sessionEditForm.getId());

		return this.list(model, shitenMasterListForm, pageable);
	}

	@RequestMapping("/shitenMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		if(sessionEditForm == null) {

			return "redirect:shitenMaster/edit";
		}

		model.addAttribute("shitenMasterForm", sessionEditForm);

		return "shitenMaster/edit";
	}

	@RequestMapping("/shitenMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		if(sessionEditForm == null) {

			return "redirect:shitenMaster/detail";
		}

		model.addAttribute("shitenMasterForm", sessionEditForm);

		return "shitenMaster/detail";
	}

}
