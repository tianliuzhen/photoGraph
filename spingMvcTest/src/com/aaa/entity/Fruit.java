package com.aaa.entity;

/**
 * Fruit entity. @author MyEclipse Persistence Tools
 */

public class Fruit implements java.io.Serializable {

	// Fields

	private Integer fid;
	private Ftype ftype;
	private String name;
	private String price;
	private Integer status;
	private Integer tid;
	// Constructors
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Ftype getFtype() {
		return ftype;
	}
	public void setFtype(Ftype ftype) {
		this.ftype = ftype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Fruit(Integer fid, Ftype ftype, String name, String price,
			Integer status, Integer tid) {
		super();
		this.fid = fid;
		this.ftype = ftype;
		this.name = name;
		this.price = price;
		this.status = status;
		this.tid = tid;
	}
	public Fruit() {
		super();
		// TODO Auto-generated constructor stub
	}


}