package org.esupportail.cookingapp.web.config;

import org.esupportail.commons.services.application.ApplicationService;
import org.esupportail.commons.services.application.SimpleApplicationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	public static final String APP_NAME = "cookingapp";
	public static final int MAJOR_VERSION_NUMBER = 0;
	public static final int MINOR_VERSION_NUMBER = 0;
	public static final int UPDATE_VERSION_NUMBER = 0;
	
	@Bean
	public ApplicationService applicationService() {
		SimpleApplicationServiceImpl service = new SimpleApplicationServiceImpl();
		service.setName(APP_NAME);
		service.setVersionMajorNumber(MAJOR_VERSION_NUMBER);
		service.setVersionMinorNumber(MINOR_VERSION_NUMBER);
		service.setVersionUpdate(UPDATE_VERSION_NUMBER);
		return service;
	}
}
