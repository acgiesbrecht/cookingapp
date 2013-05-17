/**
 * 
 */
package org.esupportail.cookingapp.domain.services;


import static fj.Unit.unit;
import static fj.data.Array.array;
import static fj.data.IterableW.wrap;
import static fj.data.List.iterableList;
import static fj.data.Option.fromNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.Client;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.cookingapp.dao.repositories.IngredientRepository;
import org.esupportail.cookingapp.dao.repositories.RecipeRepository;
import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fj.Effect;
import fj.F;
import fj.Unit;

/**
 * @author llevague
 * 
 */
@Transactional
public class DomainServiceImpl implements DomainService {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 1949257025303804693L;
	
	/**
	 * For Logging.
	 */
	@SuppressWarnings("unused")
	private final Logger logger = new LoggerImpl(this.getClass());
	
	/**
	 * ES index.
	 */
	private final String INDEX = "cookingapp";

	/**
	 * ES ingredient type.
	 */
	private final String TYPE_INGREDIENT = "ingredient";
	
	/**
	 * A repository for {@link Ingredient}s
	 */
	@Inject
	private IngredientRepository ingredientRepository;
	
	/**
	 * A repository for {@link Recipe}s
	 */
	@Inject
	private RecipeRepository recipeRepository;
	
	/**
	 * An elasticSearch client.
	 */
	@Inject
	private Client clientES;
	
	/**
	 * An elasticSearch object mapper.
	 */
	private ObjectMapper mapper;
	
	public DomainServiceImpl() {
		mapper = new ObjectMapper();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Ingredient getIngredient(final String name) {
		return ingredientRepository.findByName(name);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Ingredient> getIngredients() {
		return new ArrayList<>(wrap(ingredientRepository.findAll()).toStandardList());
	}

	@Override
	public Ingredient addIngredient(final Ingredient ingredient) {
		final Ingredient theIngredient = ingredientRepository.save(ingredient);
		clientES.prepareIndex(INDEX, TYPE_INGREDIENT, theIngredient.getId().toString())
			.setSource(getJson(theIngredient)).execute().actionGet();
		return theIngredient;
	}

	@Override
	public void deleteIngredient(final Ingredient ingredient) {
		final Ingredient theIngredient = getIngredient(ingredient.getName());
		clientES.delete(new DeleteRequest(INDEX, TYPE_INGREDIENT, theIngredient.getId().toString()));
		ingredientRepository.delete(theIngredient);
	}

	@Override
	public void deleteIngredients(final Ingredient... ingredients) {
		fromNull(ingredients).option(unit(), new F<Ingredient[], Unit>() {
			@Override
			public Unit f(final Ingredient[] a) {
				deleteIngredients(array(a).toCollection());
				return unit();
			}});
	}

	@Override
	public void deleteIngredients(final Collection<Ingredient> ingredients) {
		fromNull(ingredients).option(unit(), new F<Collection<Ingredient>, Unit>() {
			@Override
			public Unit f(final Collection<Ingredient> a) {
				ingredientRepository.delete(ingredients);
				return unit();
			}});
	}

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
	
	private <T> String getJson(final T object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}	
}
