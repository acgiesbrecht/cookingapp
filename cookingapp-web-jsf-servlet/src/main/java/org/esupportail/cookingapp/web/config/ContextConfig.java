package org.esupportail.cookingapp.web.config;

import org.esupportail.commons.context.ApplicationContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("org.esupportail.cookingapp")
@ImportResource({
			"classpath:properties/exceptionHandling/exceptionHandling.xml", 
			"classpath:properties/cache/cache.xml",
			"classpath:properties/smtp/smtp.xml",
			"classpath:properties/web/controllers.xml"})
@Import({I18nConfig.class, ApplicationConfig.class, DomainConfig.class, ControllerConfig.class})
public class ContextConfig {
	
	@Bean(name="app_context")
	public ApplicationContextHolder applicationContextHolder() {
		return new ApplicationContextHolder();
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		final Resource[] resources = new ClassPathResource[] { 
				new ClassPathResource("/properties/defaults.properties"),
				new ClassPathResource("/properties/config.properties")
		};
		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}
	
}
