package org.fxb.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 9. 7.
 */
@Configuration
public class DataSourceConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

  private final static String propertiesName = "dataSource.";

  private DataSource dataSource;

  @Autowired
  private Config config;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private BasicDataSource getDataSource() {
    Assert.notNull(config, "config is null!!!");
    BasicDataSource dataSource = new BasicDataSource();

    String driverClassName = config.getString("dataSource.driverClassName");
    String url = config.getString("dataSource.url");
    String username = config.getString("dataSource.username");
    String password = config.getString("dataSource.password");

    Integer initialSize = config.getInteger("dataSource.initialSize");

    Integer maxTotal = config.getInteger("dataSource.maxTotal");
    Integer maxIdle = config.getInteger("dataSource.maxIdle");
    Integer minIdle = config.getInteger("dataSource.minIdle");
    Integer maxWaitMills = config.getInteger("dataSource.maxWaitMills");
    Boolean testOnBorrow = config.getBool("dataSource.testOnBorrow");
    Boolean testOnReturn = config.getBool("dataSource.testOnReturn");
    Long timeBetweenEvictionRunsMillis = config.getLonger("dataSource.timeBetweenEvictionRunsMillis");
    Integer numTestsPerEvictionRun = config.getInteger("dataSource.numTestsPerEvictionRun");
    Long minEvictableIdleTimeMillis = config.getLonger("dataSource.minEvictableIdleTimeMillis");
    Boolean testWhileIdle = config.getBool("dataSource.testWhileIdle");

    String validationQuery = config.getString("dataSource.validationQuery");
    Integer validationQueryTimeout = config.getInteger("dataSource.validationQueryTimeout");
    String connectionInitSqls = config.getString("dataSource.connectionInitSqls");
    Boolean defaultReadOnly = config.getBool("dataSource.defaultReadOnly");
    Boolean defaultAutoCommit = config.getBool("dataSource.defaultAutoCommit");
    Integer defaultTransactionIsolation = config.getInteger("dataSource.defaultTransactionIsolation");
    String defaultCatalog = config.getString("dataSource.defaultCatalog");

    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);

    if (initialSize != null) {
      dataSource.setInitialSize(initialSize.intValue());
    }

    if (maxTotal != null) {
      dataSource.setMaxTotal(maxTotal);
    }

    if (maxIdle != null) {
      dataSource.setMinIdle(maxIdle.intValue());
    }

    if (minIdle != null) {
      dataSource.setMinIdle(minIdle.intValue());
    }

    if (maxWaitMills != null) {
      dataSource.setMaxWaitMillis(maxWaitMills.intValue());
    }

    if (testOnBorrow != null) {
      dataSource.setTestOnBorrow(testOnBorrow.booleanValue());
    }

    if (testOnReturn != null) {
      dataSource.setTestOnReturn(testOnReturn.booleanValue());
    }

    if (timeBetweenEvictionRunsMillis != null) {
      dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis.longValue());
    }

    if (numTestsPerEvictionRun != null) {
      dataSource.setNumTestsPerEvictionRun(numTestsPerEvictionRun.intValue());
    }

    if (minEvictableIdleTimeMillis != null) {
      dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis.longValue());
    }

    if (testWhileIdle != null) {
      dataSource.setTestWhileIdle(testWhileIdle.booleanValue());
    }

    if (validationQuery != null) {
      dataSource.setValidationQuery(validationQuery);
    }
    if (validationQueryTimeout != null) {
      dataSource.setValidationQueryTimeout(validationQueryTimeout.intValue());
    }
    if (connectionInitSqls != null) {
      String[] connectionInitSqlsArray = StringUtils.tokenizeToStringArray(connectionInitSqls, ",");
      dataSource.setConnectionInitSqls(Arrays.asList(connectionInitSqlsArray));
    }
    if (defaultReadOnly != null) {
      dataSource.setDefaultReadOnly(defaultReadOnly);
    }
    if (defaultAutoCommit != null) {
      dataSource.setDefaultAutoCommit(defaultAutoCommit);
    }
    if (defaultTransactionIsolation != null) {
      dataSource.setDefaultTransactionIsolation(defaultTransactionIsolation.intValue());
    }
    if (defaultCatalog != null) {
      dataSource.setDefaultCatalog(defaultCatalog);
    }

    return dataSource;
  }

  @Bean(name = "fxbDataSource")
  public DataSource dataSource() {
    if (dataSource == null) dataSource = this.getDataSource();

    if (logger.isDebugEnabled()) {
      List<String> names = config.getKeys(propertiesName);
      for (String name : names) {
        logger.debug("><>< {} : {}", name, config.getString(name));
      }
    }

    return dataSource;
  }

  @Bean("fxbTransactionManager")
  public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }
}