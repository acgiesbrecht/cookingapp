package org.esupportail.cookingapp.web.config;

import org.esupportail.cookingapp.web.controllers.IngredientController;
import org.esupportail.cookingapp.web.controllers.RecipeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
@Import({DomainConfig.class})
public class ControllerConfig {

	@Bean
	@Scope("request")
	public IngredientController ingredientController() {
		return new IngredientController();
	}
	
	@Bean
	@Scope("request")
	public RecipeController recipeController() {
		return new RecipeController();
	}
}
