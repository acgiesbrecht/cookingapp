package org.esupportail.cookingapp.web.config;

import org.esupportail.commons.services.application.ApplicationService;
import org.esupportail.commons.services.application.SimpleApplicationServiceImpl;
import org.esupportail.commons.services.application.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
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
	
	@Bean
	public Version applicationVersion() {
		return applicationService().getVersion();
	}
}
