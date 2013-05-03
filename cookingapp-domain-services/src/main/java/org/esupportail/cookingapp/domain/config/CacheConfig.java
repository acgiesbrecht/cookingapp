package org.esupportail.cookingapp.domain.config;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Lazy
public class CacheConfig {

	@Bean
	public EhCacheManagerFactoryBean cacheManager() {
		EhCacheManagerFactoryBean cm = new EhCacheManagerFactoryBean();
		cm.setConfigLocation(new ClassPathResource("META-INF/cache/ehcache.xml"));
		return cm;
	}
}
