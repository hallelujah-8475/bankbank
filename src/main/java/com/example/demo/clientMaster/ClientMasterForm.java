package com.example.demo.clientMaster;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ClientMasterForm implements Serializable {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@NotBlank
	private String name;

	@Getter
	@Setter
	@NotNull
	private String daihyoname;

	@Getter
	@Setter
	@NotNull
	private int daihyoage;

	@Getter
	@Setter
	@NotNull
	private int yoshinlevel;

	@Getter
	@Setter
	@NotNull
	private int postcode1;

	@Getter
	@Setter
	@NotNull
	private int postcode2;

	@Getter
	@Setter
	@NotBlank
	private String prefecture;

	@Getter
	@Setter
	@NotBlank
	private String address1;

	@Getter
	@Setter
	@NotBlank
	private String address2;

	@Getter
	@Setter
	@NotNull
	private int phonenumber1;

	@Getter
	@Setter
	@NotNull
	private int phonenumber2;
}
