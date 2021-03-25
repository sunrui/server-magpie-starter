package com.honeysense.magpie.framework.spring.config;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.honeysense.magpie", transactionManagerRef = "jpaTransactionManager")
public class MagpieJpaConfig {
//    @Autowired
//    private TransactionManager transactionManager;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter japVendor = new HibernateJpaVendorAdapter();
        japVendor.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(japVendor);
        entityManagerFactory.setPackagesToScan("com.honeysense.magpie");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

//
//    @Bean
//    public TransactionInterceptor txAdvice() {
//        /* 事务管理规则，声明具备事务管理的方法名。 */
//        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
//        /* 只读事务，不做更新操作。 */
//        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
//        readOnlyTx.setReadOnly(true);
//        /* transaction definition 定义事务的隔离级别 PROPAGATION_NOT_SUPPORTED 事务传播级别 5，以非事务运行，如果当前存在事务，则把当前事务挂起。 */
//        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
//        /* 当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务。 */
//        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
//        /* 抛出异常后执行切点回滚。 */
//        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
//        /* PROPAGATION_REQUIRED:事务隔离性为 1，若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。 */
//        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        /* 设置事务失效时间，如果超过 5 秒，则回滚事务。 */
//        requiredTx.setTimeout(5000);
//        Map<String, TransactionAttribute> txMap = new HashMap<>();
//
//        txMap.put("save*", requiredTx);
//        txMap.put("remove*", requiredTx);
//        txMap.put("update*", requiredTx);
//        txMap.put("batch*", requiredTx);
//        txMap.put("clear*", requiredTx);
//        txMap.put("add*", requiredTx);
//        txMap.put("append*", requiredTx);
//        txMap.put("modify*", requiredTx);
//        txMap.put("edit*", requiredTx);
//        txMap.put("insert*", requiredTx);
//        txMap.put("delete*", requiredTx);
//        txMap.put("do*", requiredTx);
//        txMap.put("create*", requiredTx);
//        /* select,count开头的方法,开启只读,提高数据库访问性能。 */
//        txMap.put("select*", readOnlyTx);
//        txMap.put("get*", readOnlyTx);
//        txMap.put("valid*", readOnlyTx);
//        txMap.put("list*", readOnlyTx);
//        txMap.put("count*", readOnlyTx);
//        txMap.put("find*", readOnlyTx);
//        txMap.put("load*", readOnlyTx);
//        txMap.put("search*", readOnlyTx);
//        txMap.put("pop*", readOnlyTx);
//        txMap.put("*", requiredTx);
//
//        source.setNameMap(txMap);
//        return new TransactionInterceptor(transactionManager, source);
//    }
//
//    @Bean
//    public Advisor txAdviceAdvisor() {
//        /* 声明切点的面：切面就是通知和切入点的结合。通知和切入点共同定义了关于切面的全部内容——它的功能、在何时和何地完成其功能。 */
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        /* 声明和设置需要拦截的方法,用切点语言描写。 */
//        pointcut.setExpression("execution (* com..service..*.*(..))");
//        /* 设置切面 = 切点 pointcut+ 通知 TxAdvice。*/
//        return new DefaultPointcutAdvisor(pointcut, txAdvice());
//    }
//
//    @Pointcut("execution (* com..service..*.*(..))")
//    public void logPointCut() {
//    }
//
//    @Around("logPointCut()")
//    public Object around(ProceedingJoinPoint point) {
//        long beginTime = System.currentTimeMillis();
//        // 执行方法
//        try {
//            Object result = point.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        // 执行时长(毫秒)
//        long time = System.currentTimeMillis() - beginTime;
//        // 保存日志
//        log.trace(time + "");
//
//        return "";
//    }
}
