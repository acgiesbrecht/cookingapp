package org.esupportail.cookingapp.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:/META-INF/cookingapp-dao-dao.xml",
		"classpath*:/META-INF/cookingapp-domain-services-domain.xml"})
public class DomainConfig {
}
