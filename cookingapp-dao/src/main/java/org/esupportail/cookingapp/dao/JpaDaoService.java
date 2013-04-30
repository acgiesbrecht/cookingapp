/**
 * 
 */
package org.esupportail.cookingapp.dao;

import static fj.data.Option.fromNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.beans.QIngredient;
import org.esupportail.cookingapp.domain.beans.QRecipe;
import org.esupportail.cookingapp.domain.beans.QStep;
import org.esupportail.cookingapp.domain.beans.QStepIngredient;
import org.esupportail.cookingapp.domain.beans.Recipe;
import org.esupportail.cookingapp.domain.beans.Step;
import org.esupportail.cookingapp.domain.beans.StepIngredient;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;

import fj.F;
import fj.Unit;

/**
 * @author llevague
 *
 */
public class JpaDaoService implements DaoService {
	
	private EntityManager entityManager;

	@Override
	public Ingredient getIngredient(final String name) {
		final QIngredient ingredient = QIngredient.ingredient;
		return from(ingredient)
				.where(ingredient._name.eq(name))
				.uniqueResult(ingredient);
	}
	
	@Override
	public List<Ingredient> getIngredients() {
		final QIngredient ingredient = QIngredient.ingredient;
		return from(ingredient).list(ingredient);
	}

	@Override
	public Step getStep(final Long id) {
		final QStep step = QStep.step;
		return from(step)
				.where(step._id.eq(id))
				.uniqueResult(step);
	}

	@Override
	public StepIngredient getStepIngredient(final Step step) {
		final QStepIngredient stepIngredient = QStepIngredient.stepIngredient;
		return from(stepIngredient)
				.where(stepIngredient._step.eq(step))
				.uniqueResult(stepIngredient);
	}

	@Override
	public StepIngredient getStepIngredient(final Ingredient ingredient) {
		final QStepIngredient stepIngredient = QStepIngredient.stepIngredient;
		return from(stepIngredient)
				.where(stepIngredient._ingredient.eq(ingredient))
				.uniqueResult(stepIngredient);
	}

	@Override
	public StepIngredient getStepIngredient(final Step step, Ingredient ingredient) {
		final QStepIngredient stepIngredient = QStepIngredient.stepIngredient;
		return from(stepIngredient)
				.where(stepIngredient._step.eq(step)
					.and(stepIngredient._ingredient.eq(ingredient)))
				.uniqueResult(stepIngredient);
	}

	@Override
	public Recipe getRecipe(final Long id) {
		final QRecipe recipe = QRecipe.recipe;
		return from(recipe)
				.where(recipe._id.eq(id))
				.uniqueResult(recipe);
	}

	@Override
	public List<Recipe> getRecipes() {
		final QRecipe recipe = QRecipe.recipe;
		return from(recipe).list(recipe);
	}

	@Override
	public List<Recipe> getRecipes(final String name) {
		final QRecipe recipe = QRecipe.recipe;
		return from(recipe)
				.where(recipe._name.eq(name))
				.list(recipe);
	}
	
	@Override
	public <T> void add(final T object) {
		fromNull(object).foreach(new F<T, Unit>() {
			@Override
			public Unit f(final T o) {
				getEntityManager().persist(object);
				return Unit.unit();
			}
		});
	}

	@Override
	public <T> void delete(final T object) {
		fromNull(object).foreach(new F<T, Unit>() {
			@Override
			public Unit f(final T o) {
				getEntityManager().remove(object);
				return Unit.unit();
			}
		});
	}
	
	/**
	 * @param entityManager the entityManager to set
	 */
	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * @return the entityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private JPAQuery from(final EntityPath<?>... entityPaths) {
		return new JPAQuery(entityManager).from(entityPaths);
	}
}
