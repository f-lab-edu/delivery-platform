package org.flab.deliveryplatform.server.config;

import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@Configuration
public class DataSourceConfiguration {

    private final JpaProperties jpaProperties;

    public DataSourceConfiguration(JpaProperties jpaProperties) {
        this.jpaProperties = jpaProperties;
    }

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routingDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
                    return DataSourceType.SLAVE;
                }
                return DataSourceType.MASTER;
            }
        };

        Map<Object, Object> targetDataSources = Map.of(
            DataSourceType.MASTER, masterDataSource,
            DataSourceType.SLAVE, slaveDataSource
        );

        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        return routingDataSource;
    }

    @Bean
    public DataSource dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
        DataSource dataSource, ConfigurableListableBeanFactory beanFactory) {
        EntityManagerFactoryBuilder entityManagerFactoryBuilder = createEntityManagerFactoryBuilder(
            jpaProperties);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = entityManagerFactoryBuilder
            .dataSource(dataSource)
            .packages("org.flab.deliveryplatform")
            .build();

        Map<String, Object> jpaPropertyMap = entityManagerFactory.getJpaPropertyMap();
        jpaPropertyMap.put(AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(beanFactory));

        return entityManagerFactory;
    }

    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(
        JpaProperties jpaProperties) {
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(vendorAdapter, jpaProperties.getProperties(), null);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
