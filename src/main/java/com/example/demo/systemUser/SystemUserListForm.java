package com.example.demo.systemUser;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SystemUserListForm implements Serializable {

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String role;
}
