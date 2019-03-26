package com.sriharilabs.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sriharilabs.model.HReport;
import com.sriharilabs.model.Hospitality;
import com.sriharilabs.model.HospitalityReport;

@Repository
public class HospitalityFileHandleRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void save(Hospitality saveHospitalityExcel) {
		try {
		mongoTemplate.save(saveHospitalityExcel);
		}catch(Exception exp) {
			System.out.println("EXP :: "+exp);
		}
	}

	public Hospitality findByDate(LocalDate hdate) {
		logger.debug("HospitalityFileHandleRepository.." + hdate);
		Hospitality user = mongoTemplate.findOne(query(where("date").is(hdate)), Hospitality.class);
		user.getEmployeeList().forEach(us -> {
			logger.debug(us.getName() + "    ::  " + us.getId());
		});
		return user;
	}
public Map<String, Map<LocalDate, Long>> getByBetweenDatesHtype(LocalDate fromHdate, LocalDate toDate) {
	List<HReport> hr= findByBetweenDates(fromHdate,toDate);
	Map<String, Map<LocalDate, Long>> tot=hr.stream()
			.collect(Collectors.groupingBy(HReport::getHtype,
					Collectors.groupingBy(HReport::getDate,Collectors.counting())));
	
	return tot;
}
	

public Map<LocalDate, Map<String, Map<String, Long>>> getDateWiseBasedOnDateRange(LocalDate fromHdate, LocalDate toDate) {
	List<HReport> hr= findByBetweenDates(fromHdate,toDate);
	Map<LocalDate, Map<String, Map<String, Long>>> datSingleTot=hr.stream()
																  .collect(Collectors.groupingBy(HReport::getDate,Collectors.groupingBy(HReport::getHtype,
					                                                 Collectors.groupingBy(HReport::getName,Collectors.counting()))));
	
	datSingleTot.keySet().forEach(l->{
		
		System.out.println(l.getDayOfMonth());
		Map map=datSingleTot.get(fromHdate);
		map.entrySet().forEach(s->System.out.println(" DATA  ::::::::::   "+s));
	});
	return datSingleTot;
}

	public List<HReport> findByBetweenDates(LocalDate fromHdate, LocalDate toDate) {
		logger.debug("HospitalityFileHandleRepository.." + fromHdate);
		Query query = new Query();
		query.addCriteria(Criteria.where("date").gte(fromHdate).lt(toDate));
		List<Hospitality> list = mongoTemplate.find(query, Hospitality.class);
		List<HReport> hreport=list.stream().flatMap(h->h.getEmployeeList().stream()
																	.map(u->new  HReport((String)h.getHospitalityType(),
																			(LocalDate)h.getDate(),
																			(String)u.getName(),
																			(int)u.getId())
																		)
												).collect(Collectors.toList());
		
		hreport.forEach(p->{
			logger.debug(p.getName()+ "      ::    "+p.getId()+"         ::   " + p.getHtype() +"         ::   " + p.getDate());});


		return hreport;

	}

}
