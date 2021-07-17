package com.example.demo.haizokuMaster;

import java.io.Serializable;

import com.example.demo.constant.Yakushoku;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class HaizokuMasterListForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String koinname;

	@Getter
	@Setter
	private int shitenid;

	@Getter
	@Setter
	private String age;

	@Getter
	@Setter
	private int yakushoku;

	public String getYakushokuname() {

		return Yakushoku.getLabel(this.yakushoku);
	}
}
