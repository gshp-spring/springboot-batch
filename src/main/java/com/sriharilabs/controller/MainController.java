package com.sriharilabs.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sriharilabs.model.Employee;
import com.sriharilabs.service.EmployeeService;

@Controller
public class MainController {

	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	 @Autowired
	    private EmployeeService empService;
	 	private static final String template = "Hello, %s!";
	    private final AtomicLong counter = new AtomicLong();
	@RequestMapping(value="/")
    public String index() {
  
    	return "index.html";
                       
    }
	
	//http://localhost:8080/spring-rest/ex/foos/1
	@RequestMapping(value = "/gs/gshp/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getFoosBySimplePathWithPathVariable(
	  @PathVariable String id) {
	    return "Get a specific Foo with id=" + id;
	}
	
	//@PathVariable  obtain some placeholder from uri
	// @RequestParam is obtain from a param
	//@RequestMapping with Request parameters   :   @RestController not appropriate  u need ResponseEntity<?>
	//Note :  dont need @ResponseBody and @RequestBody for @RestController
	
	/**
	 * 
	 * 
	 * Spring Framework 4.3 introduced a few new HTTP mapping annotations, all based on @RequestMapping:

        @GetMapping
        @PostMapping
		@PutMapping
		@DeleteMapping
		@PatchMapping
	 * @param emp
	 * @return
	 */
	 //Spring deserialize json to an object
	   @RequestMapping(value="/save6",method = RequestMethod.POST)	    
	    public String save1(@RequestBody Employee emp) {
	    	
	    		empService.save(emp);
	        return "{\"msg\":\"Successfully submitted\"}";               
	    }
	    
	    @RequestMapping(value="/save7",method = RequestMethod.POST)	    
	    public String save2(@ModelAttribute Employee emp) {
	    	
	    		empService.save(emp);
	        return "{\"msg\":\"Successfully submitted\"}";               
	    }
	    
	    //By default it is equal to @ModelAttribute Employee emp
	    @RequestMapping(value="/save5",method = RequestMethod.POST)	    
	    public String save( Employee emp) {
	    	
	    		empService.save(emp);
	        return "{\"msg\":\"Successfully submitted\"}";               
	    }
}
