package com.sriharilabs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sriharilabs.model.Employee;
import com.sriharilabs.repository.EmployeeRepositoryImpl;

@Service("employeeService")
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	EmployeeRepositoryImpl empRepository;

	// @Autowired
	// HbaseDao hbaseDao;

	public void save(Employee emp) {
		// hbaseDao.insert(emp);
		empRepository.saveEmp(emp);

	}

	public void update(Employee emp) {
		// hbaseDao.insert(emp);
		empRepository.saveEmp(emp);
	}

	public Employee getById(String hdate) {
		// return hbaseDao.findById(empid);
		// return hbaseDao.findById(empid);
		return empRepository.findById(hdate);
	}

	public String remove(String empid) {
		return empRepository.removeById(empid);
	}

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		logger.debug("EmployeeService   ....getAll() ");
		// return hbaseDao.getAll();
		return empRepository.getAll();
	}

}
