/**
 * 
 */
package org.esupportail.cookingapp.domain.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.esupportail.commons.services.authentication.AuthUtils;
import org.esupportail.commons.services.authentication.AuthenticationService;
import org.esupportail.commons.services.authentication.info.AuthInfo;
import org.esupportail.cookingapp.domain.beans.AuthUser;

/**
 * @author llevague
 *
 */
public class AuthenticatorImpl implements Authenticator {
	
	@Inject
	@SuppressWarnings({"unused"})
	private DomainService domainService;
	
	@Inject
	@Named("${auth.bean}")
	private AuthenticationService authenticationService;
	
	@Override
	public AuthUser getUser() {
		AuthUser user = new AuthUser(); 
		AuthInfo authInfo = authenticationService.getAuthInfo();
		if (AuthUtils.CAS.equals(authInfo.getType())) {
			user.withLogin(authInfo.getId());
		}
		return user;
	}

}
