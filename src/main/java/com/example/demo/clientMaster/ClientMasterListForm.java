package com.example.demo.clientMaster;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ClientMasterListForm implements Serializable {

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String prefecture;
}
