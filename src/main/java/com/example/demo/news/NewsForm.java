package com.example.demo.news;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class NewsForm implements Serializable {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@NotBlank
	private String title;

	@Getter
	@Setter
	@NotBlank
	private String content;

	@Getter
	@Setter
	@NotBlank
	private String kokaistartdate;

	@Getter
	@Setter
	private String kokaienddate;

	@Getter
	@Setter
	private int shitenid;

	@Getter
	@Setter
	private int kokaiflg;


}
