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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author llevague
 * 
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
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
	private String name;

	/**
	 * Ingredient's description.
	 */
	@Basic
	private String description;

	/**
	 * Constructor.
	 */
	public Ingredient() {
		name = "";
		description = "";
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 */
	public Ingredient(final String name) {
		this.name = name;
		description = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		final Ingredient other = (Ingredient) obj;
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
	public void setId(final Long _id) {
		this.id = _id;
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
	public void setName(final String _name) {
		this.name = _name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @return the {@link Ingredient}
	 */
	public Ingredient withName(final String _name) {
		this.name = _name;
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
	public void setDescription(final String _description) {
		this.description = _description;
	}

	/**
	 * @param description
	 *            the description to set
	 * @return the {@link Ingredient}
	 */
	public Ingredient withDescription(final String _description) {
		this.description = _description;
		return this;
	}

}
