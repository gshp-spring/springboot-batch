package com.sriharilabs.model;

import java.time.LocalDate;
import java.util.List;

public class HospitalityReport {

	private String name;
	//@Id
	private int id;
	private String htype;
	private int total;
	private LocalDate date;
//	String[] listHtype;
//
//	public String[] getListHtype() {
//		return listHtype;
//	}
//
//	public void setListHtype(String[] listHtype) {
//		this.listHtype = listHtype;
//	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
	public String getHtype() {
		return htype;
	}

	public void setHtype(String htype) {
		this.htype = htype;
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
