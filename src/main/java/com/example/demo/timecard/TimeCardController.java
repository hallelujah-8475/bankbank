package com.example.demo.timecard;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.koinMaster.KoinMasterService;

@Controller
public class TimeCardController {

	@Autowired
	private TimeCardService timeCardService;

	@Autowired
	private TimeCardRepository timeCardRepository;

	@Autowired
	private KoinMasterService koinMasterService;

	@RequestMapping(value = "/timecard")
	private String index(Model model) {

        var list = timeCardRepository .findByWorkdateEqualsOrderById(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

        model.addAttribute("list", list);

		return "timecard/index";
	}

	@RequestMapping(value = "/timecard/list")
	public String list(Model model) {
		
        var list = timeCardRepository .findByWorkdateEqualsOrderById(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

        model.addAttribute("list", list);
        
        return "/timecard/list";
	}

	@RequestMapping("/timecard/entry")
	public String entry(@RequestParam("id") int id, Model model) {

		String insertTime = new SimpleDateFormat("HH:mm:ss").format(new Date());

		var timeCard = timeCardRepository.findById(id);
		
		if(StringUtils.isBlank(timeCard.getWorkstarttime())) {

	        timeCard.setWorkstarttime(insertTime);
		}else {

	        timeCard.setWorkendtime(insertTime);
		}

        this.timeCardService.save(timeCard);

		return this.index(model);
	}

	@RequestMapping("/timecard/reset")
	public String reset() {

		String insertDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        for(KoinMaster target : koinMasterService.findAll()) {

        	var timeCard = new TimeCard();

        	timeCard.setKoinid(target.getId());

        	timeCard.setWorkdate(insertDate);

        	this.timeCardService.save(timeCard);
        }

        return "timecard/list";
	}

}
