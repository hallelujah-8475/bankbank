package com.example.demo.haizokuMaster;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.koinMaster.KoinMasterService;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterService;

@Controller
public class HaizokuMasterController {


	@Autowired
	private KoinMasterService koinMasterService;

	@Autowired
	private ShitenMasterService shitenMasterService;

    private void setSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getShitenid(), entity.getShitenname());
        }

        model.addAttribute("optionMapList", optionMap);
    }

	@RequestMapping(value = "/haizokuMaster/list")
	public String list(Model model, @ModelAttribute("haizokuMasterListForm") HaizokuMasterListForm haizokuMasterListForm) {

		var list = koinMasterService.findUsers(haizokuMasterListForm);

		model.addAttribute("list", list);
		this.setSelectTag(model);

		return "/haizokuMaster/list";
	}
}
