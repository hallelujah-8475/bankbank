package com.example.demo.koinMaster;

public class KoinMasterForm {

	private Long id;

	private int koinid;

	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

		String bushoname = "";

		switch(this.busho) {
		case 1:
			bushoname = "融資";
			break;
		case 2:
			bushoname = "預金";
			break;
		case 3:
			bushoname = "営業";
			break;
		}

		return bushoname;
	}

}
