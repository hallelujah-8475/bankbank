package com.example.demo.systemUser;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class SystemUserForm implements Serializable {

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
	@Min(18)
	private int age;

	@Getter
	@Setter
	@NotBlank
	private String loginid;

	@Getter
	@Setter
	@NotBlank
	private String password;

	@Getter
	@Setter
	private String role;

}
