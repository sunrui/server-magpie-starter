package com.honeysense.magpie.framework.spring.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.honeysense.magpie", transactionManagerRef = "jpaTransactionManager")
public class MagpieJpaConfig {
    @Autowired
    private PlatformTransactionManager jpaTransactionManager;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendor = new HibernateJpaVendorAdapter();
        jpaVendor.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(jpaVendor);
        entityManagerFactory.setPackagesToScan("com.honeysense.magpie");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() {
        Properties properties = new Properties();
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception|MagpieException");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");

        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(jpaTransactionManager);
        transactionInterceptor.setTransactionAttributes(properties);
        return transactionInterceptor;
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}