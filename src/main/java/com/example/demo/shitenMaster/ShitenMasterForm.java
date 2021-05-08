package com.example.demo.shitenMaster;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ShitenMasterForm implements Serializable {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String shitenname;

	@Getter
	@Setter
	private int postcode1;

	@Getter
	@Setter
	private int postcode2;

	@Getter
	@Setter
	private String prefecture;

	@Getter
	@Setter
	private String address1;

	@Getter
	@Setter
	private String address2;

	@Getter
	@Setter
	private int phonenumber1;

	@Getter
	@Setter
	private int phonenumber2;

	@Getter
	@Setter
	private int atmcount;

	@Getter
	@Setter
	private int parkingflg;

	@Getter
	@Setter
	private String biko;

	@Getter
	@Setter
	private int shitenid;

}
