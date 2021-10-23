package com.example.demo.shohinMaster;

import java.io.Serializable;

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
	private String name;

	@Getter
	@Setter
	private Double kinri;
}
