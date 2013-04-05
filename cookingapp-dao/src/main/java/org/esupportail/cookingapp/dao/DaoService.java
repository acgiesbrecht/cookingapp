/**
 * 
 */
package org.esupportail.cookingapp.dao;

import java.util.List;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.esupportail.cookingapp.domain.beans.Step;
import org.esupportail.cookingapp.domain.beans.StepIngredient;

/**
 * @author llevague
 *
 */
public interface DaoService {


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
	 * Finds the {@link Step} by its id.
	 * @param id
	 * @return
	 */
	Step getStep(Long id);

	
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
	 * Add the object.
	 * @param object
	 */
	<T> void add(T object);
	
	/**
	 * Delete the object.
	 * @param object
	 */
	<T> void delete(T object);
}
