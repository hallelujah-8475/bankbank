package com.example.demo.koinMaster;

import java.io.Serializable;

import com.example.demo.constant.BushoKbn;
import com.example.demo.constant.Yakushoku;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class KoinMasterForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String koinname;

	@Getter
	@Setter
	private int age;

	@Getter
	@Setter
	private int shitenid;

	@Getter
	@Setter
	private int busho;

	@Getter
	@Setter
	private String shitenname;

	public String getBushoname() {

		return BushoKbn.getLabel(this.busho);
	}

	@Getter
	@Setter
	private String nyushastartdate;

	@Getter
	@Setter
	private String haizokustartdate;

	@Getter
	@Setter
	private int tenkinflg;

	@Getter
	@Setter
	private int yakushoku;

	public String getYakushokuname() {

		return Yakushoku.getLabel(this.yakushoku);
	}
	
	@Getter
	@Setter
	private byte[] filedata;

	@Getter
	@Setter
	private String filename;

	@Getter
	@Setter
	private String filedataString;
	
	@Getter
	@Setter
	private int nextShitenIdSameFlg;
	
	@Getter
	@Setter
	private int firstFlg;
	
	@Getter
	@Setter
	private long totalPrice;
	
	@Getter
	@Setter
	private long countKeiyaku;
	
	@Getter
	@Setter
	private String shitenRank;
}
