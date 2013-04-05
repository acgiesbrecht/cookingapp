/**
 * 
 */
package org.esupportail.cookingapp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.esupportail.cookingapp.domain.beans.Step;
import org.esupportail.cookingapp.domain.beans.StepIngredient;


/**
 * @author llevague
 * 
 */
public interface DomainService extends Serializable {

	/**
	 * Finds the {@link Ingredient} by its name.
	 * @param name
	 * @return
	 */
	Ingredient getIngredient(String name);

	/**
	 * Finds all the {@link Ingredient}.
	 * @return
	 */
	List<Ingredient> getIngredients();

	/**
	 * Add the {@link Ingredient}.
	 * @param ingredient
	 */
	Ingredient addIngredient(Ingredient ingredient);
	
	/**
	 * Delete the {@link Ingredient}.
	 * @param ingredient
	 */
	void deleteIngredient(Ingredient ingredient);
	
	/**
	 * Delete the list of {@link Ingredient}.
	 * @param ingredients
	 */
	void deleteIngredients(final Ingredient... ingredients);
	
	/**
	 * Delete the list of {@link Ingredient}.
	 * @param ingredients
	 */
	void deleteIngredients(final Collection<Ingredient> ingredients);
	

	/**
	 * Finds the {@link Step} by its id.
	 * @param id
	 * @return
	 */
	Step getStep(Long id);

	/**
	 * Add the {@link Step}.
	 * @param step
	 */
	void addStep(Step step);
	
	/**
	 * Delete the {@link Step}.
	 * @param step
	 */
	void deleteStep(Step step);


	/**
	 * Finds the {@link StepIngredient} by its {@link Step}.
	 * @param step
	 * @return
	 */
	StepIngredient getStepIngredient(Step step);
	
	/**
	 * Finds the {@link StepIngredient} by its {@link Ingredient}.
	 * @param ingredient
	 * @return
	 */
	StepIngredient getStepIngredient(Ingredient ingredient);
	
	/**
	 * Finds the {@link StepIngredient} by its {@link Step} and its {@link Ingredient}.
	 * @param step
	 * @param ingredient
	 * @return
	 */
	StepIngredient getStepIngredient(Step step, Ingredient ingredient);


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
