/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	private Step _step;

	/**
	 * The ingredient.
	 */
	@Id
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Ingredient _ingredient;

	/**
	 * Quantity of the ingredient.
	 */
	@Basic(optional = false)
	@Column(name = "quantity")
	private int _quantity = 0;

	/**
	 * The measuring unit.
	 */
	// FIXME replace type.
	@Basic(optional = false)
	@Column(name = "measuringUnit")
	private String _measuringUnit;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_step == null) ? 0 : _step.hashCode());
		result = prime * result
				+ ((_ingredient == null) ? 0 : _ingredient.hashCode());
		result = prime * result
				+ ((_measuringUnit == null) ? 0 : _measuringUnit.hashCode());
		result = prime * result + _quantity;
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
		if (_step == null) {
			if (other._step != null) {
				return false;
			}
		} else if (!_step.equals(other._step)) {
			return false;
		}
		if (_ingredient == null) {
			if (other._ingredient != null) {
				return false;
			}
		} else if (!_ingredient.equals(other._ingredient)) {
			return false;
		}
		if (_measuringUnit == null) {
			if (other._measuringUnit != null) {
				return false;
			}
		} else if (!_measuringUnit.equals(other._measuringUnit)) {
			return false;
		}
		if (_quantity != other._quantity) {
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
	 * @param step
	 *            the step to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withStep(final Step step) {
		this._step = step;
		return this;
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

	/**
	 * @param ingredient
	 *            the ingredient to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withIngredient(final Ingredient ingredient) {
		this._ingredient = ingredient;
		return this;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return _quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(final int quantity) {
		this._quantity = quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withQuantity(final int quantity) {
		this._quantity = quantity;
		return this;
	}

	/**
	 * @return the measuringUnit
	 */
	public String getMeasuringUnit() {
		return _measuringUnit;
	}

	/**
	 * @param measuringUnit
	 *            the measuringUnit to set
	 */
	public void setMeasuringUnit(final String measuringUnit) {
		this._measuringUnit = measuringUnit;
	}

	/**
	 * @param measuringUnit
	 *            the quantity to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withMeasuringUnit(final String measuringUnit) {
		this._measuringUnit = measuringUnit;
		return this;
	}
}
