package com.sriharilabs.model;

import java.time.LocalDate;

public class HReport {
	String htype;
	LocalDate date;
	private String name;
	private int id;
	public String getHtype() {
		return htype;
	}
	public HReport(String htype, LocalDate date, String name, int id) {
		super();
		this.htype = htype;
		this.date = date;
		this.name = name;
		this.id = id;
	}
	public void setHtype(String htype) {
		this.htype = htype;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
