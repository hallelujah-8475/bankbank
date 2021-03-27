package com.example.demo.haizokuMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.koinMaster.KoinMasterService;
import com.example.demo.shitenMaster.ShitenMasterService;

@Controller
public class HaizokuMasterController {


	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	private KoinMasterService koinMasterService;

	@RequestMapping(value = "/haizokuMaster/list")
	public String list(Model model, @ModelAttribute("haizokuMasterListForm") HaizokuMasterListForm haizokuMasterListForm) {

		var list = koinMasterService.findUsers(haizokuMasterListForm);

		model.addAttribute("list", list);

		return "/haizokuMaster/list";
	}
}
