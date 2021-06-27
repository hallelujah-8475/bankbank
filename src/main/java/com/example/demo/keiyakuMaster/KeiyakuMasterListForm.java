package com.example.demo.keiyakuMaster;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class KeiyakuMasterListForm implements Serializable {

	@Getter
	@Setter
	private int shoninflg;
}
