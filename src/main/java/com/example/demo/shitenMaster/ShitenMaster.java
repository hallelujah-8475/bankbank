
package com.example.demo.shitenMaster;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shitenmaster")
public class ShitenMaster implements Serializable{

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Getter
	@Setter
	@Column
	private String shitenname;

	@Getter
	@Setter
	@Column
	private int postcode1;

	@Getter
	@Setter
	@Column
	private int postcode2;

	@Getter
	@Setter
	@Column
	private String prefecture;

	@Getter
	@Setter
	@Column
	private String address1;

	@Getter
	@Setter
	@Column
	private String address2;

	@Getter
	@Setter
	@Column
	private int phonenumber1;

	@Getter
	@Setter
	@Column
	private int phonenumber2;

	@Getter
	@Setter
	@Column
	private int atmcount;

	@Getter
	@Setter
	@Column
	private int parkingflg;

	@Getter
	@Setter
	@Column
	private String biko;

	@Getter
	@Setter
	@Column
	private int shitenid;

}
