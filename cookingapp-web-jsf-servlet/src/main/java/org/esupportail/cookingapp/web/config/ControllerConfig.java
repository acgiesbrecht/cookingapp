package org.esupportail.cookingapp.web.config;

import org.esupportail.cookingapp.web.controllers.IngredientController;
import org.esupportail.cookingapp.web.controllers.RecipeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
public class ControllerConfig {
		
	@Bean
	@Scope("view")
	public IngredientController ingredientController() {
		return new IngredientController();
	}
	
	@Bean
	@Scope("view")
	public RecipeController recipeController() {
		return new RecipeController();
	}
}
