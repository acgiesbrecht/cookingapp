/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author llevague
 * 
 */
@Entity
public class StepIngredient implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 6820503964897493462L;

	/**
	 * The step.
	 */
	@EmbeddedId
	private StepIngredientId id;

	/**
	 * Quantity of the ingredient.
	 */
	@Basic(optional = false)
	@Column(name = "quantity")
	private int quantity = 0;

	/**
	 * The measuring unit.
	 */
	// FIXME replace type.
	@Basic(optional = false)
	@Column(name = "measuringUnit")
	private String measuringUnit;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
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
	 * @return the id
	 */
	public StepIngredientId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final StepIngredientId id) {
		this.id = id;
	}

	/**
	 * @param id the id to set
	 */
	public StepIngredient withId(final StepIngredientId id) {
		this.id = id;
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
	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withQuantity(final int quantity) {
		this.quantity = quantity;
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
	public void setMeasuringUnit(final String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	/**
	 * @param measuringUnit
	 *            the quantity to set
	 * @return the {@link StepIngredient}
	 */
	public StepIngredient withMeasuringUnit(final String measuringUnit) {
		this.measuringUnit = measuringUnit;
		return this;
	}
}
