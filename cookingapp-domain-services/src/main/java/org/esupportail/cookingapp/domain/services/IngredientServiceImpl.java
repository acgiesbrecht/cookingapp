/**
 * 
 */
package org.esupportail.cookingapp.domain.services;


import static fj.Unit.unit;
import static fj.data.Array.array;
import static fj.data.Option.fromNull;

import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.action.delete.DeleteRequest;
import org.esupportail.cookingapp.dao.repositories.IngredientRepository;
import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fj.F;
import fj.Unit;

/**
 * @author llevague
 * 
 */
@Transactional
public class IngredientServiceImpl extends AbstractDomainService implements IngredientService {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 1949257025303804693L;

	/**
	 * ES ingredient type.
	 */
	private final String TYPE_INGREDIENT = "ingredient";
	
	/**
	 * A repository for {@link Ingredient}s.
	 */
	@Inject
	private IngredientRepository ingredientRepository;


	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Ingredient getIngredient(final String name) {
		return ingredientRepository.findByName(name);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Ingredient> getIngredients() {
		return ingredientRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Page<Ingredient> getIngredients(int page, int size) {
		return ingredientRepository
				.findAll(new PageRequest(page, size));
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Page<Ingredient> getIngredientsStartingWith(final String name, int page, int size) {
		return ingredientRepository
				.findByNameStartingWith(name, new PageRequest(page, size));
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
				deleteIngredients(array(a));
				return unit();
			}});
	}

	@Override
	public void deleteIngredients(final Iterable<Ingredient> ingredients) {
		fromNull(ingredients).option(unit(), new F<Iterable<Ingredient>, Unit>() {
			@Override
			public Unit f(final Iterable<Ingredient> a) {
				for (Ingredient ingredient : a) {
					deleteIngredient(ingredient);
				}
				return unit();
			}});
	}
}
