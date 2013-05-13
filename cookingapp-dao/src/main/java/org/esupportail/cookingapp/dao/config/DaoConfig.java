package org.esupportail.cookingapp.dao.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.esupportail.cookingapp.dao.DaoService;
import org.esupportail.cookingapp.dao.JpaDaoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Lazy
@Import({JdbcDataSourceConfig.class, JndiDataSourceConfig.class})
public class DaoConfig {
	
	private final String[] PACKAGES_TO_SCAN = new String[]{"org.esupportail.cookingapp.domain.beans"}; 
	
	@Value("${jpa.database.type}")
	private String databaseType;

	@Value("#{systemProperties[generateDdl]?:false}")
	private boolean generateDdl;
	
	@Value("${hibernate.show_sql}")
	private boolean showSql;
	
	@Value("${hibernate.format_sql}")
	private boolean formatSql;
	
	@Value("${hibernate.use_sql_comments}")
	private boolean useSqlComments;

	@Resource(name="${datasource.bean}")
	private DataSource dataSource;

	@Bean
	public BeanPostProcessor beanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	public DaoService daoService() {
		return new JpaDaoService();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaProperties(jpaProperties());
		factoryBean.setJpaVendorAdapter(vendorAdapter());
		factoryBean.setDataSource(dataSource);
		factoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
		return factoryBean;
	}

	private Properties jpaProperties() {
		Properties props = new Properties();
		props.put("hibernate.cache.provider_class",
				"org.hibernate.cache.NoCacheProvider");
		props.put("hibernate.cache.use_query_cache", false);
		props.put("hibernate.cache.use_second_level_cache", false);
		props.put("hibernate.show_sql", showSql);
		props.put("hibernate.format_sql", formatSql);
		props.put("hibernate.use_sql_comments", useSqlComments);
		return props;
	}

	private JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(generateDdl);
		adapter.setDatabase(Database.valueOf(databaseType));
		return adapter;
	}
}
