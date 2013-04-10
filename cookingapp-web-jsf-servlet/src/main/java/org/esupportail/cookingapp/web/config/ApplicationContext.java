package org.esupportail.cookingapp.web.config;

import javax.inject.Inject;

import org.esupportail.commons.context.ApplicationContextHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackageClasses = ApplicationContext.class)
@PropertySource("classpath:/properties/defaults.properties,classpath:/properties/config.properties")
@ImportResource("classpath*:META-INF/cookingapp-dao-dao.xml, " +
		"classpath*:META-INF/cookingapp-domain-services-auth.xml, " +
		"classpath*:META-INF/cookingapp-domain-services-domain.xml, " +
		"classpath*:META-INF/cookingapp-domain-services-application.xml, " +
		"classpath*:META-INF/cookingapp-domain-services-i18n.xml, " +
		"exceptionHandling/exceptionHandling.xml, " +
		"cache/cache.xml, " +
		"smtp/smtp.xml, " +
		"/controllers.xml, " +
		"web/converters.xml")
public class ApplicationContext {
	
	@Inject
	Environment env;
	
	public ApplicationContextHolder app_context() {
		return new ApplicationContextHolder();
	}
	
}
