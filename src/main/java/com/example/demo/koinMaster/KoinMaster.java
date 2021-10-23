package com.example.demo.koinMaster;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import com.example.demo.constant.BushoKbn;
import com.example.demo.constant.Yakushoku;
import com.example.demo.shitenMaster.ShitenMaster;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "koinmaster")
public class KoinMaster implements Serializable {

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
	@Column
	private String nyushastartdate;

	@Getter
	@Setter
	@Column
	private String haizokustartdate;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "shitenid", referencedColumnName = "id", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	private ShitenMaster shitenmaster;

	public String getBushoname() {

		return BushoKbn.getLabel(this.busho);
	}

	@Getter
	@Setter
	@Column
	private int tenkinflg;

	@Getter
	@Setter
	@Column
	private int yakushoku;

	public String getYakushokuname() {

		return Yakushoku.getLabel(this.yakushoku);
	}
	
	@Getter
	@Setter
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name="filedata")
	private byte[] filedata;

	@Getter
	@Setter
	@Column
	private String filename;
}
