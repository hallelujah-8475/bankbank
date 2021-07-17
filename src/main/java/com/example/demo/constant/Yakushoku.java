package com.example.demo.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class Yakushoku {

	public static final int SHUNIN = 1;
	public static final int KAKARICHO = 2;
	public static final int DAIRI = 3;
	public static final int SHITENCHO = 4;

	public static final Map<Integer, String> items = new LinkedHashMap<Integer, String>();

	static {

		items.put(SHUNIN, "主任");
		items.put(KAKARICHO, "係長");
		items.put(DAIRI, "代理");
		items.put(SHITENCHO, "支店長");

	}

	public static String getLabel(final int value) {

		if(items.containsKey(value)) {

			return items.get(value);

		}else {

			return "";

		}

	}

	public static Map<Integer, String> getOptionMap() {

		Map<Integer, String> optionMap = new LinkedHashMap<Integer, String>();

    	optionMap.put(SHUNIN, getLabel(SHUNIN));
    	optionMap.put(KAKARICHO, getLabel(KAKARICHO));
    	optionMap.put(DAIRI, getLabel(DAIRI));
    	optionMap.put(SHITENCHO, getLabel(SHITENCHO));

		return optionMap;
	}

}
