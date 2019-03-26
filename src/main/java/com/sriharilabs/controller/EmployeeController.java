package com.sriharilabs.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sriharilabs.model.Employee;
import com.sriharilabs.service.EmployeeService;

@RestController
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(HospitalityFileHandleController.class);
	
	 @Autowired
	    private EmployeeService empService;
	 	private static final String template = "Hello, %s!";
	    private final AtomicLong counter = new AtomicLong();

	    /***
	     * @RequestMapping("/getById")
	    public @ResponseBody Employee getById(@RequestParam(value="eid", defaultValue="World") String eid) {
	    	
	    	return	empService.getById(eid);
	                       
	    }
	    **/
	   
	    
	    
	   /*** 
	    @RequestMapping("/removeById")
	    public String removeById(@RequestParam(value="eid", defaultValue="World") String eid) {
	  
	    	return empService.remove(eid);
	                       
	    }
	    **/
	    //Spring deserialize json to an object
	   @RequestMapping(value="/save1",method = RequestMethod.POST)	    
	    public String save1(Employee emp) {
		   System.out.println("........");
		   logger.debug("  EmployeeController  save1.....");
	    		empService.save(emp);
	        return "{\"msg\":\"Successfully submitted\"}";               
	    }
	    
	    @RequestMapping(value="/save2",method = RequestMethod.POST)	    
	    public String save2(@ModelAttribute Employee emp) {
	    	logger.debug("  EmployeeController  save2.....");
	    		empService.save(emp);
	        return "{\"msg\":\"Successfully submitted\"}";               
	    }
	    
	    //By default it is equal to @ModelAttribute Employee emp
	   /** @RequestMapping(value="/save",method = RequestMethod.POST)	    
	    public String save(@RequestBody Employee emp) {
	    	
	    		empService.save(emp);
	        return "{\"msg\":\"Successfully submitted\"}";               
	    }
	    ***/
	  
	    
	    @RequestMapping(value="/update",method = RequestMethod.PUT)	    
	    public String update(@RequestBody Employee emp) {
	    	logger.debug("  EmployeeController  update.....");
	    		empService.update(emp);
	        return "Successfully updated";               
	    }
	    @RequestMapping(value="/getAll",method = RequestMethod.GET)	    
	    public List<Employee> getAll() {
	    	
	     	logger.debug("  EmployeeController  getAll.....");
	        return empService.getAll();               
	    }
	    
	    @RequestMapping(value="/saveGet",method = RequestMethod.GET)	    
	    public String saveGet(@RequestParam(value="eid", defaultValue="World") String eid,
	    		@RequestParam(value="name", defaultValue="default") String name,
	    		@RequestParam(value="age", defaultValue="23") String age,
	    		@RequestParam(value="position", defaultValue="position") String position,
	    		@RequestParam(value="exp", defaultValue="exp") String exp) {
	    		Employee emp1 = null;// = new	Employee(eid, name,  position,  exp,  age);
	    		empService.save(emp1);
	        return "Successfully submitted";               
	    }
}
