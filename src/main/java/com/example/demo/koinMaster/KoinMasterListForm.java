package com.example.demo.koinMaster;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class KoinMasterListForm implements Serializable {

	@Getter
	@Setter
	private String koinname;

	@Getter
	@Setter
	private int shitenid;
}
