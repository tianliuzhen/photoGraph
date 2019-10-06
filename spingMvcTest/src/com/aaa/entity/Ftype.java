package com.aaa.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ftype entity. @author MyEclipse Persistence Tools
 */

public class Ftype implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String name;
	private List<Fruit> fruit;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Fruit> getFruit() {
		return fruit;
	}
	public void setFruit(List<Fruit> fruit) {
		this.fruit = fruit;
	}
	public Ftype(Integer tid, String name, List<Fruit> fruit) {
		super();
		this.tid = tid;
		this.name = name;
		this.fruit = fruit;
	}
	public Ftype() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructors



}