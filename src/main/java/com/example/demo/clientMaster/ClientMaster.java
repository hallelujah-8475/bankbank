
package com.example.demo.clientMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "clientmaster")
public class ClientMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private int clientid;

	@Column
	private String name;

	@Column
	private String daihyoname;

	@Column
	private int daihyoage;

	@Column
	private int yoshinlevel;

	@Column
	private int postcode1;

	@Column
	private int postcode2;

	@Column
	private String prefecture;

	@Column
	private String address1;

	@Column
	private String address2;

	@Column
	private int phonenumber1;

	@Column
	private int phonenumber2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
