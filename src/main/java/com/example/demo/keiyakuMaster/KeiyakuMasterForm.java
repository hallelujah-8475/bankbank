package com.example.demo.keiyakuMaster;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
}
