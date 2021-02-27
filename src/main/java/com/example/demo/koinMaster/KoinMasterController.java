package com.example.demo.koinMaster;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterService;
import com.example.demo.timecard.TimeCardService;

@Controller
public class KoinMasterController {

	@Autowired
	private KoinMasterService koinMasterService;

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	private TimeCardService timeCardService;

	@Autowired
	private KoinMasterRepository koinMasterRepository;

    private void setSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getShitenid(), entity.getName());
        }

        model.addAttribute("list", optionMap);
    }

    private void setBushoSelectTag(Model model) {

    	Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

    	optionMap.put(1, "融資");
    	optionMap.put(2, "営業");
    	optionMap.put(3, "預金");

    	model.addAttribute("bushoList", optionMap);
    }

	@RequestMapping(value = "/koinMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id,
			@ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm,
			HttpSession session,Model model) {

		var koinMaster = koinMasterRepository.findById(id).get();

		koinMasterForm.setId(id);
		koinMasterForm.setName(koinMaster.getName());
		koinMasterForm.setAge(koinMaster.getAge());
		koinMasterForm.setBusho(koinMaster.getBusho());
		koinMasterForm.setShitenid(koinMaster.getShitenid());
		var entity = (ShitenMaster)shitenMasterService.findByShitenId(koinMasterForm.getShitenid());
		koinMasterForm.setShitenname(entity.getName());

		session.setAttribute("koinMasterForm", koinMasterForm);

		return "/koinMaster/detail";
	}

	@RequestMapping(value = "/koinMaster/edit")
	private String edit(Model model, @RequestParam(name = "id", required = false) Long id, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm, HttpSession session) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var koinMaster = koinMasterRepository.findById(id).get();

			koinMasterForm.setId(id);
			koinMasterForm.setKoinid(koinMaster.getKoinid());
			koinMasterForm.setName(koinMaster.getName());
			koinMasterForm.setAge(koinMaster.getAge());
			koinMasterForm.setBusho(koinMaster.getBusho());
			koinMasterForm.setShitenid(koinMaster.getShitenid());

		}

		session.setAttribute("koinMasterForm", koinMasterForm);

		this.setSelectTag(model);
		this.setBushoSelectTag(model);

		return "/koinMaster/edit";
	}

	@RequestMapping("/koinMaster/editCheck")
	public String editCheck(HttpSession session, @Validated @ModelAttribute KoinMasterForm koinMasterForm, BindingResult result) {

		session.setAttribute("koinMasterForm", koinMasterForm);

		if(result.hasErrors()) {
			return "/koinMaster/edit";
		}

		return "/koinMaster/editCheck";
	}

	@PostMapping("/koinMaster/finish")
	public String finish(HttpSession session) {
		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		var koinMaster = new KoinMaster();

		if(sessionEditForm.getId() == null) {
			int maxId = koinMasterService.findByMaxKoinId();

			koinMaster.setKoinid(maxId + 1);
		}else {
			koinMaster.setId(sessionEditForm.getId());
			koinMaster.setKoinid(sessionEditForm.getKoinid());
		}

		koinMaster.setName(sessionEditForm.getName());
		koinMaster.setAge(sessionEditForm.getAge());
		koinMaster.setBusho(sessionEditForm.getBusho());
		koinMaster.setShitenid(sessionEditForm.getShitenid());

		this.koinMasterService.save(koinMaster);

		return "/koinMaster/finish";
	}

	@RequestMapping(value = "/koinMaster/list")
	public String list(Model model) {
        var list = timeCardService.findForKoinMasterAndTimeCard();
        model.addAttribute("list", list);
        return "/koinMaster/list";
	}

	@RequestMapping("/koinMaster/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model) {

		this.koinMasterRepository.deleteById(id);

		return this.list(model);
	}

	@RequestMapping("/koinMaster/returnEdit")
	public String returnEdit(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/koinMaster/edit";
		}

		model.addAttribute("koinMasterForm", sessionEditForm);

		return "/koinMaster/edit";
	}

	@RequestMapping("/koinMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/koinMaster/detail";
		}

        var entity = (ShitenMaster)shitenMasterService.findByShitenId(sessionEditForm.getShitenid());

        sessionEditForm.setShitenname(entity.getName());

		model.addAttribute("koinMasterForm", sessionEditForm);

		return "/koinMaster/detail";
	}

}
