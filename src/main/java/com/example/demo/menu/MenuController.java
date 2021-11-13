
package com.example.demo.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.keiyakuMaster.KeiyakuMasterRepository;
import com.example.demo.keiyakuMaster.KeiyakuMasterService;
import com.example.demo.news.NewsRepository;

@Controller
public class MenuController {

	@Autowired
	KeiyakuMasterRepository keiyakuMasterRepositry;

	@Autowired
	KeiyakuMasterService keiyakuMasterService;

	@Autowired
	private NewsRepository newsRepositry;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

    	var miShoninCount = keiyakuMasterRepositry.countByShoninflg(0);

    	model.addAttribute("miShoninCount", Integer.toString(miShoninCount));

    	var rankingList = keiyakuMasterService.findByKeiyakuRanking();

    	model.addAttribute("rankingList", rankingList);

        var list = newsRepositry.findAllByKokaiflgOrderByIdAsc(1);
        model.addAttribute("list", list);

    	return "index";
    }

}
