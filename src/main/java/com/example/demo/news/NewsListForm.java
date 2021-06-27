package com.example.demo.news;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class NewsListForm implements Serializable {

	@Getter
	@Setter
	private String kokaistartdate;

	@Getter
	@Setter
	private String kokaienddate;
}
