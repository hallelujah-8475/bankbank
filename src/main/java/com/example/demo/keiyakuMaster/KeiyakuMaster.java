package com.example.demo.keiyakuMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "keiyakumaster")
public class KeiyakuMaster {

	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column
	  private long id;

	  @Column
	  private int keiyakuid;

	  @Column
	  private int shohinid;

	  @Column
	  private int price;

	  @Column
	  private int kinri;

	  @Column
	  private String returnlimit;

	  @Column
	  private int clientid;

	  @Column
	  private int koinid;

	  @Column
	  private int shoninflg;

	  public long getId() {
	    return id;
	  }
	  public void setId(long id) {
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
	public int getShoninflg() {
		return shoninflg;
	}
	public void setShoninflg(int shoninflg) {
		this.shoninflg = shoninflg;
	}





}
