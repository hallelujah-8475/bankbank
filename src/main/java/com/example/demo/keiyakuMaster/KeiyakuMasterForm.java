package com.example.demo.keiyakuMaster;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class KeiyakuMasterForm {

	protected Long id;

	@NotNull
	protected int keiyakuid;

	@NotNull
	protected int shohinid;

	@NotNull
	protected int price;

	@NotNull
	protected int kinri;

	@NotBlank
	protected String returnlimit;

	@NotNull
	protected int clientid;

	@NotNull
	protected int koinid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKeiyakuid() {
		return keiyakuid;
	}

	public void setKeiyakuid(int keiyakuid) {
		this.keiyakuid = keiyakuid;
	}

	public int getShohinid() {
		return shohinid;
	}

	public void setShohinid(int shohinid) {
		this.shohinid = shohinid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getKinri() {
		return kinri;
	}

	public void setKinri(int kinri) {
		this.kinri = kinri;
	}

	public String getReturnlimit() {
		return returnlimit;
	}

	public void setReturnlimit(String returnlimit) {
		this.returnlimit = returnlimit;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getKoinid() {
		return koinid;
	}

	public void setKoinid(int koinid) {
		this.koinid = koinid;
	}



}
