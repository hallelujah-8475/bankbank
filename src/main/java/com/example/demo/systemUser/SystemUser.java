package com.example.demo.systemUser;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "systemuser")
public class SystemUser implements Serializable {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Getter
	@Setter
	@Column
	private String name;

	@Getter
	@Setter
	@Column
	private int age;

	@Getter
	@Setter
	@Column
	private String loginid;

	@Getter
	@Setter
	@Column
	private String password;

	@Getter
	@Setter
	@Column
	private String role;

}
