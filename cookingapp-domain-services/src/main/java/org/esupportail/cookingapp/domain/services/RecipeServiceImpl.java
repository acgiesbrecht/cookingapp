/**
 * 
 */
package org.esupportail.cookingapp.domain.services;


import static fj.data.IterableW.wrap;
import static fj.data.List.iterableList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.esupportail.cookingapp.dao.repositories.RecipeRepository;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.springframework.transaction.annotation.Transactional;

import fj.Effect;

/**
 * @author llevague
 * 
 */
@Transactional
public class RecipeServiceImpl extends AbstractDomainService implements RecipeService {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 1064741474140299471L;
	
	/**
	 * ES ingredient type.
	 */
	@SuppressWarnings("unused")
	private final String TYPE_INGREDIENT = "recipe";
	
	/**
	 * A repository for {@link Recipe}s
	 */
	@Inject
	private RecipeRepository recipeRepository;
	
	@Override
	public Recipe getRecipe(final Long id) {
		return recipeRepository.findOne(id);
	}

	@Override
	public List<Recipe> getRecipes() {
		return new ArrayList<>(wrap(recipeRepository.findAll()).toStandardList());
	}

	@Override
	public List<Recipe> getRecipes(final String name) {
		return new ArrayList<>(wrap(recipeRepository.findByName(name)).toStandardList());
	}

	@Override
	public void addRecipe(final Recipe recipe) {
		final List<Recipe> recipies = getRecipes(recipe.getName());
		recipe.getAlternatives().addAll(recipies);
		recipeRepository.save(recipe);
		iterableList(recipies).foreach(new Effect<Recipe>() {
			@Override
			public void e(final Recipe r) {
				r.getAlternatives().add(recipe);
			}
		});
	}

	@Override
	public void deleteRecipe(final Recipe recipe) {
		final Recipe toDelete = getRecipe(recipe.getId());
		iterableList(getRecipes(toDelete.getName())).foreach(new Effect<Recipe>() {
			@Override
			public void e(final Recipe r) {
				if (!r.equals(recipe)) {
					r.getAlternatives().remove(recipe);
				}
			}
		});
		recipeRepository.delete(toDelete.withAlternatives(new ArrayList<Recipe>()));
	}
}
