package org.yq.tool.yktools.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
        entityManagerFactoryRef = "devEntityManager",
        transactionManagerRef = "transactionManager",
        basePackages = {"org.yq.tool.yktools.repository.dev"}
)
@EnableConfigurationProperties(DevDBConfig.class)
public class DevJPAConfig {

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    public DevDBConfig config;

    // 配置数据源
    @Bean(name = "devDS")
    @Primary
    public DataSource devDS() throws SQLException {
        // 新建数据源，并将数据源配置信息装置
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(config.getUrl());
        mysqlXADataSource.setUser(config.getUsername());
        mysqlXADataSource.setPassword(config.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        // 将事物信息交给atomikos进行统一管理
        AtomikosDataSourceBean xDataSource = new AtomikosDataSourceBean();
        xDataSource.setXaDataSource(mysqlXADataSource); // 将数据源信息放入到atomikos容器中
        xDataSource.setUniqueResourceName("devDS");
        // 设置数据源其他参数信息
        xDataSource.setMinPoolSize(config.getMinPoolSize());
        xDataSource.setMaxPoolSize(config.getMaxPoolSize());
        xDataSource.setMaxLifetime(config.getMaxLifetime());
        xDataSource.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
        xDataSource.setLoginTimeout(config.getLoginTimeout());
        xDataSource.setMaintenanceInterval(config.getMaintenanceInterval());
        xDataSource.setMaxIdleTime(config.getMaxIdleTime());
        xDataSource.setTestQuery(config.getTestQuery());
        return xDataSource;
    }

    @Bean(name = "devEntityManager")
    @DependsOn("transactionManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean devEntityManager() throws Throwable {

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(devDS());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("org.yq.tool.yktools.entity.dev");
        entityManager.setPersistenceUnitName("devPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }



}
