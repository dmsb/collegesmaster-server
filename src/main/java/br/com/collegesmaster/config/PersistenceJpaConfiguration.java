package br.com.collegesmaster.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
public class PersistenceJpaConfiguration {

	@Autowired
	private Environment env;
	
	@Value("classpath:/sql/spring_oauth.sql")
	private Resource oauthSchemaScript;
	
	private DatabasePopulator databasePopulator() {
	    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    populator.addScript(oauthSchemaScript);
	    return populator;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
	    DataSourceInitializer initializer = new DataSourceInitializer();
	    initializer.setDataSource(dataSource);
	    initializer.setDatabasePopulator(databasePopulator());
	    return initializer;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(this.dataSource());
		em.setPackagesToScan(new String[] { "br.com.collegesmaster.*.model" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(persistenceProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("javax.persistence.jdbc.driver"));
		dataSource.setUrl(env.getProperty("javax.persistence.jdbc.url"));
		dataSource.setUsername(env.getProperty("javax.persistence.jdbc.user"));
		dataSource.setPassword(env.getProperty("javax.persistence.jdbc.password"));
		return dataSource;
	}

	private final Properties persistenceProperties() {
		Properties persistenceProperties = new Properties();
		persistenceProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		persistenceProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		persistenceProperties.setProperty("hibernate.show_sql", "true");

		return persistenceProperties;
	}

}
