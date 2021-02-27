package com.example.demo.shohinMaster;

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
public class ShohinMasterController {

	@Autowired
	private ShohinMasterService shohinMasterService;

	@Autowired
	private ShohinMasterRepository shohinMasterRepository;

	@RequestMapping(value = "/shohinMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("shohinMasterForm") ShohinMasterForm shohinMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var shohinMaster = shohinMasterRepository.findById(id).get();

			shohinMasterForm.setId(id);
			shohinMasterForm.setName(shohinMaster.getName());
			shohinMasterForm.setShohinid(shohinMaster.getShohinid());

		}

		session.setAttribute("shohinMasterForm", shohinMasterForm);

		return "/shohinMaster/detail";
	}

	@RequestMapping(value = "/shohinMaster/edit")
	private String edit(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("shohinMasterForm") ShohinMasterForm shohinMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var shohinMaster = shohinMasterRepository.findById(id).get();

			shohinMasterForm.setId(id);
			shohinMasterForm.setShohinid(shohinMaster.getShohinid());
			shohinMasterForm.setName(shohinMaster.getName());

		}

		session.setAttribute("shohinMasterForm", shohinMasterForm);

		return "/shohinMaster/edit";
	}

	@RequestMapping("/shohinMaster/editCheck")
	public String editCheck(HttpSession session, @Validated @ModelAttribute ShohinMasterForm shohinMasterForm, BindingResult result) {

		session.setAttribute("shohinMasterForm", shohinMasterForm);

		if(result.hasErrors()) {
			return "/shohinMaster/edit";
		}

		return "/shohinMaster/editCheck";
	}

	@PostMapping("/shohinMaster/finish")
	public String finish(HttpSession session) {
		var sessionEditForm = (ShohinMasterForm) session.getAttribute("shohinMasterForm");

		var shohinMaster = new ShohinMaster();

		if(sessionEditForm.getId() == null) {
			int maxId = shohinMasterService.findByMaxShohinId();

			shohinMaster.setShohinid(maxId + 1);
		}else {
			shohinMaster.setId(sessionEditForm.getId());
			shohinMaster.setShohinid(sessionEditForm.getShohinid());
		}

		shohinMaster.setName(sessionEditForm.getName());

		this.shohinMasterService.save(shohinMaster);

		return "/shohinMaster/finish";
	}

	@RequestMapping(value = "/shohinMaster/list")
	public String list(Model model) {
        var list = shohinMasterService.findAll();
        model.addAttribute("list", list);
        return "/shohinMaster/list";
	}

	@RequestMapping("/shohinMaster/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model) {

		this.shohinMasterRepository.deleteById(id);

		return this.list(model);
	}

	@RequestMapping("/shohinMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (ShohinMasterForm) session.getAttribute("shohinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/shohinMaster/edit";
		}

		model.addAttribute("shohinMasterForm", sessionEditForm);

		return "/shohinMaster/edit";
	}

	@RequestMapping("/shohinMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (ShohinMasterForm) session.getAttribute("shohinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/shohinMaster/detail";
		}

		model.addAttribute("shohinMasterForm", sessionEditForm);

		return "/shohinMaster/detail";
	}

}
