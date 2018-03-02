package org.fxb.config.context;

import javax.sql.DataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fxb.commons.io.MultiplePathMatchingResourcePatternResolver;
import org.fxb.config.Config;
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
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 31.
 */
@Configuration
@Profile("mybatis")
@MapperScan(
    basePackages = "org.fxb.app",
    annotationClass = Mapper.class,
    sqlSessionFactoryRef = "sqlSessionFactory"
)
public class MyBatisConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

  @Autowired
  private Config config;

  @Autowired
  private DataSource dataSource;

  @Bean
  public DatabaseIdProvider databaseIdProvider() {
    return databaseIdProvider;
  }

  @Autowired
  private DatabaseIdProvider databaseIdProvider;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
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

    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

    if (logger.isDebugEnabled()) {
      logger.debug("\ndatabaseIdProvider: {}\nmyBatis configLocation : {}\nmyBatis mapperLocations : {}",
        databaseIdProvider.getDatabaseId(dataSource),
        configLocation,
        StringUtils.arrayToCommaDelimitedString(mapperLocations));
    }

    return sqlSessionFactory;
  }


  @Autowired
  private SqlSessionFactory sqlSessionFactory;

  @Bean
  public SqlSessionTemplate sqlSessionTemplate() {
    return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
  }
}