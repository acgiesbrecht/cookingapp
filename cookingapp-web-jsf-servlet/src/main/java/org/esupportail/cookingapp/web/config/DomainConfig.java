package org.esupportail.cookingapp.web.config;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.esupportail.cookingapp.domain.DomainService;
import org.esupportail.cookingapp.domain.DomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Lazy
@EnableTransactionManagement
@ImportResource({"classpath*:/META-INF/cookingapp-dao-dao.xml"})
public class DomainConfig {
	
	private final String ES_CONFIG_FILE = "es.properties";
	
	@Bean
	public DomainService domainService() {
		return new DomainServiceImpl();
	}
	
	@Bean
	public Node esNode() {
		final Settings settings = settingsBuilder()
				.loadFromClasspath(ES_CONFIG_FILE).build();
		return nodeBuilder().settings(settings).build();
	}
	
	@Bean
	public Client esClient() {
		return esNode().client();
	}
	
	
}
