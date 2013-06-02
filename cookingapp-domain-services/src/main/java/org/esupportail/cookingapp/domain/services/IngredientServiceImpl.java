/**
 * 
 */
package org.esupportail.cookingapp.domain.services;


import static fj.Unit.unit;
import static fj.data.Array.array;
import static fj.data.Option.fromNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.elasticsearch.action.delete.DeleteRequest;
import org.esupportail.cookingapp.dao.repositories.IngredientRepository;
import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fj.F;
import fj.Unit;

/**
 * @author llevague
 * 
 */
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
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
	public Page<Ingredient> getIngredients(final int page, final int size, final Sort sort, final Map<String, String> filters) {
		return ingredientRepository
				.findAll(new Specification<Ingredient>() {
					@Override
					public Predicate toPredicate(Root<Ingredient> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						List<Predicate> restrictions = new ArrayList<Predicate>();
						if (filters != null) {
							for (Entry<String, String> filter : filters.entrySet()) {
								final String pattern = getLikePattern(filter.getValue());
								restrictions.add(
									cb.like(cb.lower(root.<String>get(filter.getKey())), pattern));	
							}
						}
						return cb.and(restrictions.toArray(new Predicate[]{}));
					}
					
		            private String getLikePattern(final String searchTerm) {
		                StringBuilder pattern = new StringBuilder();
		                pattern.append(searchTerm.toLowerCase());
		                pattern.append("%");
		                return pattern.toString();
		            }
				}, new PageRequest(page, size, sort));
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Page<Ingredient> getIngredients(final int page, final int size, final Sort sort) {
		return getIngredients(page, size, sort, null);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Page<Ingredient> getIngredients(final int page, final int size) {
		return getIngredients(page, size, null, null);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Page<Ingredient> getIngredientsStartingWith(final String name, final int page, final int size, final Sort sort) {
		return ingredientRepository
				.findByNameStartingWith(name, new PageRequest(page, size, sort));
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Page<Ingredient> getIngredientsStartingWith(final String name, int page, int size) {
		return getIngredientsStartingWith(name, page, size, null);
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
