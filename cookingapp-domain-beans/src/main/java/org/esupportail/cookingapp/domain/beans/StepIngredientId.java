/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * @author llevague
 * 
 */
public class StepIngredientId implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 3440682589914042728L;

	/**
	 * The step.
	 */
	@Column(name = "step")
	private Step _step;

	/**
	 * The ingredient.
	 */
	@Column(name = "ingredient")
	private Ingredient _ingredient;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_ingredient == null) ? 0 : _ingredient.hashCode());
		result = prime * result + ((_step == null) ? 0 : _step.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StepIngredientId other = (StepIngredientId) obj;
		if (_ingredient == null) {
			if (other._ingredient != null) {
				return false;
			}
		} else if (!_ingredient.equals(other._ingredient)) {
			return false;
		}
		if (_step == null) {
			if (other._step != null) {
				return false;
			}
		} else if (!_step.equals(other._step)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the step
	 */
	public Step getStep() {
		return _step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(final Step step) {
		this._step = step;
	}

	/**
	 * @return the ingredient
	 */
	public Ingredient getIngredient() {
		return _ingredient;
	}

	/**
	 * @param ingredient
	 *            the ingredient to set
	 */
	public void setIngredient(final Ingredient ingredient) {
		this._ingredient = ingredient;
	}

}
