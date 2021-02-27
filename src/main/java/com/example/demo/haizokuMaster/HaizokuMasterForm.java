package com.example.demo.haizokuMaster;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class HaizokuMasterForm {

	private Long id;

	@NotBlank
	private String name;

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

	@NotNull
	private int atmcount;

	@NotNull
	private int parkingflg;

	@NotBlank
	private String biko;

	private int shitenid;

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
	public int getAtmcount() {
		return atmcount;
	}
	public void setAtmcount(int atmcount) {
		this.atmcount = atmcount;
	}
	public int getParkingflg() {
		return parkingflg;
	}
	public void setParkingflg(int parkingflg) {
		this.parkingflg = parkingflg;
	}
	public String getBiko() {
		return biko;
	}
	public void setBiko(String biko) {
		this.biko = biko;
	}
	public int getShitenid() {
		return shitenid;
	}
	public void setShitenid(int shitenid) {
		this.shitenid = shitenid;
	}




}
