package com.example.demo.shohinMaster;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ShohinMasterForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	@NotBlank
	private String name;

	@Getter
	@Setter
	@NotNull
	private int shohinid;

	@Getter
	@Setter
	@NotNull
	private int kinri;
}
