/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author llevague
 * 
 */
@Entity
public class Step implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -1882096294929871161L;

	/**
	 * Database id.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long _id;

	/**
	 * The recipe.
	 */
	@JoinColumn(name = "recipe")
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Recipe _recipe;

	/**
	 * Instructions to follow.
	 */
	@Column(name = "instructions")
	private String _instructions;

	/**
	 * Mandatory ingredients with their quantity.
	 */
	@JoinColumn(name = "stepIngredients")
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<StepIngredient> _stepIngredients = new ArrayList<>();

	/**
	 * Constructor.
	 */
	public Step() {
		_instructions = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((_recipe == null) ? 0 : _recipe.hashCode());
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
		final Step other = (Step) obj;
		if (_id == null) {
			if (other._id != null) {
				return false;
			}
		} else if (!_id.equals(other._id)) {
			return false;
		}
		if (_recipe == null) {
			if (other._recipe != null) {
				return false;
			}
		} else if (!_recipe.equals(other._recipe)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return _id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this._id = id;
	}

	/**
	 * @return the recipe
	 */
	public Recipe getRecipe() {
		return _recipe;
	}

	/**
	 * @param recipe
	 *            the recipe to set
	 */
	public void setRecipe(final Recipe recipe) {
		this._recipe = recipe;
	}

	/**
	 * @param recipe
	 *            the recipe to set
	 * @return the {@link Step}
	 */
	public Step withRecipe(final Recipe recipe) {
		this._recipe = recipe;
		return this;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return _instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(final String instructions) {
		this._instructions = instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 * @return the {@link Step}
	 */
	public Step withInstructions(final String instructions) {
		this._instructions = instructions;
		return this;
	}

	/**
	 * @return the stepIngredients
	 */
	public List<StepIngredient> getStepIngredients() {
		return _stepIngredients;
	}

	/**
	 * @param stepIngredients
	 *            the stepIngredients to set
	 */
	public void setStepIngredients(final List<StepIngredient> stepIngredients) {
		this._stepIngredients = stepIngredients;
	}

	/**
	 * @param stepIngredients
	 *            the stepIngredients to set
	 * @return the {@link Step}
	 */
	public Step withStepIngredients(final List<StepIngredient> stepIngredients) {
		this._stepIngredients = stepIngredients;
		return this;
	}

}
