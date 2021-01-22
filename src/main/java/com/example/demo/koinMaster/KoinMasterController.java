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

	@RequestMapping(value = "/koinMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var koinMaster = koinMasterRepository.findById(id).get();

			koinMasterForm.setId(id);
			koinMasterForm.setName(koinMaster.getName());
			koinMasterForm.setAge(koinMaster.getAge());

		}

		session.setAttribute("koinMasterForm", koinMasterForm);

		return "/koinMaster/detail";
	}

	@RequestMapping(value = "/koinMaster/edit")
	private String edit(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var koinMaster = koinMasterRepository.findById(id).get();

			koinMasterForm.setId(id);
			koinMasterForm.setName(koinMaster.getName());
			koinMasterForm.setAge(koinMaster.getAge());

		}

		session.setAttribute("koinMasterForm", koinMasterForm);

		return "/koinMaster/edit";
	}

	@RequestMapping("/koinMaster/editCheck")
	public String editCheck(HttpSession session, @Validated @ModelAttribute KoinMasterForm koinMasterForm, BindingResult result) {

		session.setAttribute("koinMasterForm", koinMasterForm);

		if(result.hasErrors()) {
			return "/koinMaster/edit";
		}

		return "/koinMaster/editCheck";
	}

	@PostMapping("/koinMaster/finish")
	public String finish(HttpSession session) {
		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		var koinMaster = new KoinMaster();

		if(sessionEditForm.getId() != null) {

			koinMaster.setId(sessionEditForm.getId());

		}

		koinMaster.setName(sessionEditForm.getName());
		koinMaster.setAge(sessionEditForm.getAge());

		this.userService.save(koinMaster);

		return "/koinMaster/finish";
	}

	@RequestMapping(value = "/koinMaster/list")
	public String list(Model model) {
        var list = userService.findAll();
        model.addAttribute("list", list);
        return "/koinMaster/list";
	}

	@RequestMapping("/koinMaster/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model) {

		this.koinMasterRepository.deleteById(id);

		return this.list(model);
	}

	@RequestMapping("/koinMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/koinMaster/edit";
		}

		model.addAttribute("koinMasterForm", sessionEditForm);

		return "/koinMaster/edit";
	}

	@RequestMapping("/koinMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/koinMaster/detail";
		}

		model.addAttribute("koinMasterForm", sessionEditForm);

		return "/koinMaster/detail";
	}

}
