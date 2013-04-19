package org.esupportail.cookingapp.web.config;

import javax.inject.Inject;

import org.esupportail.commons.services.application.ApplicationService;
import org.esupportail.commons.services.i18n.I18nService;
import org.esupportail.cookingapp.web.controllers.IngredientController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import({I18nConfig.class, ApplicationConfig.class, DomainConfig.class})
public class ControllerConfig {
	
	@Inject
	private I18nService i18nService;
	
	@Inject
	private ApplicationService applicationService;
	
	@Bean
	@Scope("request")
	public IngredientController ingredientController() {
		IngredientController c = new IngredientController();
		c.setApplicationService(applicationService);
		c.setI18nService(i18nService);
		return c;
	}
}
