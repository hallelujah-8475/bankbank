package com.example.demo.koinMaster;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.shitenMaster.ShitenMaster;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "koinmaster")
public class KoinMaster implements Serializable {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Getter
	@Setter
	@Column
	private int koinid;

	@Getter
	@Setter
	@Column
	private String koinname;

	@Getter
	@Setter
	@Column
	private int age;

	@Getter
	@Setter
	@Column
	private int shitenid;

	@Getter
	@Setter
	@Column
	private int busho;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "shitenid", referencedColumnName = "shitenid", insertable = false, updatable = false)
	private ShitenMaster shitenmaster;

}
