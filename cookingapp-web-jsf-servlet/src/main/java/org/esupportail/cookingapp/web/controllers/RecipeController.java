/**
 * 
 */
package org.esupportail.cookingapp.web.controllers;

import static fj.Ord.ord;
import static fj.Ord.stringOrd;
import static fj.data.Array.array;
import static fj.data.IterableW.wrap;
import static fj.data.List.iterableList;
import static fj.data.Option.fromNull;
import static fj.data.Option.fromString;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.RECIPES_LIST;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.RECIPE_ADD;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.REDIRECT;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.esupportail.cookingapp.domain.beans.Step;
import org.esupportail.cookingapp.domain.services.DomainService;
import org.esupportail.cookingapp.utils.JsfMessagesUtils;
import org.primefaces.push.PushContextFactory;

import fj.F;
import fj.Ordering;

/**
 * @author llevague
 *
 */
@ViewScoped
@ManagedBean
public class RecipeController {

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
	 * The {@link Recipe} list.
	 */
	private List<Recipe> recipes;
		
	/**
	 * The selected {@link Recipe} list.
	 */
	private Recipe[] selectedRecipes;

	/**
	 * A new {@link Recipe} to add.
	 */
	private Recipe newRecipe;

	/**
	 * A new {@link Step} to add to the {@link Recipe}.
	 */
	private Step newStep;
	
	

	/**
	 * The {@link Ingredient} list.
	 */
	private List<Ingredient> ingredients;
	
	/**
	 * A filtered value.
	 */
	private String filter;
	
	@PostConstruct
	public void init() {
		filter = new String();
		// recipe
		recipes = domainService.getRecipes();
		selectedRecipes = new Recipe[0];
		newRecipe = new Recipe();
		
		// step
		
		
		// ingredients
		ingredients = domainService.getIngredients();
	}
	
	public synchronized void pushRecipes() {
		PushContextFactory.getDefault().getPushContext()
				.push("/recipes", recipes);
	}
	
	/**
	 * Get the recipes from the database.
	 * @return
	 */
	public List<Recipe> getRecipes() {

		final F<Recipe, F<Recipe, Ordering>> ordering = 
				new F<Recipe, F<Recipe, Ordering>>() {
			public F<Recipe, Ordering> f(final Recipe i1) {
				return new F<Recipe, Ordering>() {
					public Ordering f(final Recipe i2) {
						return stringOrd.compare(i1.getName(), i2.getName());
					}
				};
			};
		};

		final F<Recipe, Boolean> filtering = new F<Recipe, Boolean>() {
			@Override
			@SuppressWarnings("synthetic-access")
			public Boolean f(final Recipe r) {
				return fromString(filter).option(Boolean.TRUE, new F<String, Boolean>() {
					@Override
					public Boolean f(final String a) {
						return fromNull(r.getName()).orSome("").startsWith(a);
					}
				});
			}
		};
		return wrap(iterableList(recipes)
				.filter(filtering).sort(ord(ordering))).toStandardList();
	}
	
	/**
	 * @return the addable
	 */
	public boolean checkIfAddable() {
		final boolean addable = hasText(newRecipe.getName())
			&& fromNull(domainService.getRecipes(newRecipe.getName())).isNone();
		return addable;
	}
	
	/**
	 * Add an {@link Recipe}.
	 */
	public String addRecipe() {
		if (checkIfAddable()) {
			domainService.addRecipe(newRecipe);
			recipes.add(newRecipe);
			pushRecipes();
			jsfMessagesUtils.addInfoMessage(null, "INFO.RECIPE.ADD", null, newRecipe.getName());
			return goList();
		}
		jsfMessagesUtils.addErrorMessage(null, "ERROR.RECIPE.ADD", null);
		return null;
	}

	
	/**
	 * Delete selected {@link Recipe}.
	 */
	public String deleteRecipes() {
		if (array(selectedRecipes).isNotEmpty()) {
//			domainService.deleteRecipes(selectedRecipes);
//			recipes.removeAll(array(selectedRecipes).toCollection());
			jsfMessagesUtils.addInfoMessage(null, "INFO.RECIPE.DELETE", null);
		}
		pushRecipes();
		return null;
	}
	
	/**
	 * Adding callback.
	 * @return
	 */
	public String goAdd() {
		return RECIPE_ADD + REDIRECT;
	}

	/**
	 * Listing callback.
	 * @return
	 */
	public String goList() {
		return RECIPES_LIST + REDIRECT;
	}
	
	/**
	 * @return the newRecipe
	 */
	public Recipe getNewRecipe() {
		return newRecipe;
	}

	/**
	 * @param newRecipe the newRecipe to set
	 */
	public void setNewRecipe(final Recipe newRecipe) {
		this.newRecipe = newRecipe;
	}

	/**
	 * @return the newStep
	 */
	public Step getNewStep() {
		return newStep;
	}

	/**
	 * @param newStep the newStep to set
	 */
	public void setNewStep(final Step newStep) {
		this.newStep = newStep;
	}

	/**
	 * @param domainService the domainService to set
	 */
	public void setDomainService(final DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * @return the selectedRecipes
	 */
	public Recipe[] getSelectedRecipes() {
		return selectedRecipes;
	}

	/**
	 * @param selectedRecipes the selectedRecipes to set
	 */
	public void setSelectedRecipes(final Recipe[] selectedRecipes) {
		this.selectedRecipes = selectedRecipes;
	}

	/**
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
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
