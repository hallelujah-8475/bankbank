
package com.example.demo.clientMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "clientmaster")
public class ClientMaster {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;

	@Getter
	@Setter
	@Column
	private String name;

	@Getter
	@Setter
	@Column
	private String daihyoname;

	@Getter
	@Setter
	@Column
	private int daihyoage;

	@Getter
	@Setter
	@Column
	private int yoshinlevel;

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
}
