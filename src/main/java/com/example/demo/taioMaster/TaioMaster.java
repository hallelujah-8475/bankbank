
package com.example.demo.taioMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "taiomaster")
public class TaioMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private int taioid;

	@Column
	private String taiostartdate;

	@Column
	private String taioenddate;

	@Column
	private int clientid;

	@Column
	private int shitenid;

	@Column
	private int koinid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
