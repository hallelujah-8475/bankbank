package com.example.demo.systemUser;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.koinMaster.KoinMaster;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "systemuser")
public class SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Getter
	@Setter
	@Column
	private String loginid;

	@Getter
	@Setter
	@Column
	private int koinid;

	@Getter
	@Setter
	@Column
	private String password;

	@Getter
	@Setter
	@Column
	private String role;
	
	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "koinid", referencedColumnName = "id", insertable = false, updatable = false)
	private KoinMaster koinmaster;
}
