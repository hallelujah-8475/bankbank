package com.example.demo.haizokuMaster;

import java.io.Serializable;

public class HaizokuMasterListForm implements Serializable {

	private String koinname;

	private int shitenid;

	private String age;

	public String getKoinname() {
		return koinname;
	}

	public void setKoinname(String koinname) {
		this.koinname = koinname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getShitenid() {
		return shitenid;
	}

	public void setShitenid(int shitenid) {
		this.shitenid = shitenid;
	}

}
