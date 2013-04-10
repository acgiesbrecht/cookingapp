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
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.INGREDIENTS_LIST;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.INGREDIENT_ADD;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.REDIRECT;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.esupportail.commons.beans.AbstractJsfMessagesAwareBean;
import org.esupportail.cookingapp.domain.DomainService;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import fj.F;
import fj.Ordering;

/**
 * @author llevague
 *
 */
@ViewScoped
@ManagedBean
public class RecipeController extends AbstractJsfMessagesAwareBean {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 7506035726291472336L;

	/**
	 * The service.
	 */
	@Inject
	private DomainService domainService;
	
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
	 * A filtered value.
	 */
	private String filter;
	
	@PostConstruct
	public void init() {
		recipes = domainService.getRecipes();
		selectedRecipes = new Recipe[0];
		newRecipe = new Recipe();
		filter = new String();
	}
	
	public synchronized void pushRecipes() {
		PushContext pc = PushContextFactory.getDefault().getPushContext();
		pc.push("/recipes", recipes);
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
						return r.getName().startsWith(a);
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
			addInfoMessage(null, "INFO.INGREDIENT.ADD", newRecipe.getName());
			return goList();
		}
		addErrorMessage(null, "ERROR.INGREDIENT.ADD");
		return null;
	}

	
	/**
	 * Delete selected {@link Recipe}.
	 */
	public String deleteRecipes() {
		if (array(selectedRecipes).isNotEmpty()) {
//			domainService.deleteRecipes(selectedRecipes);
			recipes.removeAll(array(selectedRecipes).toCollection());
			addInfoMessage(null, "INFO.INGREDIENT.DELETE");
		}
		pushRecipes();
		return null;
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
