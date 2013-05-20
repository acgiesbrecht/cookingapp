/**
 * 
 */
package org.esupportail.cookingapp.domain.services;

import java.io.Serializable;
import java.util.List;

import org.esupportail.cookingapp.domain.beans.Recipe;


/**
 * @author llevague
 * 
 */
public interface RecipeService extends Serializable {

	/**
	 * Finds the {@link Recipe} by its id.
	 * @param id
	 * @return
	 */
	Recipe getRecipe(Long id);
	
	/**
	 * Finds the list of all {@link Recipe}.
	 * @param name
	 * @return
	 */
	List<Recipe> getRecipes();
	
	/**
	 * Finds the list of {@link Recipe} which name match the parameter.
	 * @param name
	 * @return
	 */
	List<Recipe> getRecipes(String name);
	
	/**
	 * Add the {@link Recipe}.
	 * @param recipe
	 */
	void addRecipe(Recipe recipe);
	
	/**
	 * Delete the {@link Recipe}.
	 * @param recipe
	 */
	void deleteRecipe(Recipe recipe);
}
