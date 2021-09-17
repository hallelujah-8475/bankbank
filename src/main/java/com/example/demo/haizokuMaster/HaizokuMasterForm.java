package com.example.demo.haizokuMaster;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class HaizokuMasterForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private int postcode1;

	@Getter
	@Setter
	private int postcode2;

	@Getter
	@Setter
	private String prefecture;

	@Getter
	@Setter
	private String address1;

	@Getter
	@Setter
	private String address2;

	@Getter
	@Setter
	@NotNull
	private int phonenumber1;

	@Getter
	@Setter
	@NotNull
	private int phonenumber2;

	@Getter
	@Setter
	@NotNull
	private int atmcount;

	@Getter
	@Setter
	@NotNull
	private int parkingflg;

	@Getter
	@Setter
	@NotBlank
	private String biko;

	@Getter
	@Setter
	private int shitenid;

}
