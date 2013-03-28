/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * @author llevague
 * 
 */
@Entity
@IdClass(StepIngredientId.class)
public class StepIngredient implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 6820503964897493462L;

	/**
	 * The step.
	 */
	@Id
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Step step;

	/**
	 * The ingredient.
	 */
	@Id
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Ingredient ingredient;

	/**
	 * Quantity of the ingredient.
	 */
	@Basic(optional = false)
	private int quantity = 0;

	/**
	 * The measuring unit.
	 */
	// FIXME replace type.
	@Basic(optional = false)
	private String measuringUnit;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((step == null) ? 0 : step.hashCode());
		result = prime * result
				+ ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result
				+ ((measuringUnit == null) ? 0 : measuringUnit.hashCode());
		result = prime * result + quantity;
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
		final StepIngredient other = (StepIngredient) obj;
		if (step == null) {
			if (other.step != null) {
				return false;
			}
		} else if (!step.equals(other.step)) {
			return false;
		}
		if (ingredient == null) {
			if (other.ingredient != null) {
				return false;
			}
		} else if (!ingredient.equals(other.ingredient)) {
			return false;
		}
		if (measuringUnit == null) {
			if (other.measuringUnit != null) {
				return false;
			}
		} else if (!measuringUnit.equals(other.measuringUnit)) {
			return false;
		}
		if (quantity != other.quantity) {
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
	public void setStep(final Step _step) {
		this.step = _step;
	}

	/**
	 * @param step
	 *            the step to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withStep(final Step _step) {
		this.step = _step;
		return this;
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
	public void setIngredient(final Ingredient _ingredient) {
		this.ingredient = _ingredient;
	}

	/**
	 * @param ingredient
	 *            the ingredient to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withIngredient(final Ingredient _ingredient) {
		this.ingredient = _ingredient;
		return this;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(final int _quantity) {
		this.quantity = _quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withQuantity(final int _quantity) {
		this.quantity = _quantity;
		return this;
	}

	/**
	 * @return the measuringUnit
	 */
	public String getMeasuringUnit() {
		return measuringUnit;
	}

	/**
	 * @param measuringUnit
	 *            the measuringUnit to set
	 */
	public void setMeasuringUnit(final String _measuringUnit) {
		this.measuringUnit = _measuringUnit;
	}

	/**
	 * @param measuringUnit
	 *            the quantity to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withMeasuringUnit(final String _measuringUnit) {
		this.measuringUnit = _measuringUnit;
		return this;
	}
}
