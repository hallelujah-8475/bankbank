package com.example.demo.koinMaster;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constant.BushoKbn;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterService;

@Controller
public class KoinMasterController {

	@Autowired
	private KoinMasterService koinMasterService;

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	private KoinMasterRepository koinMasterRepository;

    private void setSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getShitenid(), entity.getShitenname());
        }

        model.addAttribute("list", optionMap);
    }

    private void setBushoSelectTag(Model model) {

    	model.addAttribute("bushoList", BushoKbn.getOptionMap());
    }

	@RequestMapping(value = "/koinMaster/detail")
	private String detail(@RequestParam(name = "id", required = false) Long id,
			@ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm,
			HttpSession session,Model model) {

		if(id == null) {
			// 新規登録
		}else {
			// 更新
			var koinMaster = koinMasterRepository.findById(id).get();

			BeanUtils.copyProperties(koinMaster, koinMasterForm);
			koinMasterForm.setId(id);
			var entity = (ShitenMaster)shitenMasterService.findByShitenId(koinMasterForm.getShitenid());
			koinMasterForm.setShitenname(entity.getShitenname());
		}

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

			BeanUtils.copyProperties(koinMaster, koinMasterForm);
			koinMasterForm.setId(id);
			var entity = (ShitenMaster)shitenMasterService.findByShitenId(koinMasterForm.getShitenid());
			koinMasterForm.setShitenname(entity.getShitenname());
		}

		session.setAttribute("koinMasterForm", koinMasterForm);

		this.setSelectTag(model);
		this.setBushoSelectTag(model);

		return "/koinMaster/edit";
	}

	@RequestMapping("/koinMaster/editCheck")
	public String editCheck(@Validated @ModelAttribute KoinMasterForm koinMasterForm, BindingResult result, HttpSession session) {

		session.setAttribute("koinMasterForm", koinMasterForm);

//		if(result.hasErrors()) {
//			return "/koinMaster/edit";
//		}

		return "/koinMaster/editCheck";
	}

	@PostMapping("/koinMaster/finish")
	public String finish(HttpSession session, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm) {

		var koinMaster = new KoinMaster();

		if(koinMasterForm.getId() == null) {
			int maxId = koinMasterService.findByMaxKoinId();

			koinMasterForm.setId((long) 0);
			koinMasterForm.setKoinid(maxId + 1);
		}

		BeanUtils.copyProperties(koinMasterForm, koinMaster);

		this.koinMasterService.save(koinMaster);

		return "/koinMaster/finish";
	}

	@RequestMapping(value = "/koinMaster/list")
	public String list(Model model) {
        var list = koinMasterService.findAll();
        model.addAttribute("list", list);
        return "/koinMaster/list";
	}

	@RequestMapping("/koinMaster/delete")
	public String delete(@RequestParam(name = "id", required = false) Long id, Model model) {

		this.koinMasterRepository.deleteById(id);

		return this.list(model);
	}

	@RequestMapping("/koinMaster/returnEdit")
	public String returnEdit(Model model, @ModelAttribute("koinMasterForm") KoinMasterForm koinMasterForm, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/koinMaster/edit";
		}

		model.addAttribute("koinMasterForm", sessionEditForm);

		this.setSelectTag(model);
		this.setBushoSelectTag(model);

		return "/koinMaster/edit";
	}

	@RequestMapping("/koinMaster/returnDetail")
	public String returnDetail(Model model, HttpSession session) {

		var sessionEditForm = (KoinMasterForm) session.getAttribute("koinMasterForm");

		if(sessionEditForm == null) {

			return "redirect:/koinMaster/detail";
		}

        var entity = (ShitenMaster)shitenMasterService.findByShitenId(sessionEditForm.getShitenid());

        sessionEditForm.setShitenname(entity.getShitenname());

		model.addAttribute("koinMasterForm", sessionEditForm);

		return "/koinMaster/detail";
	}

}
