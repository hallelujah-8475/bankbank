package com.example.demo.taioMaster;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TaioMasterForm implements Serializable {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@NotNull
	private int taioid;

	@Getter
	@Setter
	@NotBlank
	private String taiostartdate;

	@Getter
	@Setter
	@NotBlank
	private String taioenddate;

	@Getter
	@Setter
	@NotNull
	private int clientid;

	@Getter
	@Setter
	@NotNull
	private int shitenid;

	@Getter
	@Setter
	@NotNull
	private int koinid;

	@Getter
	@Setter
	private String koinname;

	@Getter
	@Setter
	private String clientname;

	@Getter
	@Setter
	private String shitenname;
}
