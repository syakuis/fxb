package org.fxb.configure.hibernate;

import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.lang3.ArrayUtils;
import org.fxb.configure.DataSourceConfiguration;
import org.fxb.resources.properties.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 14.
 */
@Configuration
@EnableTransactionManagement
@Import(DataSourceConfiguration.class)
public class EntityManagerConfiguration {
  @Autowired
  private Config config;

  @Autowired
  private DataSource dataSource;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setDataSource(dataSource);

    bean.setPackagesToScan(
        ArrayUtils.add(config.getStringArray("hibernate.packageToScan"),
            config.getString("fxb.hibernate.packageToScan")));

    bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

    List<String> keys = config.getKeys("hibernate.properties.");

    if (!keys.isEmpty()) {
      bean.setJpaProperties(hibernateProperties(keys));
    }

    return bean;
  }

  private Properties hibernateProperties(List<String> keys) {
    Properties properties = new Properties();

    for (String key : keys) {
      properties.setProperty(key.replace(".properties.", "."),
          config.getString(key));
    }

    return properties;
  }

  @Bean
  @Primary
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
    return transactionManager;
  }

}
