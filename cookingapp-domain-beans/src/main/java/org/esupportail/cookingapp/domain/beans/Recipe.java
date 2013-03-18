/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author llevague
 * 
 */
@Entity
public class Recipe implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 5594297286609482902L;

	/**
	 * Database id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Recipe's name.
	 */
	@Basic
	private String name = "";

	/**
	 * Recipe's description.
	 */
	@Basic
	private String description = "";

	/**
	 * Alternatives for this recipe.
	 */
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Recipe> alternatives = new ArrayList<>();

	/**
	 * Steps of this recipe.
	 */
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, mappedBy = "recipe")
	private List<Step> steps = new ArrayList<>();

	/**
	 * Constructor.
	 */
	public Recipe(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		final Recipe other = (Recipe) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @return the {@link Recipe}
	 */
	public Recipe withName(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param description
	 *            the description to set
	 * @return the {@link Recipe}
	 */
	public Recipe withDescription(final String description) {
		this.description = description;
		return this;
	}

	/**
	 * @return the alternatives
	 */
	public List<Recipe> getAlternatives() {
		return alternatives;
	}

	/**
	 * @param alternatives
	 *            the alternatives to set
	 */
	public void setAlternatives(final List<Recipe> alternatives) {
		this.alternatives = alternatives;
	}

	/**
	 * @param alternatives
	 *            the alternatives to set
	 * @return the {@link Recipe}
	 */
	public Recipe withAlternatives(final List<Recipe> alternatives) {
		this.alternatives = alternatives;
		return this;
	}

	/**
	 * @return the steps
	 */
	public List<Step> getSteps() {
		return steps;
	}

	/**
	 * @param steps
	 *            the steps to set
	 */
	public void setSteps(final List<Step> steps) {
		this.steps = steps;
	}

	/**
	 * @param steps
	 *            the steps to set
	 * @return the {@link Recipe}
	 */
	public Recipe withSteps(final List<Step> steps) {
		this.steps = steps;
		return this;
	}

}
