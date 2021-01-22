package com.example.demo.systemUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "systemuser")
public class SystemUser {

	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column
	  private long id;

	  @Column
	  private String name;

	  @Column
	  private int age;

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
	  public int getAge() {
	    return age;
	  }
	  public void setAge(int age) {
	    this.age = age;
	  }

}
