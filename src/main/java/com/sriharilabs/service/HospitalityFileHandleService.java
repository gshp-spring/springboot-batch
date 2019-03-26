package com.sriharilabs.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sriharilabs.model.Employee;
import com.sriharilabs.model.HReport;
import com.sriharilabs.model.Hospitality;
import com.sriharilabs.repository.HospitalityFileHandleRepository;

@Service
public class HospitalityFileHandleService {

	@Autowired
	private ExcelUtilityService excelUtilityService;
	@Autowired
	private HospitalityFileHandleRepository hospitalityFileHandleRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public String save(MultipartFile uploadfile,LocalDate ldate,String htype) {
		logger.debug(" HospitalityFileHandleService  save(MultipartFile uploadfile,LocalDate ldate,String htype) ");
		hospitalityFileHandleRepository.save(excelUtilityService.saveHospitalityExcel(uploadfile, ldate,htype));
		return "successfully uploaded";
		
	}
	
	public Hospitality findByDate(LocalDate hdate) {
		logger.debug(" HospitalityFileHandleService ... ");
		return hospitalityFileHandleRepository.findByDate(hdate);
	}
	
	
	public List<HReport> findByBetweenDates(LocalDate fromHdate,LocalDate toDate){
		return hospitalityFileHandleRepository.findByBetweenDates(fromHdate, toDate);
	}
	public Map<String, Map<LocalDate, Long>> getByBetweenDatesHtype(LocalDate fromHdate, LocalDate toDate) {
		return hospitalityFileHandleRepository.getByBetweenDatesHtype(fromHdate, toDate);
	}
	public Map<LocalDate, Map<String, Map<String, Long>>> getDateWiseBasedOnDateRange(LocalDate fromHdate, LocalDate toDate) {
		return hospitalityFileHandleRepository.getDateWiseBasedOnDateRange(fromHdate, toDate);
	}

}
