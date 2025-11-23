package com.example.jobservice.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.jobservice.cv.repository",
        entityManagerFactoryRef = "cvEntityManagerFactory",
        transactionManagerRef = "cvTransactionManager"
)
public class CvDataSourceConfig {

    @Bean
    @Primary
    public DataSource cvDataSource(
            @Value("${spring.datasource.cv.url}") String url,
            @Value("${spring.datasource.cv.username}") String username,
            @Value("${spring.datasource.cv.password}") String password,
            @Value("${spring.datasource.cv.driver-class-name}") String driverClassName) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "cvEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean cvEntityManagerFactory(
            @Qualifier("cvDataSource") DataSource cvDataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(cvDataSource);
        em.setPackagesToScan("com.example.jobservice.cv.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", true);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "cvTransactionManager")
    @Primary
    public PlatformTransactionManager cvTransactionManager(
            @Qualifier("cvEntityManagerFactory") LocalContainerEntityManagerFactoryBean cvEntityManagerFactory) {

        return new JpaTransactionManager(cvEntityManagerFactory.getObject());
    }
}
