package com.example.demo.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.clientMaster.ClientMaster;
import com.example.demo.clientMaster.ClientMasterService;
import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.koinMaster.KoinMasterService;
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
}
