package com.example.demo.koinMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "koinmaster")
public class KoinMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private int koinid;

	@Column
	private String name;

	@Column
	private int age;

	@Column
	private int shitenid;

	@Column
	private int busho;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getBusho() {
		return busho;
	}

	public void setBusho(int busho) {
		this.busho = busho;
	}

}
