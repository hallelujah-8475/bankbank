package com.example.demo.clientMaster;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ClientMasterForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String daihyoname;

	@Getter
	@Setter
	private int daihyoage;

	@Getter
	@Setter
	private int yoshinlevel;

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
}
