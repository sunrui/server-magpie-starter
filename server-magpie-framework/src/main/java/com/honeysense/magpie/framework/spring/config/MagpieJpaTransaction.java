//package com.honeysense.magpie.framework.spring.config;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.interceptor.TransactionInterceptor;
//
//import java.util.Properties;
//
//@Configuration
//public class MagpieJpaTransaction {
//    private DataSourceTransactionManager transactionManager;
//
//    @Autowired
//    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }
//
//    // 创建事务通知
//    @Bean(name = "txAdvice")
//    public TransactionInterceptor getAdvisor() {
//        Properties properties = new Properties();
//        properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
//        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//        return new TransactionInterceptor(transactionManager, properties);
//    }
//
//    @Bean
//    public BeanNameAutoProxyCreator txProxy() {
//        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
//        creator.setInterceptorNames("txAdvice");
//        creator.setBeanNames("*Service", "*ServiceImpl");
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
//}
