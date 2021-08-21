package com.example.demo.accesslog;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class AccessLogListForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String actsystemuserid;

	@Getter
	@Setter
	private String actdatetimeFrom;

	@Getter
	@Setter
	private String actdatetimeTo;

	@Getter
	@Setter
	private String actcontent;

	@Getter
	@Setter
	private String actresult;

}
