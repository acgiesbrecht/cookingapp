/**
 * 
 */
package org.esupportail.cookingapp.web.utils;

import static javax.faces.context.FacesContext.getCurrentInstance;

import javax.servlet.http.HttpServletRequest;

/**
 * @author llevague
 * 
 */
public class ContextHelper {

	/**
	 * 
	 */
	private ContextHelper() {
	}

	/**
	 * Put an object to the request.
	 * 
	 * @param name
	 * @param object
	 */
	public static void putToRequest(final String name, Object object) {
		HttpServletRequest _request = (HttpServletRequest) getCurrentInstance()
				.getExternalContext().getRequest();
		_request.setAttribute(name, object);
	}

	/**
	 * Get an object from the request.
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFromRequest(final String name) {
		HttpServletRequest _request = (HttpServletRequest) getCurrentInstance()
				.getExternalContext().getRequest();
		return (T) _request.getAttribute(name);
	}

	/**
	 * Put an object to the session.
	 * 
	 * @param name
	 * @param object
	 */
	public static void putToSession(final String name, Object object) {
		HttpServletRequest _request = (HttpServletRequest) getCurrentInstance()
				.getExternalContext().getRequest();
		_request.getSession().setAttribute(name, object);
	}

	/**
	 * Get an object from the session.
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFromSession(final String name) {
		HttpServletRequest _request = (HttpServletRequest) getCurrentInstance()
				.getExternalContext().getRequest();
		return (T) _request.getSession().getAttribute(name);
	}
}
