package com.example.demo.koinMaster;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.shitenMaster.ShitenMaster;

@Entity
@Table(name = "koinmaster")
public class KoinMaster implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private int koinid;

	@Column
	private String koinname;

	@Column
	private int age;

	@Column
	private int shitenid;

	@Column
	private int busho;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy="shitenid")
//	private List<ShitenMaster> koinlist;

	@OneToOne
	@JoinColumn(name = "shitenid", referencedColumnName = "shitenid", insertable = false, updatable = false)
	private ShitenMaster shitenmaster;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "shitenid", referencedColumnName = "id", insertable = false, updatable = false)
//	private KoinMaster koinmaster;

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

	public int getBusho() {
		return busho;
	}

	public void setBusho(int busho) {
		this.busho = busho;
	}

//	public ShitenMaster getShitenmaster() {
//		return shitenmaster;
//	}
//
//	public void setShitenmaster(ShitenMaster shitenmaster) {
//		this.shitenmaster = shitenmaster;
//	}


//	public List<ShitenMaster> getKoinlist() {
//		return koinlist;
//	}
//
//	public void setKoinlist(List<ShitenMaster> koinlist) {
//		this.koinlist = koinlist;
//	}





}
