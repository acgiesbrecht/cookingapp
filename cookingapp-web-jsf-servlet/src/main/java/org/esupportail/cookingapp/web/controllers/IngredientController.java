/**
 * 
 */
package org.esupportail.cookingapp.web.controllers;

import static fj.data.Array.array;
import static fj.data.Option.fromNull;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.INGREDIENTS_LIST;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.INGREDIENT_ADD;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.REDIRECT;
import static org.springframework.util.StringUtils.hasText;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.services.IngredientService;
import org.esupportail.cookingapp.web.utils.JsfMessagesUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.push.PushContextFactory;

/**
 * @author llevague
 *
 */
public class IngredientController {

	/**
	 * The service.
	 */
	@Inject
	private IngredientService ingredientService;
	
	/**
	 * The jsf utilities.
	 */
	@Inject
	private JsfMessagesUtils jsfMessagesUtils;
	
	/**
	 * The {@link Ingredient} list.
	 */
	@Inject
	private LazyDataModel<Ingredient> ingredients;
		
	/**
	 * The selected {@link Ingredient} list.
	 */
	private Ingredient[] selectedIngredients;

	/**
	 * A new {@link Ingredient} to add.
	 */
	private Ingredient newIngredient;
	
	@PostConstruct
	public void init() {
		selectedIngredients = new Ingredient[0];
		newIngredient = new Ingredient();
	}
	
	/**
	 * 
	 */
	public synchronized void pushIngredients() {
		PushContextFactory.getDefault().getPushContext()
				.push("/ingredients", ingredients);
	}
	
	/**
	 * @return the addable
	 */
	public boolean checkIfAddable() {
		final boolean addable = hasText(newIngredient.getName())
			&& fromNull(ingredientService.getIngredient(newIngredient.getName().trim())).isNone();
		return addable;
	}
	
	/**
	 * Add an {@link Ingredient}.
	 * @return
	 */
	public String addIngredient() {
		if (checkIfAddable()) {
			ingredientService.addIngredient(newIngredient);
			pushIngredients();
			jsfMessagesUtils.addInfoMessage(null, "INFO.INGREDIENT.ADD", null, newIngredient.getName());
			return goList();
		}
		jsfMessagesUtils.addErrorMessage(null, "ERROR.INGREDIENT.ADD", null);
		return null;
	}

	
	/**
	 * Delete selected {@link Ingredient}.
	 * @return
	 */
	public String deleteIngredients() {
		if (array(selectedIngredients).isNotEmpty()) {
			ingredientService.deleteIngredients(selectedIngredients);
			pushIngredients();
			jsfMessagesUtils.addInfoMessage(null, "INFO.INGREDIENT.DELETE", null);
		}
		return goList();
	}
	
	/**
	 * Adding callback.
	 * @return
	 */
	public String goAdd() {
		return INGREDIENT_ADD + REDIRECT;
	}

	/**
	 * Listing callback.
	 * @return
	 */
	public String goList() {
		return INGREDIENTS_LIST + REDIRECT;
	}
	
	/**
	 * @return the newIngredient
	 */
	public Ingredient getNewIngredient() {
		return newIngredient;
	}

	/**
	 * @param newIngredient the newIngredient to set
	 */
	public void setNewIngredient(final Ingredient newIngredient) {
		this.newIngredient = newIngredient;
	}

	/**
	 * @return the ingredients
	 */
	public LazyDataModel<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @return the selectedIngredients
	 */
	public Ingredient[] getSelectedIngredients() {
		return selectedIngredients;
	}

	/**
	 * @param selectedIngredients the selectedIngredients to set
	 */
	public void setSelectedIngredients(final Ingredient[] selectedIngredients) {
		this.selectedIngredients = selectedIngredients;
	}
}
