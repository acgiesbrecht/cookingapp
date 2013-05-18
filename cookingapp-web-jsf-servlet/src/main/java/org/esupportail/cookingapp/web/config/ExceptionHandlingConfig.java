package org.esupportail.cookingapp.web.config;

import static org.esupportail.cookingapp.web.rewrite.NavigationRules.NOTFOUND;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.REDIRECT;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.esupportail.commons.services.application.ApplicationService;
import org.esupportail.commons.services.i18n.I18nService;
import org.esupportail.commons.services.smtp.SmtpService;
import org.esupportail.cookingapp.domain.config.ApplicationConfig;
import org.esupportail.cookingapp.domain.config.CacheConfig;
import org.esupportail.cookingapp.domain.config.I18nConfig;
import org.esupportail.cookingapp.domain.config.SmtpConfig;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@Import({I18nConfig.class, ApplicationConfig.class, SmtpConfig.class, CacheConfig.class})
public class ExceptionHandlingConfig {

	@Inject
	private ApplicationService applicationService;
	
	@Inject
	private I18nService i18nService;
	
	@Inject
	private SmtpService smtpService;
	
	@Inject
	private EhCacheManagerFactoryBean cacheManager;
	
	@SuppressWarnings("serial")
	private Map<String, String> exceptionViews = new HashMap<String, String>() {{
		put("java.lang.Exception", NOTFOUND + REDIRECT);
		put("java.lang.IllegalArgumentException", NOTFOUND + REDIRECT);
	}};
	
//FIXME
//	@Bean
//	public ExceptionServiceFactory exceptionServiceFactory() {
//		CachingEmailExceptionServiceFactoryImpl factory = new CachingEmailExceptionServiceFactoryImpl();
//		factory.setApplicationService(applicationService);
//		factory.setI18nService(i18nService);
//		factory.setSmtpService(smtpService);
//		factory.setCacheManager(cacheManager.getObject());
//		factory.setCacheName("");
//		factory.setLogLevel(LogLevel.INFO.getLabel());
//		factory.setExceptionViews(exceptionViews);
//		return factory;
//	}
	
}
