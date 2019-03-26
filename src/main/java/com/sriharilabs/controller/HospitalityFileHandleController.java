package com.sriharilabs.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sriharilabs.model.Employee;
import com.sriharilabs.model.HReport;
import com.sriharilabs.model.Hospitality;
import com.sriharilabs.service.EmployeeService;
import com.sriharilabs.service.HospitalityFileHandleService;

@RestController
public class HospitalityFileHandleController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EmployeeService empService;
	@Autowired
	HospitalityFileHandleService hospitalityFileHandleService;

	@GetMapping("/getById12")
	public @ResponseBody Employee getById11(@RequestParam(value = "eid", defaultValue = "World") String eid) {
		logger.debug("EmpController   getById :: " + eid);
		return empService.getById(eid);

	}
	
	@GetMapping("/reportBetweenDates")
	@ResponseBody
	public List<HReport> getDataBetweenDates(@RequestParam("fromdate") String fromdate,
			@RequestParam("todate") String todate) {
		logger.debug("fromdate : "+ fromdate + " todate:: "+todate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDateOflocalDate = LocalDate.parse(fromdate, formatter);
		LocalDate toDateOflocalDate = LocalDate.parse(todate, formatter);
		
		List<HReport> report=hospitalityFileHandleService.findByBetweenDates(fromDateOflocalDate, toDateOflocalDate);
		report.forEach(s->{
			System.out.println(" id :"+ s.getId()+"        name: "+s.getName());
		});
		
		return report;
	}
	@GetMapping("/getByHtypeBasedOnDateRange")
	@ResponseBody
	public Map<String, Map<LocalDate, Long>> getByHtypeBasedOnDateRange(@RequestParam("fromdate") String fromdate,
			@RequestParam("todate") String todate) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDateOflocalDate = LocalDate.parse(fromdate, formatter);
		LocalDate toDateOflocalDate = LocalDate.parse(todate, formatter);
		return hospitalityFileHandleService.getByBetweenDatesHtype(fromDateOflocalDate, toDateOflocalDate);
	}
	
	@GetMapping("/getDateWiseBasedOnDateRange")
	@ResponseBody
	public Map<LocalDate, Map<String, Map<String, Long>>> getDateWiseBasedOnDateRange(@RequestParam("fromdate") String fromdate,
			@RequestParam("todate") String todate) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDateOflocalDate = LocalDate.parse(fromdate, formatter);
		LocalDate toDateOflocalDate = LocalDate.parse(todate, formatter);
		return hospitalityFileHandleService.getDateWiseBasedOnDateRange(fromDateOflocalDate, toDateOflocalDate);
	}
	
	
	@GetMapping("/getById")
	public @ResponseBody Hospitality getById(@RequestParam("hdate") String hdate) throws ParseException {
		logger.debug("HospitalityFileHandleController   getById :: " + hdate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(hdate, formatter);
		logger.debug("HospitalityFileHandleController day of the week ::" + localDate.getDayOfWeek());
		return hospitalityFileHandleService.findByDate(localDate);

	}

	@PostMapping(value = "/save")
	public String save(@RequestBody Employee emp) {
		logger.debug("save ..   " + emp.getName());
		empService.save(emp);
		return "{\"msg\":\"Successfully submitted\"}";
	}

	@GetMapping("/removeById")
	public String removeById(@RequestParam(value = "eid", defaultValue = "World") String eid) {

		return empService.remove(eid);

	}

	@PostMapping(value = "/fileUpload")
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("hdate") String hdate,@RequestParam("htype") String sheetName) {
		
		if (uploadfile.isEmpty()) {
			return new ResponseEntity<String>("please select a file!", HttpStatus.OK);
		}

		try {
			logger.debug("  tying to upload excel " + hdate);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(hdate, formatter);

			logger.debug("day of the week ::" + localDate.getDayOfWeek());

			hospitalityFileHandleService.save(uploadfile, localDate,sheetName);
			logger.debug("Single file upload!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Successfully uploaded - " + uploadfile.getOriginalFilename(),
				new HttpHeaders(), HttpStatus.OK);

	}
}
