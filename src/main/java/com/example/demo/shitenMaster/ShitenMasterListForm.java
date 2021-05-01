package com.example.demo.shitenMaster;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ShitenMasterListForm implements Serializable {

	@Getter
	@Setter
	private String shitenname;

	@Getter
	@Setter
	private String prefecture;
}
