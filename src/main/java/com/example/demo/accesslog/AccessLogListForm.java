package com.example.demo.accesslog;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class AccessLogListForm implements Serializable {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private long actsystemuserid;

	@Getter
	@Setter
	private String actdatetime;

	@Getter
	@Setter
	private String actcontent;

	@Getter
	@Setter
	private String actresult;

}
