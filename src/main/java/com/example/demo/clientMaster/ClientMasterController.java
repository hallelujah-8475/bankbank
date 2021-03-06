
package com.example.demo.clientMaster;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientMasterController {

	@Autowired
	private ClientMasterService clientMasterService;

	@Autowired
	private ClientMasterRepository clientMasterRepository;

	@RequestMapping(value = "/clientMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var clientMaster = clientMasterRepository.findById(id).get();

			BeanUtils.copyProperties(clientMaster, clientMasterForm);
			clientMasterForm.setId(id);
		}

		session.setAttribute("clientMasterForm", clientMasterForm);

		return "/clientMaster/detail";
	}

	@RequestMapping(value = "/clientMaster/edit")
	private String edit(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var clientMaster = clientMasterRepository.findById(id).get();

			BeanUtils.copyProperties(clientMaster, clientMasterForm);
			clientMasterForm.setId(id);
		}

		session.setAttribute("clientMasterForm", clientMasterForm);

		return "/clientMaster/edit";
	}

	@RequestMapping("/clientMaster/editCheck")
	public String editCheck(@ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm, HttpSession session) {

		session.setAttribute("clientMasterForm", clientMasterForm);

		return "/clientMaster/editCheck";
	}

	@PostMapping("/clientMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm) {

		var clientMaster = new ClientMaster();

		if(clientMasterForm.getId() == null) {
			int maxId = clientMasterService.findByMaxClientId();

			clientMasterForm.setId((long) 0);
			clientMasterForm.setClientid(maxId + 1);
		}

		BeanUtils.copyProperties(clientMasterForm, clientMaster);

		this.clientMasterService.save(clientMaster);

		return "/clientMaster/finish";
	}

	@RequestMapping(value = "/clientMaster/list")
	public String list(Model model) {
        var list = clientMasterService.findAll();
        model.addAttribute("list", list);
        return "/clientMaster/list";
	}

	@RequestMapping("/clientMaster/delete")
	public String delete(Model model, @ModelAttribute("clientMasterForm") ClientMasterForm clientMasterForm, HttpSession session) {

		var sessionEditForm = (ClientMasterForm) session.getAttribute("clientMasterForm");

		this.clientMasterRepository.deleteById(sessionEditForm.getId());

		return this.list(model);
	}

	@RequestMapping("/clientMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (ClientMasterForm) session.getAttribute("clientMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/clientMaster/edit";
		}

		model.addAttribute("clientMasterForm", sessionEditForm);

		return "/clientMaster/edit";
	}

	@RequestMapping("/clientMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (ClientMasterForm) session.getAttribute("clientMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/clientMaster/detail";
		}

		model.addAttribute("clientMasterForm", sessionEditForm);

		return "/clientMaster/detail";
	}

}
