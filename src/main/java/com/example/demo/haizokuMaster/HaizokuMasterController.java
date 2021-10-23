package com.example.demo.haizokuMaster;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.helper.Common;
import com.example.demo.keiyakuMaster.KeiyakuMasterService;
import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.koinMaster.KoinMasterForm;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterRepository;
import com.example.demo.shitenMaster.ShitenMasterService;
import com.example.demo.systemUser.PagenationHelper;

@Controller
public class HaizokuMasterController {

	@Autowired
	private HaizokuMasterService haizokuMasterService;
	
	@Autowired
	private HaizokuMasterHelper haizokuMasterHelper;
	
	@Autowired
	private KeiyakuMasterService keiyakuMasterService;

	@Autowired
	private ShitenMasterService shitenMasterService;
	
	@Autowired
	private ShitenMasterRepository shitenMasterRepository;
	
	@Autowired
	private Common common;

	@Autowired
	HttpServletRequest request;

    @Autowired
    HttpSession session;

    private void setSelectTag(Model model) {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        optionMap.put(0, "選択してください");

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getId(), entity.getShitenname());
        }

        model.addAttribute("optionMapList", optionMap);
    }
	@RequestMapping(value = "/haizokuMaster/pagenate")
	public String pagenate(Model model, Pageable pageable) {

		HaizokuMasterListForm haizokuMasterListForm = (HaizokuMasterListForm) session.getAttribute("haizokuMasterListForm");

		return this.list(model, haizokuMasterListForm, pageable);
	}

	@RequestMapping(value = "/haizokuMaster/list")
	public String list(Model model, @ModelAttribute("haizokuMasterListForm") HaizokuMasterListForm haizokuMasterListForm,
			@PageableDefault(page = 0, size = 10, direction = Direction.DESC, sort = { "shitenid" }) Pageable pageable) {

		if(request.getParameter("fromMenu") != null) {

			haizokuMasterListForm.setShitenid(0);
		}
		
		session.setAttribute("haizokuMasterListForm", haizokuMasterListForm);

		Page<KoinMaster> list = haizokuMasterService.findUsers(haizokuMasterListForm, pageable);

		List<KoinMasterForm> koinMasterFormConvertList = new ArrayList<KoinMasterForm>();
		
		int first = 1;
		
		for(int i = 0; i < list.getContent().size(); i++) {
			
			KoinMasterForm koinMasterForm = new KoinMasterForm();

			if(first == 1) {
				
				koinMasterForm.setFirstFlg(1);
				koinMasterForm.setShitenname(shitenMasterRepository.findById(list.getContent().get(i).getShitenid()).getShitenname());
			}else if(list.getContent().get(i).getShitenid() != list.getContent().get(i - 1).getShitenid()) {
				
				koinMasterForm.setShitenname(shitenMasterRepository.findById(list.getContent().get(i).getShitenid()).getShitenname());
			}
			
			koinMasterForm.setId(list.getContent().get(i).getId());
			koinMasterForm.setKoinname(list.getContent().get(i).getKoinname());
			koinMasterForm.setYakushoku(list.getContent().get(i).getYakushoku());
			koinMasterForm.setFiledataString(common.makeFileDataString(list.getContent().get(i).getFilename(), list.getContent().get(i).getFiledata()));
			
			if(keiyakuMasterService.getSumPrice(list.getContent().get(i).getShitenid()) != 0) {

				koinMasterForm.setTotalPrice(keiyakuMasterService.getSumPrice(list.getContent().get(i).getShitenid()));
			}
			
			if(keiyakuMasterService.getCountKeiyaku(list.getContent().get(i).getShitenid()) != 0) {
				
				koinMasterForm.setCountKeiyaku(keiyakuMasterService.getCountKeiyaku(list.getContent().get(i).getShitenid()));
			}

			if(keiyakuMasterService.getSumPrice(list.getContent().get(i).getShitenid()) != 0
			&& keiyakuMasterService.getCountKeiyaku(list.getContent().get(i).getShitenid()) != 0) {
			
				koinMasterForm.setShitenRank(haizokuMasterHelper.getShitenRank(keiyakuMasterService.getSumPrice(list.getContent().get(i).getShitenid()), keiyakuMasterService.getCountKeiyaku(list.getContent().get(i).getShitenid())));
			}else {
				
				koinMasterForm.setShitenRank("集計不可");
			}
			
			koinMasterFormConvertList.add(koinMasterForm);
			
			if(i < list.getContent().size() - 1) {
				
				if(list.getContent().get(i).getShitenid() != list.getContent().get(i + 1).getShitenid()) {
					
					koinMasterForm.setNextShitenIdSameFlg(1);
				}else {
					
					koinMasterForm.setNextShitenIdSameFlg(0);
				}
			}
			
			first++;
		}
			
		model.addAttribute("list", koinMasterFormConvertList);
		model.addAttribute("haizokuMasterListForm", haizokuMasterListForm);
		model.addAttribute("page", PagenationHelper.createPagenation(list));

		this.setSelectTag(model);

		return "haizokuMaster/list";
	}
}
