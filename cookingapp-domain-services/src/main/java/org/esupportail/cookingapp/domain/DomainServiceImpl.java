/**
 * 
 */
package org.esupportail.cookingapp.domain;


import static fj.data.Array.array; 
import static fj.data.List.iterableList;
import static fj.data.Option.fromNull;
import static fj.Unit.unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.Client;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.cookingapp.dao.DaoService;
import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.esupportail.cookingapp.domain.beans.Step;
import org.esupportail.cookingapp.domain.beans.StepIngredient;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fj.Effect;
import fj.F;
import fj.Unit;

/**
 * @author llevague
 * 
 */
@Component
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
	 * The DAO service.
	 */
	@Inject
	@Named("daoService")
	private DaoService daoService;
	
	/**
	 * An elasticSearch client.
	 */
	@Inject
	@Named("esClient")
	private Client clientES;
	
	/**
	 * An elasticSearch object mapper.
	 */
	private ObjectMapper mapper;
	
	public DomainServiceImpl() {
		mapper = new ObjectMapper();
	}

	@Override
	public Ingredient getIngredient(final String name) {
		return daoService.getIngredient(name);
	}

	@Override
	public List<Ingredient> getIngredients() {
		return daoService.getIngredients();
	}

	@Override
	public Ingredient addIngredient(final Ingredient ingredient) {
		daoService.add(ingredient);
		final Ingredient theIngredient = daoService.getIngredient(ingredient.getName());
		clientES.prepareIndex(INDEX, TYPE_INGREDIENT, theIngredient.getId().toString())
			.setSource(getJson(theIngredient)).execute().actionGet();
		return theIngredient;
	}

	@Override
	public void deleteIngredient(final Ingredient ingredient) {
		final Ingredient theIngredient = daoService.getIngredient(ingredient.getName());
		clientES.delete(new DeleteRequest(INDEX, TYPE_INGREDIENT, theIngredient.getId().toString()));
		daoService.delete(theIngredient);
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
		fromNull(ingredients).foreach(new F<Collection<Ingredient>, Unit>() {
			@Override
			public Unit f(final Collection<Ingredient> c) {
				for (Ingredient ingredient : c) {
					deleteIngredient(ingredient);
				}
				return unit();
			}
		});
	}

	@Override
	public Step getStep(final Long id) {
		return daoService.getStep(id);
	}

	@Override
	public void addStep(final Step step) {
		daoService.add(step);
	}

	@Override
	public void deleteStep(final Step step) {
		daoService.delete(step);
	}

	@Override
	public StepIngredient getStepIngredient(final Step step) {
		return daoService.getStepIngredient(step);
	}

	@Override
	public StepIngredient getStepIngredient(final Ingredient ingredient) {
		return daoService.getStepIngredient(ingredient);
	}

	@Override
	public StepIngredient getStepIngredient(final Step step, final Ingredient ingredient) {
		return daoService.getStepIngredient(step, ingredient);
	}

	@Override
	public Recipe getRecipe(final Long id) {
		return daoService.getRecipe(id);
	}

	@Override
	public List<Recipe> getRecipes() {
		return daoService.getRecipes();
	}

	@Override
	public List<Recipe> getRecipes(final String name) {
		return daoService.getRecipes(name);
	}

	@Override
	public void addRecipe(final Recipe recipe) {
		final List<Recipe> recipies = getRecipes(recipe.getName());
		recipe.getAlternatives().addAll(recipies);
		daoService.add(recipe);
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
		daoService.delete(toDelete.withAlternatives(new ArrayList<Recipe>()));
	}
	
	private <T> String getJson(final T object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param daoService the daoService to set
	 */
	public void setDaoService(final DaoService daoService) {
		this.daoService = daoService;
	}

	/**
	 * @param clientES the clientES to set
	 */
	public void setClientES(final Client clientES) {
		this.clientES = clientES;
	}
	
}
