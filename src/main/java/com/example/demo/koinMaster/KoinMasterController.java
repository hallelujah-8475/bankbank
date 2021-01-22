package com.example.demo.koinMaster;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KoinMasterController {

	@Autowired
	private KoinMasterService userService;

	@Autowired
	private KoinMasterRepository koinMasterRepository;

	@RequestMapping(value = "/systemUser/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("systemUserForm") KoinMasterForm koinMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var systemUser = koinMasterRepository.findById(id).get();

			koinMasterForm.setId(id);
			koinMasterForm.setName(systemUser.getName());
			koinMasterForm.setAge(systemUser.getAge());

		}

		session.setAttribute("systemUserForm", koinMasterForm);

		return "/systemUser/detail";
	}

	@RequestMapping(value = "/systemUser/edit")
	private String edit(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("systemUserForm") KoinMasterForm koinMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var systemUser = koinMasterRepository.findById(id).get();

			koinMasterForm.setId(id);
			koinMasterForm.setName(systemUser.getName());
			koinMasterForm.setAge(systemUser.getAge());

		}

		session.setAttribute("systemUserForm", koinMasterForm);

		return "/systemUser/edit";
	}

	@RequestMapping("/systemUser/editCheck")
	public String editCheck(HttpSession session, @Validated @ModelAttribute KoinMasterForm koinMasterForm, BindingResult result) {

		session.setAttribute("systemUserForm", koinMasterForm);

		if(result.hasErrors()) {
			return "/systemUser/edit";
		}

		return "/systemUser/editCheck";
	}

	@PostMapping("/systemUser/finish")
	public String finish(HttpSession session) {
		var sessionEditForm = (KoinMasterForm) session.getAttribute("systemUserForm");

		var systemUser = new KoinMaster();

		if(sessionEditForm.getId() != null) {

			systemUser.setId(sessionEditForm.getId());

		}

		systemUser.setName(sessionEditForm.getName());
		systemUser.setAge(sessionEditForm.getAge());

		this.userService.save(systemUser);

		return "/systemUser/finish";
	}

	@RequestMapping(value = "/systemUser/list")
	public String list(Model model) {
        var list = userService.findAll();
        model.addAttribute("list", list);
        return "/systemUser/list";
	}

	@RequestMapping("/systemUser/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model) {

		this.koinMasterRepository.deleteById(id);

		return this.list(model);
	}

	@RequestMapping("/systemUser/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("systemUserForm");

		if(sessionEditForm == null) {

			return "redirect:/systemUser/edit";
		}

		model.addAttribute("systemUserForm", sessionEditForm);

		return "/systemUser/edit";
	}

	@RequestMapping("/systemUser/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("systemUserForm");

		if(sessionEditForm == null) {

			return "redirect:/systemUser/detail";
		}

		model.addAttribute("systemUserForm", sessionEditForm);

		return "/systemUser/detail";
	}

}
