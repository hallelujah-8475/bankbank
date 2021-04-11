package com.example.demo.accesslog;

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
@Table(name = "accesslog")
public class AccessLog implements Serializable {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Getter
	@Setter
	@Column
	private String actsystemuserid;

	@Getter
	@Setter
	@Column
	private String actdatetime;

	@Getter
	@Setter
	@Column
	private String actcontent;

	@Getter
	@Setter
	@Column
	private String actresult;
}
