package com.example.demo.timecard;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.example.demo.koinMaster.KoinMaster;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TimeCardForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Getter
	@Setter
	private int koinid;

	@Getter
	@Setter
	private String workdate;

	@Getter
	@Setter
	private String workstarttime;

	@Getter
	@Setter
	private String workendtime;

	@Getter
	@Setter
	@JoinColumn(name = "koinid", referencedColumnName = "id", insertable = false, updatable = false)
	private KoinMaster koinmaster;
}
