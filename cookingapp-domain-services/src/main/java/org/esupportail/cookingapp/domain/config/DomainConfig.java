package org.esupportail.cookingapp.domain.config;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.esupportail.cookingapp.dao.config.DaoConfig;
import org.esupportail.cookingapp.domain.services.IngredientService;
import org.esupportail.cookingapp.domain.services.IngredientServiceImpl;
import org.esupportail.cookingapp.domain.services.RecipeService;
import org.esupportail.cookingapp.domain.services.RecipeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@Lazy
@EnableTransactionManagement
@Import(DaoConfig.class)
public class DomainConfig {
	
	private final String DEFAULT_ES_CONFIG_FILE = "es.properties";

	@Bean
	public IngredientService ingredientService() {
		return new IngredientServiceImpl();
	}
	@Bean
	public RecipeService recipeService() {
		return new RecipeServiceImpl();
	}
	
	@Lazy(false)
	@Bean(destroyMethod="close")
	public Node esNode() {
		final Settings defaultSettings = settingsBuilder()
				.loadFromClasspath(DEFAULT_ES_CONFIG_FILE).build();
		return nodeBuilder().settings(defaultSettings).build();
	}
	
	@Bean
	public Client esClient() {
		return esNode().start().client();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	
}
