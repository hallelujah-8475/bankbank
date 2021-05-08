package com.example.demo.shitenMaster;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class ShitenMasterController {

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	private ShitenMasterRepository shitenMasterRepository;

	@Autowired
	private ShitenMasterValidator shitenMasterValidator;

	@InitBinder("shitenMasterForm")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(shitenMasterValidator);
	}

	@RequestMapping(value = "/shitenMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var shitenMaster = shitenMasterRepository.findById(id).get();

			BeanUtils.copyProperties(shitenMaster, shitenMasterForm);
			shitenMasterForm.setId(id);
		}

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		return "/shitenMaster/detail";
	}

	@RequestMapping(value = "/shitenMaster/edit")
	private String edit(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var shitenMaster = shitenMasterRepository.findById(id).get();

			BeanUtils.copyProperties(shitenMaster, shitenMasterForm);
			shitenMasterForm.setId(id);
		}

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		return "/shitenMaster/edit";
	}

	@RequestMapping("/shitenMaster/editCheck")
	public String editCheck(@Validated @ModelAttribute ShitenMasterForm shitenMasterForm, BindingResult result, HttpSession session) {

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		if(result.hasErrors()) {
			return "/shitenMaster/edit";
		}

		return "/shitenMaster/editCheck";
	}

	@PostMapping("/shitenMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm) {

		var shitenMaster = new ShitenMaster();

		if(shitenMasterForm.getId() == null) {

			int maxId = shitenMasterService.findByMaxShitenId();

			shitenMasterForm.setId((long) 0);
			shitenMasterForm.setShitenid(maxId + 1);
		}

		BeanUtils.copyProperties(shitenMasterForm, shitenMaster);

		this.shitenMasterService.save(shitenMaster);

		return "/shitenMaster/finish";
	}

	@RequestMapping(value = "/shitenMaster/list")
	public String list(Model model, @ModelAttribute("shitenMasterListForm") ShitenMasterListForm shitenMasterListForm) {
        var list = shitenMasterService.findUsers(shitenMasterListForm);
        model.addAttribute("list", list);
        return "/shitenMaster/list";
	}

	@RequestMapping("/shitenMaster/delete")
	public String delete(Model model, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session, @ModelAttribute("shitenMasterListForm") ShitenMasterListForm shitenMasterListForm) {

		var sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		this.shitenMasterRepository.deleteById(sessionEditForm.getId());

		return this.list(model, shitenMasterListForm);
	}

	@RequestMapping("/shitenMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/shitenMaster/edit";
		}

		model.addAttribute("shitenMasterForm", sessionEditForm);

		return "/shitenMaster/edit";
	}

	@RequestMapping("/shitenMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/shitenMaster/detail";
		}

		model.addAttribute("shitenMasterForm", sessionEditForm);

		return "/shitenMaster/detail";
	}

}
