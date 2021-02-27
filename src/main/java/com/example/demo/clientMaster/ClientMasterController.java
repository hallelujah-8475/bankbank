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

			clientMasterForm.setId(id);
			clientMasterForm.setName(clientMaster.getName());
			clientMasterForm.setPhonenumber1(clientMaster.getPhonenumber1());
			clientMasterForm.setPhonenumber2(clientMaster.getPhonenumber2());
			clientMasterForm.setPostcode1(clientMaster.getPostcode1());
			clientMasterForm.setPostcode2(clientMaster.getPostcode2());
			clientMasterForm.setPrefecture(clientMaster.getPrefecture());
			clientMasterForm.setAddress1(clientMaster.getAddress1());
			clientMasterForm.setAddress2(clientMaster.getAddress2());
			clientMasterForm.setClientid(clientMaster.getClientid());
			clientMasterForm.setDaihyoage(clientMaster.getDaihyoage());
			clientMasterForm.setDaihyoname(clientMaster.getDaihyoname());
			clientMasterForm.setYoshinlevel(clientMaster.getYoshinlevel());


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

//			clientMasterForm.setId(id);
//			clientMasterForm.setName(clientMaster.getName());
//			clientMasterForm.setPhonenumber1(clientMaster.getPhonenumber1());
//			clientMasterForm.setPhonenumber2(clientMaster.getPhonenumber2());
//			clientMasterForm.setPostcode1(clientMaster.getPostcode1());
//			clientMasterForm.setPostcode2(clientMaster.getPostcode2());
//			clientMasterForm.setPrefecture(clientMaster.getPrefecture());
//			clientMasterForm.setAddress1(clientMaster.getAddress1());
//			clientMasterForm.setAddress2(clientMaster.getAddress2());
//			clientMasterForm.setClientid(clientMaster.getClientid());
//			clientMasterForm.setDaihyoage(clientMaster.getDaihyoage());
//			clientMasterForm.setDaihyoname(clientMaster.getDaihyoname());
//			clientMasterForm.setYoshinlevel(clientMaster.getYoshinlevel());

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
		ClientMasterForm sessionEditForm = (ClientMasterForm) session.getAttribute("clientMasterForm");

		var clientMaster = new ClientMaster();

		if(sessionEditForm.getId() == null) {
			int maxId = clientMasterService.findByMaxClientId();

			clientMaster.setClientid(maxId + 1);
		}else {
			clientMaster.setId(sessionEditForm.getId());
			clientMaster.setClientid(sessionEditForm.getClientid());
		}

		clientMaster.setName(sessionEditForm.getName());
		clientMaster.setPhonenumber1(sessionEditForm.getPhonenumber1());
		clientMaster.setPhonenumber2(sessionEditForm.getPhonenumber2());
		clientMaster.setPostcode1(sessionEditForm.getPostcode1());
		clientMaster.setPostcode2(sessionEditForm.getPostcode2());
		clientMaster.setPrefecture(sessionEditForm.getPrefecture());
		clientMaster.setAddress1(sessionEditForm.getAddress1());
		clientMaster.setAddress2(sessionEditForm.getAddress2());
		clientMaster.setDaihyoage(sessionEditForm.getDaihyoage());
		clientMaster.setDaihyoname(sessionEditForm.getDaihyoname());
		clientMaster.setYoshinlevel(sessionEditForm.getYoshinlevel());


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
