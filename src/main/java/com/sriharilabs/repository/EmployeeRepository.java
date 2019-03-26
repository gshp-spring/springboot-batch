package com.sriharilabs.repository;

import com.sriharilabs.model.Employee;

public interface EmployeeRepository  {

    public Employee findByName(String firstName);
   // public List<Employee> findByLastName(String lastName);

	public Employee findById(String username);
	public String removeById(String id);

}
