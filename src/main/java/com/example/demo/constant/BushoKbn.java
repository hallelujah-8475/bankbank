package com.example.demo.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class BushoKbn {

	public static final int YUSHI = 1;
	public static final int YOKIN = 2;
	public static final int EIGYO = 3;

	public static final Map<Integer, String> items = new LinkedHashMap<Integer, String>();

	static {

		items.put(YUSHI, "融資");
		items.put(YOKIN, "預金");
		items.put(EIGYO, "営業");

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

    	optionMap.put(YUSHI, getLabel(YUSHI));
    	optionMap.put(YOKIN, getLabel(YOKIN));
    	optionMap.put(EIGYO, getLabel(EIGYO));

		return optionMap;
	}

}
