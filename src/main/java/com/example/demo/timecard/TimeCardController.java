package com.example.demo.timecard;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TimeCardController {

	@Autowired
	private TimeCardService timeCardService;

	@RequestMapping(value = "/timecard")
	private String index(Model model) {

        var list = timeCardService.findForKoinMasterAndTimeCard();

        model.addAttribute("list", list);

		return "/timecard/index";
	}

	@RequestMapping("/timecard/entry")
	public String entry(@RequestParam(name = "id", required = false) int id, Model model) {

		System.out.println(id);

		var timeCard = new TimeCard();

		Date date = new Date();
		SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy/MM/dd");
		String insertDate = formatedDate.format(date);

		SimpleDateFormat formatedTime = new SimpleDateFormat("HH:mm:ss");
		String insertTime = formatedTime.format(date);

		var targetTimecard = timeCardService.findByKoinIdAndWorkDate(id, insertDate);

		if(targetTimecard == null) {
			int maxId = timeCardService.findByMaxTimeCardId();

			timeCard.setTimecardid(maxId + 1);

			timeCard.setKoinid(id);

			timeCard.setWorkdate(insertDate);

	        timeCard.setWorkstarttime(insertTime);

		}else {

			timeCard.setId(targetTimecard.getId());

			timeCard.setTimecardid(targetTimecard.getTimecardid());

			timeCard.setKoinid(targetTimecard.getKoinid());

			timeCard.setWorkdate(targetTimecard.getWorkdate());

	        timeCard.setWorkstarttime(targetTimecard.getWorkstarttime());

	        timeCard.setWorkendtime(insertTime);
		}


        this.timeCardService.save(timeCard);


		return this.index(model);
	}

}
