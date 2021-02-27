package com.example.demo.timecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timecard")
public class TimeCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private int timecardid;

	@Column
	private int koinid;

	@Column
	private String workdate;

	@Column
	private String workstarttime;

	@Column
	private String workendtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getKoinid() {
		return koinid;
	}

	public void setKoinid(int koinid) {
		this.koinid = koinid;
	}

	public int getTimecardid() {
		return timecardid;
	}

	public void setTimecardid(int timecardid) {
		this.timecardid = timecardid;
	}

	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	public String getWorkstarttime() {
		return workstarttime;
	}

	public void setWorkstarttime(String workstarttime) {
		this.workstarttime = workstarttime;
	}

	public String getWorkendtime() {
		return workendtime;
	}

	public void setWorkendtime(String workendtime) {
		this.workendtime = workendtime;
	}

}
