package com.example.demo.haizokuMaster;

import org.springframework.stereotype.Service;

@Service
public class HaizokuMasterHelper {

	public String getShitenRank(long totalPrice, long countKeiyaku) {
		
		String shitenRank = "";
		
		if(totalPrice >= 5000000 || countKeiyaku >= 10) {
			
			shitenRank = "S";
		}else if(totalPrice >= 3000000 || countKeiyaku >= 5) {
			
			shitenRank = "A";
		}else if(totalPrice >= 2000000 || countKeiyaku >= 4) {
			
			shitenRank = "B";
		}else if(totalPrice >= 1000000 || countKeiyaku >= 3) {
			
			shitenRank = "C";
		}else {
			
			shitenRank = "D";
		}
		
		return shitenRank;
	}
}
