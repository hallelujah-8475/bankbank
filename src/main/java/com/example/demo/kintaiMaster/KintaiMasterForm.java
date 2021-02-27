package com.example.demo.kintaiMaster;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class KintaiMasterForm {

	private Long id;

	@NotNull
	private int taioid;

	@NotBlank
	private String taiostartdate;

	@NotBlank
	private String taioenddate;

	@NotNull
	private int clientid;

	@NotNull
	private int shitenid;

	@NotNull
	private int koinid;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public int getTaioid() {
		return taioid;
	}
	public void setTaioid(int taioid) {
		this.taioid = taioid;
	}
	public String getTaiostartdate() {
		return taiostartdate;
	}
	public void setTaiostartdate(String taiostartdate) {
		this.taiostartdate = taiostartdate;
	}
	public String getTaioenddate() {
		return taioenddate;
	}
	public void setTaioenddate(String taioenddate) {
		this.taioenddate = taioenddate;
	}
	public int getShitenid() {
		return shitenid;
	}
	public void setShitenid(int shitenid) {
		this.shitenid = shitenid;
	}
	public int getKoinid() {
		return koinid;
	}
	public void setKoinid(int koinid) {
		this.koinid = koinid;
	}








}
