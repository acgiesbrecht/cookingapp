/**
 * 
 */
package org.esupportail.cookingapp.domain.services;

import java.io.Serializable;
import java.util.List;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.springframework.data.domain.Page;


/**
 * @author llevague
 * 
 */
public interface IngredientService extends Serializable {

	/**
	 * Finds the {@link Ingredient} by its name.
	 * @param name
	 * @return
	 */
	Ingredient getIngredient(String name);

	/**
	 * Finds all the {@link Ingredient}s.
	 * @return
	 */
	List<Ingredient> getIngredients();
	
	/**
	 * Finds all the {@link Ingredient}s.
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Ingredient> getIngredients(int page, int size);

	/**
	 * Finds all the {@link Ingredient}s starting with the string parameter.
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Ingredient> getIngredientsStartingWith(final String name, int page, int size);

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
	void deleteIngredients(final Iterable<Ingredient> ingredients);
}
