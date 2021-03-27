package com.example.demo.koinMaster;

import java.io.Serializable;

import com.example.demo.constant.BushoKbn;

public class KoinMasterForm implements Serializable {

	private Long id;

	private int koinid;

	private String koinname;

	private int age;

	private int shitenid;

	private int busho;

	private String shitenname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKoinid() {
		return koinid;
	}

	public void setKoinid(int koinid) {
		this.koinid = koinid;
	}

	public String getKoinname() {
		return koinname;
	}

	public void setKoinname(String koinname) {
		this.koinname = koinname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getShitenid() {
		return shitenid;
	}

	public void setShitenid(int shitenid) {
		this.shitenid = shitenid;
	}

	public String getShitenname() {
		return shitenname;
	}

	public void setShitenname(String shitenname) {
		this.shitenname = shitenname;
	}

	public int getBusho() {
		return busho;
	}

	public void setBusho(int busho) {
		this.busho = busho;
	}

	public String getBushoname() {

		return BushoKbn.getLabel(this.busho);
	}

}
