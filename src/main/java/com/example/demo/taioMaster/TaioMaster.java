
package com.example.demo.taioMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.koinMaster.KoinMaster;
import com.example.demo.shitenMaster.ShitenMaster;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "taiomaster")
public class TaioMaster {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;

	@Getter
	@Setter
	@Column
	private int taioid;

	@Getter
	@Setter
	@Column
	private String taiostartdate;

	@Getter
	@Setter
	@Column
	private String taioenddate;

	@Getter
	@Setter
	@Column
	private int clientid;

	@Getter
	@Setter
	@Column
	private int shitenid;

	@Getter
	@Setter
	@Column
	private int koinid;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "shitenid", referencedColumnName = "shitenid", insertable = false, updatable = false)
	private ShitenMaster shitenmaster;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "koinid", referencedColumnName = "koinid", insertable = false, updatable = false)
	private KoinMaster koinmaster;
}
