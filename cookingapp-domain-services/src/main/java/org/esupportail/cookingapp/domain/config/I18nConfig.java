package org.esupportail.cookingapp.domain.config;

import org.esupportail.commons.services.i18n.I18nService;
import org.esupportail.commons.services.i18n.ResourceBundleMessageSourceI18nServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@Lazy
public class I18nConfig {
	
	public static final int CACHE_SECONDS = 60;
	
	@Bean
	public MessageSource msgs() {
		ReloadableResourceBundleMessageSource messages = 
				new ReloadableResourceBundleMessageSource();
		messages.setBasenames("classpath:properties/i18n/bundles/Custom",
								"classpath:properties/i18n/bundles/Messages",
								"classpath:properties/i18n/bundles/Commons",
								"classpath:META-INF/i18n/bundles/Custom",
								"classpath:META-INF/i18n/bundles/Messages",
								"classpath:META-INF/i18n/bundles/Commons");
		messages.setCacheSeconds(CACHE_SECONDS);
		return messages;
	}
	
	@Bean
	public I18nService i18nService() {
		ResourceBundleMessageSourceI18nServiceImpl service = new ResourceBundleMessageSourceI18nServiceImpl();
		service.setMessageSource(msgs());
		return service;
	}
}
