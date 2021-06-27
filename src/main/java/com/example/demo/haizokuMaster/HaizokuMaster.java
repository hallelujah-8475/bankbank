package com.example.demo.haizokuMaster;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class HaizokuMaster implements Serializable {

	@Getter
	@Setter
	private String koinname;

	@Getter
	@Setter
	private int age;

	@Getter
	@Setter
	private int shitenid;

}
