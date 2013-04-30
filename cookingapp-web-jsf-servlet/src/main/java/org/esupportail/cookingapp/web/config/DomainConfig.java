package org.esupportail.cookingapp.web.config;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.esupportail.cookingapp.dao.config.DaoConfig;
import org.esupportail.cookingapp.domain.DomainService;
import org.esupportail.cookingapp.domain.DomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Lazy
@EnableTransactionManagement
@Import(DaoConfig.class)
public class DomainConfig {
	
	private final String ES_CONFIG_FILE = "es.properties";
	
	@Bean
	public DomainService domainService() {
		return new DomainServiceImpl();
	}
	
	@Bean
	public Node esNode() {
		final Settings defaultSettings = settingsBuilder()
				.loadFromClasspath(ES_CONFIG_FILE).build();
		return nodeBuilder().settings(defaultSettings)
				.build();
	}
	
	@Bean(destroyMethod="close")
	public Client esClient() {
		return esNode().start().client();
	}
	
	
}
