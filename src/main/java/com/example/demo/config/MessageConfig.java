package com.example.demo.config;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig extends WebMvcAutoConfiguration {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(this.getBasenames());
		messageSource.setCacheSeconds(0);
		messageSource.setDefaultEncoding("utf-8");

		return messageSource;
	}

	private String[] getBasenames() {
		var list = new ArrayList<String>();

		list.add("classpath:ValidationMessages");
		list.add("classpath:messages/systemUser");
		list.add("classpath:messages/clientMaster");

		return list.toArray(new String[list.size()]);
	}

}
