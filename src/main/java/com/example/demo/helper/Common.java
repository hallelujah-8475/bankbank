package com.example.demo.helper;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.clientMaster.ClientMaster;
import com.example.demo.clientMaster.ClientMasterService;
import com.example.demo.constant.BushoKbn;
import com.example.demo.constant.Yakushoku;
import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.koinMaster.KoinMasterService;
import com.example.demo.shitenMaster.ShitenMaster;
import com.example.demo.shitenMaster.ShitenMasterService;
import com.example.demo.shohinMaster.ShohinMaster;
import com.example.demo.shohinMaster.ShohinMasterService;

@Service
public class Common {

	@Autowired
	private ShohinMasterService shohinMasterService;
	
	@Autowired
	private ClientMasterService clientMasterService;
	
	@Autowired
	private KoinMasterService koinMasterService;
	
	@Autowired
	private ShitenMasterService shitenMasterService;
	
	public Map<Integer, String> setShohinSelectTag() {

		var list = shohinMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		optionMap.put(0, "");
		
		for(ShohinMaster entity : list) {

			optionMap.put(entity.getId(), entity.getName());
		}

		return optionMap;
	}
	
	public Map<Integer, String> setClientSelectTag() {

		var list = clientMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		optionMap.put(0, "");
		
		for(ClientMaster entity : list) {

			optionMap.put(entity.getId(), entity.getName());
		}

		return optionMap;
	}

	public Map<Integer, String> setKoinSelectTag() {

		var list = koinMasterService.findAll();

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

		optionMap.put(0, "");
		
		for(KoinMaster entity : list) {

			optionMap.put(entity.getId(), entity.getKoinname());
		}

		return optionMap;
	}
	
   public Map<Integer, String> setShitenSelectTag() {

        var list = shitenMasterService.findAll();

        Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

        optionMap.put(0, "選択してください");

        for(ShitenMaster entity : list) {

        	optionMap.put(entity.getId(), entity.getShitenname());
        }

        return optionMap;
    }

    public void setYakushokuSelectTag(Model model) {

    	model.addAttribute("yakushokuList", Yakushoku.getOptionMap());
    }

    public void setBushoSelectTag(Model model) {

		model.addAttribute("bushoList", BushoKbn.getOptionMap());
	}
    
    public String makeFileDataString(String fileName, byte[] fileData) {
    	
    	String fileDataString = "";
    	
		String contenttype = "";
		
		if(!org.apache.commons.lang3.StringUtils.isBlank(fileName)) {
			
			String kakuchoshi = fileName.substring(fileName.lastIndexOf("."));	
			
			if(kakuchoshi.equals(".jpg")) {
				
				contenttype = "image/jpg";
			}else if(kakuchoshi.equals(".png")) {
				
				contenttype = "image/png";
			}
		}
		
		if(fileData != null) {

			fileDataString = "data:" + contenttype + ";base64," + Base64.getEncoder().encodeToString(fileData);
		}
    	
    	return fileDataString;
    }
}
