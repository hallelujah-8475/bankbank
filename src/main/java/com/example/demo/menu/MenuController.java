
package com.example.demo.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.keiyakuMaster.KeiyakuMasterRepository;

@Controller
public class MenuController {

	@Autowired
	KeiyakuMasterRepository keiyakuMasterRepositry;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

    	var miShoninCount = keiyakuMasterRepositry.countByShoninflg(0);

    	model.addAttribute("miShoninCount", Integer.toString(miShoninCount));

    	return "index";
    }
}
