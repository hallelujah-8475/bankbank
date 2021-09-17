package com.example.demo.keiyakuMaster;

import java.io.Serializable;

import com.example.demo.constant.KeiyakuKbn;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class KeiyakuMasterForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	protected int id;

	@Getter
	@Setter
	protected int shohinid;

	@Getter
	@Setter
	protected int price;

	@Getter
	@Setter
	protected int kinri;

	@Getter
	@Setter
	protected String returnlimit;

	@Getter
	@Setter
	protected int clientid;

	@Getter
	@Setter
	protected int koinid;

	@Getter
	@Setter
	private String koinname;

	@Getter
	@Setter
	private String clientname;

	@Getter
	@Setter
	private String shohinname;
	
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
	private String ringitext;
	
	@Getter
	@Setter
	protected int keiyakukbn;

	public String getKeiyakukbnlabel() {

		return KeiyakuKbn.getLabel(this.keiyakukbn);
	}
	
	@Getter
	@Setter
	private String shikinshitotext;
	
	@Getter
	@Setter
	private String kokatext;
	
	@Getter
	@Setter
	protected int shitenid;
}
