package com.example.demo.haizokuMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HaizokuMasterController {

	@Autowired
	private HaizokuMasterService haizokuMasterService;

	@RequestMapping(value = "/haizokuMaster/list")
	public String list(Model model) {
        var list = haizokuMasterService.findForHaizokuMaster();
        model.addAttribute("list", list);
        return "/haizokuMaster/list";
	}

}
