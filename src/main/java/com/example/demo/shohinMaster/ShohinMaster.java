package com.example.demo.shohinMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "shohinmaster")
public class ShohinMaster {

	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column
	  private long id;

	  @Column
	  private int shohinid;

	  @Column
	  private String name;

	  public long getId() {
	    return id;
	  }
	  public void setId(long id) {
	    this.id = id;
	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	public int getShohinid() {
		return shohinid;
	}
	public void setShohinid(int shohinid) {
		this.shohinid = shohinid;
	}


}
