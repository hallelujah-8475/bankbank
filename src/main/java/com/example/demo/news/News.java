package com.example.demo.news;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
public class News implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Getter
	@Setter
	@Column
	private String title;

	@Getter
	@Setter
	@Column
	private String content;

	@Getter
	@Setter
	@Column
	private String kokaistartdate;

	@Getter
	@Setter
	@Column
	private String kokaienddate;

	@Getter
	@Setter
	@Column
	private int shitenid;

	@Getter
	@Setter
	@Column
	private int kokaiflg;
}
