package com.example.demo.shohinMaster;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ShohinMasterForm implements Serializable {

	private Long id;

	@NotBlank
	private String name;

	@NotNull
	private int shohinid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShohinid() {
		return shohinid;
	}

	public void setShohinid(int shohinid) {
		this.shohinid = shohinid;
	}
}
