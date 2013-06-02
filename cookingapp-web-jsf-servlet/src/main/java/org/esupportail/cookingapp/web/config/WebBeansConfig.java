package org.esupportail.cookingapp.web.config;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.web.beans.IngredientLazyDataModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
public class WebBeansConfig {
	@Bean
	@Scope("prototype")
	public LazyDataModel<Ingredient> ingredientLDM() {
		return new IngredientLazyDataModel();
	}
}
