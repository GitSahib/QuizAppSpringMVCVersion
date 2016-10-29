package com.mems.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan(basePackages = "com.mems")
@EnableJpaRepositories("com.mems.repository")
@PropertySource("classpath:datasource-cfg.properties")
class AppConfig {
	// The Environment class serves as the property holder
	// and stores all the properties loaded by the @PropertySource
	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// See: datasouce-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		System.out.println("## getDataSource: " + dataSource);

		return dataSource;
	}

	@Bean(name = "transactionManager")
	JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setDataSource(getDataSource());
		return txManager;
	}

	@Bean(name = "persistenceExceptionTranslationPostProcessor")
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/*@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}*/
	@Bean HibernateJpaVendorAdapter hjva()
	{
		return new HibernateJpaVendorAdapter();
	}
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(getDataSource());
		entityManager.setPackagesToScan("com.mems.domain");
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		prop.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.setProperty("hibernate.hbm2ddl.import_files", env.getProperty("hibernate.hbm2ddl.import_files"));
		prop.setProperty("hibernate.hbm2ddl.show-sql", env.getProperty("hibernate.hbm2ddl.show-sql"));
		entityManager.setJpaProperties(prop);
		entityManager.setJpaVendorAdapter(hjva());
		return  entityManager;
	}
}