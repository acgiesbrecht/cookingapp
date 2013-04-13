/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author llevague
 * 
 */
@Entity
public class Ingredient implements Serializable {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 7142308107535294845L;

	/**
	 * Database id.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long _id;

	/**
	 * Ingredient's name.
	 */
	@Basic
	@Column(name = "name", unique = true)
	private String _name;

	/**
	 * Ingredient's description.
	 */
	@Basic
	@Column(name = "description")
	private String _description;

	/**
	 * Constructor.
	 */
	public Ingredient() {
		_name = "";
		_description = "";
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 */
	public Ingredient(final String name) {
		_name = name;
		_description = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		final Ingredient other = (Ingredient) obj;
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
	 * @return the {@link Ingredient}
	 */
	public Ingredient withName(final String name) {
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
	 * @return the {@link Ingredient}
	 */
	public Ingredient withDescription(final String description) {
		this._description = description;
		return this;
	}

}
