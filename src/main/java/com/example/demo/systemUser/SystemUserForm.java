package com.example.demo.systemUser;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SystemUserForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int koinid;

	@Getter
	@Setter
	private String loginid;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private String role;

	@Getter
	@Setter
	private String koinname;

}
