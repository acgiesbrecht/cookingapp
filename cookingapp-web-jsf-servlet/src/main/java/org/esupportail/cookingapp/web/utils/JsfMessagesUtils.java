/**
 * ESUP-Portail Commons - Copyright (c) 2006-2009 ESUP-Portail consortium.
 */
package org.esupportail.cookingapp.web.utils;

import java.util.Locale;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.esupportail.commons.services.i18n.I18nService;

/**
 * An utilitary class to get access to i18n features.
 */
public class JsfMessagesUtils {

	/**
	 * The name of the session attribute that stores the current locale.
	 */
	public final String LOCALE_ATTRIBUTE = "locale";
	
	/**
	 * i18n service.
	 */
	@Inject
	public I18nService i18nService;

	/**
	 * Constructor.
	 */
	private JsfMessagesUtils() {
	}
	
	public static JsfMessagesUtils getInstance() {
		return new JsfMessagesUtils();
	}
	
	/**
	 * @see org.esupportail.commons.beans.AbstractI18nAwareBean#getDefaultLocale()
	 */
	public Locale getDefaultLocale() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null) {
			return Locale.getDefault();
		}
		UIViewRoot viewRoot = null;
		try {
			viewRoot = context.getViewRoot();
		} catch (IllegalStateException e) {
			// context has probably been released, happens on exception handling
		}
		if (viewRoot != null) {
			return viewRoot.getLocale();
		}
		Application application = null;
		try {
			application = context.getApplication();
		} catch (IllegalStateException e) {
			// context has probably been released, happens on exception handling
		}
		if (application == null) {
			return Locale.getDefault();
		}
		return application.getDefaultLocale();
	}


	/**
	 * @return a FacesMessage that corresponds to a message and a severity level.
	 * @param severity the severity
	 * @param message the message itself
	 */
	private FacesMessage getFacesFormattedMessage(
			final Severity severity,
			final String message) {
		return getFacesFormattedMessage(severity, message, null);
	}

	/**
	 * @return a FacesMessage that corresponds to a message and a severity level.
	 * @param severity the severity
	 * @param message the message itself
	 * @param detail the detail message
	 */
	private FacesMessage getFacesFormattedMessage(
			final Severity severity,
			final String message,
			final String detail) {
		return new FacesMessage(severity, message, detail);
	}

	/**
	 * @return a FacesMessage that corresponds to a message and a severity level.
	 * @param severity the severity
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nArgs
	 */
	private FacesMessage getFacesMessage(
			final Severity severity,
			final String i18nMessage,
			final Object... i18nArgs) {
		return getFacesFormattedMessage(
				severity, getI18nService().getString(i18nMessage, i18nArgs));
	}

	/**
	 * @return a FacesMessage that corresponds to a message and a severity level.
	 * @param severity the severity
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nArgs
	 */
	private FacesMessage getFacesMessage(
			final Severity severity,
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object... i18nArgs) {
		if (i18nDetailMessage != null && !"".equals(i18nDetailMessage.trim())) {
			return getFacesFormattedMessage(severity, getI18nService()
					.getString(i18nMessage, i18nArgs), getI18nService()
					.getString(i18nDetailMessage, i18nArgs));
		}
		return getFacesFormattedMessage(severity, getI18nService()
				.getString(i18nMessage, i18nArgs));
	}

	/**
	 * @return a FacesMessage that corresponds to a message and a severity level.
	 * @param severity the severity
	 * @param i18nMessage the key of the message in the i18n bundles
	 */
	private FacesMessage getFacesMessage(
			final Severity severity,
			final String i18nMessage) {
		return getFacesFormattedMessage(severity, getI18nService().getString(i18nMessage));
	}

	/**
	 * @return a warn FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nArgs
	 */
	public FacesMessage getFacesWarnMessage(
			final String i18nMessage,
			final Object i18nArgs) {
		return getFacesMessage(FacesMessage.SEVERITY_WARN, i18nMessage, i18nArgs);
	}
	
	/**
	 * @return a warn FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 * @param i18nArgs
	 */
	public FacesMessage getFacesWarnMessage(
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object i18nArgs) {
		return getFacesMessage(FacesMessage.SEVERITY_WARN, i18nMessage, i18nDetailMessage, i18nArgs);
	}

	/**
	 * @return a warn FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 */
	public FacesMessage getFacesWarnMessage(
			final String i18nMessage) {
		return getFacesMessage(FacesMessage.SEVERITY_WARN, i18nMessage);
	}

	/**
	 * @return an error FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nArgs
	 */
	public FacesMessage getFacesErrorMessage(
			final String i18nMessage,
			final Object... i18nArgs) {
		return getFacesMessage(
				FacesMessage.SEVERITY_ERROR, i18nMessage, i18nArgs);
	}

	/**
	 * @return an error FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 * @param i18nArgs
	 */
	public FacesMessage getFacesErrorMessage(
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object... i18nArgs) {
		return getFacesMessage(
				FacesMessage.SEVERITY_ERROR, i18nMessage, i18nDetailMessage, i18nArgs);
	}

	/**
	 * @return an error FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 */
	public FacesMessage getFacesErrorMessage(
			final String i18nMessage) {
		return getFacesMessage(FacesMessage.SEVERITY_ERROR, i18nMessage);
	}

	/**
	 * @return an info FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nArgs
	 */
	public FacesMessage getFacesInfoMessage(
			final String i18nMessage,
			final Object... i18nArgs) {
		return getFacesMessage(FacesMessage.SEVERITY_INFO, i18nMessage, i18nArgs);
	}
	/**
	 * @return an info FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 * @param i18nArgs
	 */
	public FacesMessage getFacesInfoMessage(
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object... i18nArgs) {
		return getFacesMessage(FacesMessage.SEVERITY_INFO, i18nMessage, i18nDetailMessage, i18nArgs);
	}

	/**
	 * @return an info FacesMessage that corresponds to a message and a severity level.
	 * @param i18nMessage the key of the message in the i18n bundles
	 */
	public FacesMessage getFacesInfoMessage(
			final String i18nMessage) {
		return getFacesMessage(FacesMessage.SEVERITY_INFO, i18nMessage);
	}

	/**
	 * Add to the current context a message.
	 * @param severity the severity
	 * @param clientId the id of client that should receive the message
	 * @param message the message itself
	 * @param detailMessage the detail message
	 */
	private void addFormattedMessage(
			final Severity severity,
			final String clientId,
			final String message,
			final String detailMessage) {
		FacesMessage facesMessage;
		if (detailMessage != null && !"".equals(detailMessage.trim())) {
			facesMessage = getFacesFormattedMessage(severity, message, detailMessage);	
		} else {
			facesMessage = getFacesFormattedMessage(severity, message);	
		}
		FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
	}

	/**
	 * Add to the current context a formatted error message.
	 * @param clientId the id of client that should receive the message
	 * @param message the message itself
	 */
	public void addFormattedError(
			final String clientId,
			final String message) {
		addFormattedMessage(FacesMessage.SEVERITY_ERROR, clientId, message, null);
	}

	/**
	 * Add to the current context a formatted error message.
	 * @param clientId the id of client that should receive the message
	 * @param message the message itself
	 * @param detailMessage the detail message
	 */
	public void addFormattedError(
			final String clientId,
			final String message,
			final String detailMessage) {
		addFormattedMessage(FacesMessage.SEVERITY_ERROR, clientId, message, detailMessage);
	}

	/**
	 * Add to the current context a formatted info message.
	 * @param clientId the id of client that should receive the message
	 * @param message the message itself
	 */
	public void addFormattedInfo(
			final String clientId,
			final String message) {
		addFormattedMessage(FacesMessage.SEVERITY_INFO, clientId, message, null);
	}

	/**
	 * Add to the current context a formatted info message.
	 * @param clientId the id of client that should receive the message
	 * @param message the message itself
	 * @param detailMessage the detail message
	 */
	public void addFormattedInfo(
			final String clientId,
			final String message,
			final String detailMessage) {
		addFormattedMessage(FacesMessage.SEVERITY_INFO, clientId, message, detailMessage);
	}

	/**
	 * Add to the current context a formatted warn message.
	 * @param clientId the id of client that should receive the message
	 * @param message the message itself
	 */
	public void addFormattedWarn(
			final String clientId,
			final String message) {
		addFormattedMessage(FacesMessage.SEVERITY_WARN, clientId, message, null);
	}

	/**
	 * Add to the current context a formatted warn message.
	 * @param clientId the id of client that should receive the message
	 * @param message the message itself
	 * @param detailMessage the detail message
	 */
	public void addFormattedWarn(
			final String clientId,
			final String message,
			final String detailMessage) {
		addFormattedMessage(FacesMessage.SEVERITY_WARN, clientId, message, detailMessage);
	}

	/**
	 * Add to the current context a message.
	 * @param severity the severity
	 * @param clientId the id of client that should receive the message
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 * @param i18nArgs
	 */
	private void addMessage(
			final Severity severity,
			final String clientId,
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object... i18nArgs) {
		String message = getI18nService().getString(
				i18nMessage, getDefaultLocale(), i18nArgs);
		if (i18nDetailMessage != null && !"".equals(i18nDetailMessage.trim())) {
			String detailmessage = getI18nService().getString(
					i18nDetailMessage, getDefaultLocale(), i18nArgs);
			addFormattedMessage(severity, clientId, message, detailmessage);
		} else {
			addFormattedMessage(severity, clientId, message, null);
		}
	}

	/**
	 * Add to the current context a error message.
	 * @param clientId the id of client that should receive the message
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 * @param i18nArgs
	 */
	public void addWarnMessage(
			final String clientId,
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object... i18nArgs) {
		addMessage(FacesMessage.SEVERITY_WARN, clientId, i18nMessage, i18nDetailMessage, i18nArgs);
	}

	/**
	 * Add to the current context a error message.
	 * @param clientId the id of client that should receive the message
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 */
	public void addWarnMessage(
			final String clientId,
			final String i18nMessage,
			final String i18nDetailMessage) {
		addMessage(FacesMessage.SEVERITY_WARN, clientId, i18nMessage, i18nDetailMessage);
	}

	/**
	 * Add to the current context a error message.
	 * @param clientId the id of client that should receive the message
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 * @param i18nArgs
	 */
	public void addErrorMessage(
			final String clientId,
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object... i18nArgs) {
		addMessage(FacesMessage.SEVERITY_ERROR, clientId, i18nMessage, i18nDetailMessage, i18nArgs);
	}

	/**
	 * Add to the current context a error message.
	 * @param clientId the id of client that should receive the message
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 */
	public void addErrorMessage(
			final String clientId,
			final String i18nMessage,
			final String i18nDetailMessage) {
		addMessage(FacesMessage.SEVERITY_ERROR, clientId, i18nMessage, i18nDetailMessage);
	}

	/**
	 * Add to the current context an info message.
	 * @param clientId the id of client that should receive the message
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 * @param i18nArgs
	 */
	public void addInfoMessage(
			final String clientId,
			final String i18nMessage,
			final String i18nDetailMessage,
			final Object... i18nArgs) {
		addMessage(FacesMessage.SEVERITY_INFO, clientId, i18nMessage, i18nDetailMessage, i18nArgs);
	}

	/**
	 * Add to the current context an info message.
	 * @param clientId the id of client that should receive the message
	 * @param i18nMessage the key of the message in the i18n bundles
	 * @param i18nDetailMessage the key of the detail message in the i18n bundles
	 */
	public void addInfoMessage(
			final String clientId,
			final String i18nMessage,
			final String i18nDetailMessage) {
		addMessage(FacesMessage.SEVERITY_INFO, clientId, i18nMessage, i18nDetailMessage);
	}
	/**
	 * Add to the current context a message saying that the action is not authorized.
	 */
	public void addUnauthorizedActionMessage() {
		addErrorMessage(null, "_.MESSAGE.UNAUTHORIZED_ACTION", null);
	}

	/**
	 * @return the i18nService
	 */
	private I18nService getI18nService() {
		return i18nService;
	}
}
