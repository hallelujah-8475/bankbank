package com.example.demo.shitenMaster;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "/shitenMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var shitenMaster = shitenMasterRepository.findById(id).get();

			shitenMasterForm.setId(id);
			shitenMasterForm.setName(shitenMaster.getName());
			shitenMasterForm.setPhonenumber1(shitenMaster.getPhonenumber1());
			shitenMasterForm.setPhonenumber2(shitenMaster.getPhonenumber2());
			shitenMasterForm.setPostcode1(shitenMaster.getPostcode1());
			shitenMasterForm.setPostcode2(shitenMaster.getPostcode2());
			shitenMasterForm.setPrefecture(shitenMaster.getPrefecture());
			shitenMasterForm.setAddress1(shitenMaster.getAddress1());
			shitenMasterForm.setAddress2(shitenMaster.getAddress2());
			shitenMasterForm.setAtmcount(shitenMaster.getAtmcount());
			shitenMasterForm.setParkingflg(shitenMaster.getParkingflg());
			shitenMasterForm.setBiko(shitenMaster.getBiko());

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

			shitenMasterForm.setId(id);
			shitenMasterForm.setName(shitenMaster.getName());
			shitenMasterForm.setPhonenumber1(shitenMaster.getPhonenumber1());
			shitenMasterForm.setPhonenumber2(shitenMaster.getPhonenumber2());
			shitenMasterForm.setPostcode1(shitenMaster.getPostcode1());
			shitenMasterForm.setPostcode2(shitenMaster.getPostcode2());
			shitenMasterForm.setPrefecture(shitenMaster.getPrefecture());
			shitenMasterForm.setAddress1(shitenMaster.getAddress1());
			shitenMasterForm.setAddress2(shitenMaster.getAddress2());
			shitenMasterForm.setAtmcount(shitenMaster.getAtmcount());
			shitenMasterForm.setParkingflg(shitenMaster.getParkingflg());
			shitenMasterForm.setBiko(shitenMaster.getBiko());

		}

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		return "/shitenMaster/edit";
	}

	@RequestMapping("/shitenMaster/editCheck")
	public String editCheck(@ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session) {

		session.setAttribute("shitenMasterForm", shitenMasterForm);

		return "/shitenMaster/editCheck";
	}

	@PostMapping("/shitenMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm) {
		ShitenMasterForm sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		var shitenMaster = new ShitenMaster();

		if(sessionEditForm.getId() != null) {
			shitenMaster.setId(sessionEditForm.getId());
		}

		shitenMaster.setName(sessionEditForm.getName());
		shitenMaster.setPhonenumber1(sessionEditForm.getPhonenumber1());
		shitenMaster.setPhonenumber2(sessionEditForm.getPhonenumber2());
		shitenMaster.setPostcode1(sessionEditForm.getPostcode1());
		shitenMaster.setPostcode2(sessionEditForm.getPostcode2());
		shitenMaster.setPrefecture(sessionEditForm.getPrefecture());
		shitenMaster.setAddress1(sessionEditForm.getAddress1());
		shitenMaster.setAddress2(sessionEditForm.getAddress2());
		shitenMaster.setAtmcount(sessionEditForm.getAtmcount());
		shitenMaster.setParkingflg(sessionEditForm.getParkingflg());
		shitenMaster.setBiko(sessionEditForm.getBiko());

		this.shitenMasterService.save(shitenMaster);

		return "/shitenMaster/finish";
	}

	@RequestMapping(value = "/shitenMaster/list")
	public String list(Model model) {
        var list = shitenMasterService.findAll();
        model.addAttribute("list", list);
        return "/shitenMaster/list";
	}

	@RequestMapping("/shitenMaster/delete")
	public String delete(Model model, @ModelAttribute("shitenMasterForm") ShitenMasterForm shitenMasterForm, HttpSession session) {

		var sessionEditForm = (ShitenMasterForm) session.getAttribute("shitenMasterForm");

		this.shitenMasterRepository.deleteById(sessionEditForm.getId());

		return this.list(model);
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
