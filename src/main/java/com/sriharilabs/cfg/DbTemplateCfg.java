package com.sriharilabs.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mongodb.MongoClient;

//@Configuration
public class DbTemplateCfg {
	private static final Logger logger = LoggerFactory.getLogger(DbTemplateCfg.class);
	
	@Value("${spring.data.mongodb.host}")
	private String host;//="localhost";
	
	@Value("${spring.data.mongodb.database}")
	private String database;//="poc";
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		logger.debug("DbTemplateCfg  database configuration");
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(host), database);
		return mongoTemplate;

	}
	
	
	     
}
