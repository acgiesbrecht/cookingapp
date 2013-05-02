/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

import javax.persistence.Basic;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Ingredient's name.
	 */
	@Basic
	private String name = "";

	/**
	 * Ingredient's description.
	 */
	@Basic
	private String description = "";

	/**
	 * Constructor. 
	 */
	public Ingredient() {
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 */
	public Ingredient(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
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
	 * @return the {@link Ingredient}
	 */
	public Ingredient withName(final String name) {
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
	 * @return the {@link Ingredient}
	 */
	public Ingredient withDescription(final String description) {
		this.description = description;
		return this;
	}

}
