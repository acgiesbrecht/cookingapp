/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author llevague
 * 
 */
@Embeddable
public class StepIngredientId implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 3440682589914042728L;

	/**
	 * The step.
	 */
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Step step;

	/**
	 * The ingredient.
	 */
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Ingredient ingredient;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((step == null) ? 0 : step.hashCode());
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
		if (ingredient == null) {
			if (other.ingredient != null) {
				return false;
			}
		} else if (!ingredient.equals(other.ingredient)) {
			return false;
		}
		if (step == null) {
			if (other.step != null) {
				return false;
			}
		} else if (!step.equals(other.step)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the step
	 */
	public Step getStep() {
		return step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(final Step step) {
		this.step = step;
	}

	/**
	 * @return the ingredient
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}

	/**
	 * @param ingredient
	 *            the ingredient to set
	 */
	public void setIngredient(final Ingredient ingredient) {
		this.ingredient = ingredient;
	}

}
