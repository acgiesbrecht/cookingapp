package org.esupportail.cookingapp.domain.config;

//import org.esupportail.commons.services.authentication.AuthenticationService;
//import org.esupportail.commons.services.authentication.CasFilterAuthenticationService;
//import org.esupportail.commons.services.authentication.CasifiedPortalAuthenticationService;
//import org.esupportail.commons.services.authentication.OfflineFixedUserAuthenticationService;
import org.esupportail.cookingapp.domain.beans.AuthUser;
import org.esupportail.cookingapp.domain.services.Authenticator;
import org.esupportail.cookingapp.domain.services.AuthenticatorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
public class AuthConfig {
	
//	@Value("${auth.portal.uidAttribute}")
//	private String portalUidAttribute;
//	
//	@Bean
//	@Scope("session")
//	public AuthUser authenticatedUser() {
//		return authenticator().getUser();
//	}
//	
//	@Bean
//	public Authenticator authenticator() {
//		return new AuthenticatorImpl();
//	}
//	
//
//	@Bean
//	public AuthenticationService servletAuthenticationService() {
//		return new CasFilterAuthenticationService();
//	}
//	
//	@Bean
//	public AuthenticationService portletAuthenticationService() {
//		CasifiedPortalAuthenticationService service = new CasifiedPortalAuthenticationService();
//		service.setUidPortalAttribute(portalUidAttribute);
//		return service;
//	}
//	
//	@Bean
//	public AuthenticationService offlineFixedUserAuthenticationService() {
//		OfflineFixedUserAuthenticationService service = new OfflineFixedUserAuthenticationService();
//		service.setAuthId("loginuser");
//		service.setAuthType("cas");
//		return service;
//	}

}
