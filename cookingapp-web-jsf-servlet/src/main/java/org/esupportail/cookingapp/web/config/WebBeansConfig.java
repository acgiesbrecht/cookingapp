package org.esupportail.cookingapp.web.config;

import javax.inject.Inject;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.config.DomainConfig;
import org.esupportail.cookingapp.domain.services.IngredientService;
import org.esupportail.cookingapp.web.beans.IngredientLazyDataModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
@Import(DomainConfig.class)
public class WebBeansConfig {
	
	@Inject
	private IngredientService ingredientService;

	@Bean
	@Scope("view")
	public LazyDataModel<Ingredient> ingredientLDM() {
		return new IngredientLazyDataModel(ingredientService);
	}
}
