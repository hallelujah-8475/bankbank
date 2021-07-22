package com.example.demo.keiyakuMaster;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
	@NotNull
	protected int shohinid;

	@Getter
	@Setter
	@NotNull
	protected int price;

	@Getter
	@Setter
	@NotNull
	protected int kinri;

	@Getter
	@Setter
	@NotBlank
	protected String returnlimit;

	@Getter
	@Setter
	@NotNull
	protected int clientid;

	@Getter
	@Setter
	@NotNull
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
	@NotNull
	protected int keiyakukbn;

	public String getKeiyakukbnlabel() {

		return KeiyakuKbn.getLabel(this.keiyakukbn);
	}
	
	@Getter
	@Setter
	@Column
	private String shikinshitotext;
	
	@Getter
	@Setter
	@Column
	private String kokatext;
}
