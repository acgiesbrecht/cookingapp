package org.esupportail.cookingapp.domain.services;

import javax.inject.Inject;

import org.elasticsearch.client.Client;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author llevague
 *
 */
public abstract class AbstractDomainService {

	/**
	 * For Logging.
	 */
	protected final Logger logger = new LoggerImpl(getClass());
	
	/**
	 * ES index.
	 */
	protected final String INDEX = "cookingapp";
	
	/**
	 * An elasticSearch client.
	 */
	@Inject
	protected Client clientES;
	
	/**
	 * An elasticSearch object mapper.
	 */
	@Inject
	protected ObjectMapper mapper;
	

	/**
	 * Returns a JSON form of the parameter object.
	 * @param object
	 * @return
	 */
	protected <T> String getJson(final T object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}	
	
}
