package org.esupportail.cookingapp.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("org.esupportail.cookingapp")
@ImportResource({"classpath*:/META-INF/cookingapp-dao-dao.xml",
		"classpath*:/META-INF/cookingapp-domain-services-domain.xml"})
public class DaoConfig {
//	
//	@Inject
//	@Resource(name="${datasource.bean}")
//	private DataSource dataSource;
//	
//	@Bean
//	public PersistenceAnnotationBeanPostProcessor persitenceProcessor() {
//		return new PersistenceAnnotationBeanPostProcessor();
//	}
//	
////	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//		entityManagerFactory.setDataSource(dataSource);
//		entityManagerFactory.setPersistenceXmlLocation("classpath*:META-INF/cookingapp-dao-persistence.xml");
//		
//		return entityManagerFactory;
//	}
	
}
