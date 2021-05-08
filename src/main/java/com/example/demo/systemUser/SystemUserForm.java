package com.example.demo.systemUser;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SystemUserForm implements Serializable {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private int age;

	@Getter
	@Setter
	private String loginid;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private String role;

}
