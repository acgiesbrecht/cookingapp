/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * The recipe.
	 */
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Recipe recipe;

	/**
	 * Instructions to follow.
	 */
	private String instructions;

	/**
	 * Mandatory ingredients with their quantity.
	 */
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<StepIngredient> stepIngredients = new ArrayList<>();

	/**
	 * Constructor.
	 */
	public Step() {
		instructions = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (recipe == null) {
			if (other.recipe != null) {
				return false;
			}
		} else if (!recipe.equals(other.recipe)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the recipe
	 */
	public Recipe getRecipe() {
		return recipe;
	}

	/**
	 * @param recipe
	 *            the recipe to set
	 */
	public void setRecipe(final Recipe recipe) {
		this.recipe = recipe;
	}

	/**
	 * @param recipe
	 *            the recipe to set
	 * @return the {@link Step}
	 */
	public Step withRecipe(final Recipe recipe) {
		this.recipe = recipe;
		return this;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(final String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 * @return the {@link Step}
	 */
	public Step withInstructions(final String instructions) {
		this.instructions = instructions;
		return this;
	}

	/**
	 * @return the stepIngredients
	 */
	public List<StepIngredient> getStepIngredients() {
		return stepIngredients;
	}

	/**
	 * @param stepIngredients
	 *            the stepIngredients to set
	 */
	public void setStepIngredients(final List<StepIngredient> stepIngredients) {
		this.stepIngredients = stepIngredients;
	}

	/**
	 * @param stepIngredients
	 *            the stepIngredients to set
	 * @return the {@link Step}
	 */
	public Step withStepIngredients(final List<StepIngredient> stepIngredients) {
		this.stepIngredients = stepIngredients;
		return this;
	}

}
