/**
 * 
 */
package org.esupportail.cookingapp.domain.beans;

import java.io.Serializable;

/**
 * @author llevague
 *
 */
public class AuthUser implements Serializable {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = -2011185658231680522L;
	
	private String login = "anonymous";
	
	private String firstName = "anonymous";
	
	private String lastName = "anonymous";
	
	/**
	 * Constructor.
	 */
	public AuthUser() {
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * @param login the login to set
	 */
	public AuthUser withLogin(final String login) {
		this.login = login;
		return this;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public AuthUser withFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public AuthUser withLastName(final String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * @return the lastName
	 */
	public String getFullName() {
		return firstName + " " + lastName;
	}	

}
