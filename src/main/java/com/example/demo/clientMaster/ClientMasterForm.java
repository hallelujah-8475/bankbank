package com.example.demo.clientMaster;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ClientMasterForm {

	private Long id;

	@NotNull
	private int clientid;

	@NotBlank
	private String name;

	@NotNull
	private String daihyoname;

	@NotNull
	private int daihyoage;

	@NotNull
	private int yoshinlevel;

	@NotNull
	private int postcode1;

	@NotNull
	private int postcode2;

	@NotBlank
	private String prefecture;

	@NotBlank
	private String address1;

	@NotBlank
	private String address2;

	@NotNull
	private int phonenumber1;

	@NotNull
	private int phonenumber2;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPostcode1() {
		return postcode1;
	}
	public void setPostcode1(int postcode1) {
		this.postcode1 = postcode1;
	}
	public int getPostcode2() {
		return postcode2;
	}
	public void setPostcode2(int postcode2) {
		this.postcode2 = postcode2;
	}
	public String getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getPhonenumber1() {
		return phonenumber1;
	}
	public void setPhonenumber1(int phonenumber1) {
		this.phonenumber1 = phonenumber1;
	}
	public int getPhonenumber2() {
		return phonenumber2;
	}
	public void setPhonenumber2(int phonenumber2) {
		this.phonenumber2 = phonenumber2;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public String getDaihyoname() {
		return daihyoname;
	}
	public void setDaihyoname(String daihyoname) {
		this.daihyoname = daihyoname;
	}
	public int getDaihyoage() {
		return daihyoage;
	}
	public void setDaihyoage(int daihyoage) {
		this.daihyoage = daihyoage;
	}
	public int getYoshinlevel() {
		return yoshinlevel;
	}
	public void setYoshinlevel(int yoshinlevel) {
		this.yoshinlevel = yoshinlevel;
	}






}
