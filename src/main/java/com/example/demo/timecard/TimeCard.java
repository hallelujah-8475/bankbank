package com.example.demo.timecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.koinMaster.KoinMaster;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "timecard")
public class TimeCard {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Getter
	@Setter
	@Column
	private int timecardid;

	@Getter
	@Setter
	@Column
	private int koinid;

	@Getter
	@Setter
	@Column
	private String workdate;

	@Getter
	@Setter
	@Column
	private String workstarttime;

	@Getter
	@Setter
	@Column
	private String workendtime;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "koinid", referencedColumnName = "id", insertable = false, updatable = false)
	private KoinMaster koinmaster;
}
