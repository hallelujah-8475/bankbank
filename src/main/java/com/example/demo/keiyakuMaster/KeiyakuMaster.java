package com.example.demo.keiyakuMaster;

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

import org.hibernate.annotations.Type;

import com.example.demo.constant.KeiyakuKbn;
import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.shohinMaster.ShohinMaster;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "keiyakumaster")
public class KeiyakuMaster implements Serializable {
	
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
	private int shohinid;

	@Getter
	@Setter
	@Column
	private int price;

	@Getter
	@Setter
	@Column
	private int kinri;

	@Getter
	@Setter
	@Column
	private String returnlimit;

	@Getter
	@Setter
	@Column
	private int clientid;

	@Getter
	@Setter
	@Column
	private int koinid;

	@Getter
	@Setter
	@Column
	private int shoninflg;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "shohinid", referencedColumnName = "id", insertable = false, updatable = false)
	private ShohinMaster shohinmaster;

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
	
	@Getter
	@Setter
	@Column
	private String ringitext;

	@Getter
	@Setter
	@Column
	private int keiyakukbn;
	
	public String getKeiyakukbnlabel() {

		return KeiyakuKbn.getLabel(this.keiyakukbn);
	}
	
	@Getter
	@Setter
	@Column
	private String shikinshitotext;
	
	@Getter
	@Setter
	@Column
	private String kokatext;
	
	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "koinid", referencedColumnName = "id", insertable = false, updatable = false)
	private KoinMaster koinmaster;
}
