package com.example.demo.koinMaster;

import java.io.Serializable;

import com.example.demo.constant.BushoKbn;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class KoinMasterForm implements Serializable {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private int koinid;

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

}
