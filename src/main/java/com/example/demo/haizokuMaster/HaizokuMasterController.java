package com.example.demo.haizokuMaster;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterService;
import com.example.demo.systemUser.PagenationHelper;

@Controller
public class HaizokuMasterController {


	@Autowired
	private HaizokuMasterService haizokuMasterService;

	@Autowired
	private ShitenMasterService shitenMasterService;

	@Autowired
	HttpServletRequest request;

    @Autowired
    HttpSession session;

    private void setSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        optionMap.put(0, "選択してください");

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getShitenid(), entity.getShitenname());
        }

        model.addAttribute("optionMapList", optionMap);
    }
	@RequestMapping(value = "/haizokuMaster/pagenate")
	public String pagenate(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {

		HaizokuMasterListForm haizokuMasterListForm = (HaizokuMasterListForm) session.getAttribute("haizokuMasterListForm");

		return this.list(model, haizokuMasterListForm, pageable);
	}

	@RequestMapping(value = "/haizokuMaster/list")
	public String list(Model model, @ModelAttribute("haizokuMasterListForm") HaizokuMasterListForm haizokuMasterListForm,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {

		if(request.getParameter("fromMenu") != null) {

			haizokuMasterListForm.setShitenid(0);
		}

		session.setAttribute("haizokuMasterListForm", haizokuMasterListForm);

		Page<KoinMaster> list = haizokuMasterService.findUsers(haizokuMasterListForm, pageable);

		model.addAttribute("list", list.getContent());
		model.addAttribute("haizokuMasterListForm", haizokuMasterListForm);
		model.addAttribute("page", PagenationHelper.createPagenation(list));

		this.setSelectTag(model);

		return "/haizokuMaster/list";
	}
}
