package com.example.demo.shohinMaster;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ShohinMasterListForm implements Serializable {

	@Getter
	@Setter
	private String name;
}
