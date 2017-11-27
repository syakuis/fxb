package org.fxb.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fxb.commons.io.MultiplePathMatchingResourcePatternResolver;
import org.fxb.config.support.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 31.
 */
@Configuration
@Profile("mybatis")
@EnableTransactionManagement
@MapperScan(basePackages = "org.fxb.app", annotationClass = Mapper.class)
public class MyBatisConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

  @Autowired
  private Config config;

  @Autowired
  @Qualifier("fxbDataSource")
  private DataSource dataSource;

  private SqlSessionFactory sqlSessionFactory;
  private DatabaseIdProvider databaseIdProvider;

  @Bean("fxbDatabaseIdProvider")
  public DatabaseIdProvider databaseIdProvider() {
    return databaseIdProvider;
  }

  @Bean("fxbSqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    databaseIdProvider = new VendorDatabaseIdProvider();
    databaseIdProvider.setProperties(config.getProperties("myBatis.providers."));

    String configLocation = config.getString("default.myBatis.configLocation");
    String[] mapperLocations = StringUtils.tokenizeToStringArray(config.getString("default.myBatis.mapperLocations"), ",");

    Assert.notEmpty(mapperLocations, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");

    MultiplePathMatchingResourcePatternResolver pathResourcePatternResolver = new MultiplePathMatchingResourcePatternResolver();
    Resource[] resources = pathResourcePatternResolver.getResources(mapperLocations);

    Assert.notEmpty(resources, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(resources);
    sqlSessionFactoryBean.setTypeAliasesPackage(config.getString("default.myBatis.basePackage"));
    sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider);

    if (!"".equals(configLocation)) {
      sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
    }

    this.sqlSessionFactory = sqlSessionFactoryBean.getObject();

    if (logger.isDebugEnabled()) {
      logger.debug("\ndatabaseIdProvider: {}\nmyBatis configLocation : {}\nmyBatis mapperLocations : {}",
        databaseIdProvider.getDatabaseId(dataSource),
        configLocation,
        StringUtils.arrayToCommaDelimitedString(mapperLocations));
    }

    return this.sqlSessionFactory;
  }

  @Bean("fxbSqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate() {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}