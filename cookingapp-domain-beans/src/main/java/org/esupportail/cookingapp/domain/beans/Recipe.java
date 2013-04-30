/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long _id;

	/**
	 * Recipe's name.
	 */
	@Basic
	@Column(name = "name")
	private String _name = "";

	/**
	 * Recipe's description.
	 */
	@Basic
	@Column(name = "description")
	private String _description = "";

	/**
	 * Alternatives for this recipe.
	 */
	@JoinColumn(name = "alternatives")
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Recipe> _alternatives = new ArrayList<>();

	/**
	 * Steps of this recipe.
	 */
	@Column(name = "steps")
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, mappedBy = "_recipe")
	private List<Step> _steps = new ArrayList<>();

	/**
	 * Constructor.
	 */
	public Recipe() {
	}

	/**
	 * Constructor.
	 */
	public Recipe(final String name) {
		this._name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
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
		if (_id == null) {
			if (other._id != null) {
				return false;
			}
		} else if (!_id.equals(other._id)) {
			return false;
		}
		if (_name == null) {
			if (other._name != null) {
				return false;
			}
		} else if (!_name.equals(other._name)) {
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
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this._name = name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @return the {@link Recipe}
	 */
	public Recipe withName(final String name) {
		this._name = name;
		return this;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this._description = description;
	}

	/**
	 * @param description
	 *            the description to set
	 * @return the {@link Recipe}
	 */
	public Recipe withDescription(final String description) {
		this._description = description;
		return this;
	}

	/**
	 * @return the alternatives
	 */
	public List<Recipe> getAlternatives() {
		return _alternatives;
	}

	/**
	 * @param alternatives
	 *            the alternatives to set
	 */
	public void setAlternatives(final List<Recipe> alternatives) {
		this._alternatives = alternatives;
	}

	/**
	 * @param alternatives
	 *            the alternatives to set
	 * @return the {@link Recipe}
	 */
	public Recipe withAlternatives(final List<Recipe> alternatives) {
		this._alternatives = alternatives;
		return this;
	}

	/**
	 * @return the steps
	 */
	public List<Step> getSteps() {
		return _steps;
	}

	/**
	 * @param steps
	 *            the steps to set
	 */
	public void setSteps(final List<Step> steps) {
		this._steps = steps;
	}

	/**
	 * @param steps
	 *            the steps to set
	 * @return the {@link Recipe}
	 */
	public Recipe withSteps(final List<Step> steps) {
		this._steps = steps;
		return this;
	}

}
