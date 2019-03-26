package com.sriharilabs.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Hospitality {

	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private LocalDate date;

	private String hospitalityType;

	List<Employee> employeeList;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getHospitalityType() {
		return hospitalityType;
	}

	public void setHospitalityType(String hospitalityType) {
		this.hospitalityType = hospitalityType;
	}

}
