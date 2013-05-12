/**
 * 
 */
package org.esupportail.cookingapp.web.controllers;

import static fj.data.Array.array;
import static fj.data.IterableW.wrap;
import static fj.data.List.iterableList;
import static fj.data.Option.fromNull;
import static fj.data.Option.fromString;
import static org.esupportail.cookingapp.utils.SortUtils.ingredientOrd;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.INGREDIENTS_LIST;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.INGREDIENT_ADD;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.REDIRECT;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.services.DomainService;
import org.esupportail.cookingapp.web.utils.JsfMessagesUtils;
import org.primefaces.push.PushContextFactory;

import fj.F;

/**
 * @author llevague
 *
 */
public class IngredientController {

	/**
	 * The service.
	 */
	@Inject
	private DomainService domainService;
	
	/**
	 * The jsf utilities.
	 */
	@Inject
	private JsfMessagesUtils jsfMessagesUtils;
	
	/**
	 * The {@link Ingredient} list.
	 */
	private List<Ingredient> ingredients;
		
	/**
	 * The selected {@link Ingredient} list.
	 */
	private Ingredient[] selectedIngredients;

	/**
	 * A new {@link Ingredient} to add.
	 */
	private Ingredient newIngredient;
	
	/**
	 * A filtered value.
	 */
	private String filter;
	
	@PostConstruct
	public void init() {
		ingredients = domainService.getIngredients();
		selectedIngredients = new Ingredient[0];
		newIngredient = new Ingredient();
		filter = new String();
	}
	
	/**
	 * 
	 */
	public synchronized void pushIngredients() {
		PushContextFactory.getDefault().getPushContext()
				.push("/ingredients", ingredients);
	}
	
	/**
	 * Get the ingredients from the database.
	 * @return
	 */
	public List<Ingredient> getIngredients() {
		
		final F<Ingredient, Boolean> filtering = new F<Ingredient, Boolean>() {
			@Override
			@SuppressWarnings("synthetic-access")
			public Boolean f(final Ingredient i) {
				return fromString(filter).option(Boolean.TRUE, new F<String, Boolean>() {
					@Override
					public Boolean f(final String a) {
						return fromNull(i.getName()).orSome("").startsWith(a);
					}
				});
			}
		};
		return wrap(iterableList(ingredients)
				.filter(filtering).sort(ingredientOrd)).toStandardList();
	}
	
	/**
	 * @return the addable
	 */
	public boolean checkIfAddable() {
		final boolean addable = hasText(newIngredient.getName())
			&& fromNull(domainService.getIngredient(newIngredient.getName().trim())).isNone();
		return addable;
	}
	
	/**
	 * Add an {@link Ingredient}.
	 * @return
	 */
	public String addIngredient() {
		if (checkIfAddable()) {
			ingredients.add(domainService.addIngredient(newIngredient));
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
			domainService.deleteIngredients(selectedIngredients);
			ingredients.removeAll(array(selectedIngredients).toCollection());
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
	 * @param domainService the domainService to set
	 */
	public void setDomainService(final DomainService domainService) {
		this.domainService = domainService;
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

	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
}
