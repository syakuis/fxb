package org.fxb.config;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fxb.commons.io.MultiplePathMatchingResourcePatternResolver;
import org.fxb.config.support.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  private DataSource dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    String type = config.getString("dataSource.type");
    Assert.hasText(type, "'name' must not be empty");

    String configLocation = config.getString("mybatis.configLocation");
    String[] defaultMapperLocations = StringUtils.tokenizeToStringArray(config.getString("default.mybatis.mapperLocations"), ",");
    String[] mapperLocations = StringUtils.tokenizeToStringArray(config.getString("mybatis.mapperLocations"), ",");
    if (mapperLocations.length > 0) {
      defaultMapperLocations = ArrayUtils.addAll(defaultMapperLocations, mapperLocations);
    }

    String[] mappers = new String[ defaultMapperLocations.length ];
    for (int i = 0; i < defaultMapperLocations.length; i++) {
      mappers[i] = String.format(defaultMapperLocations[i], type);
    }

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);

    MultiplePathMatchingResourcePatternResolver pathResourcePatternResolver = new MultiplePathMatchingResourcePatternResolver();
    Resource[] resources = pathResourcePatternResolver.getResources(mappers);
    sqlSessionFactoryBean.setMapperLocations(resources);

    if (!"".equals(configLocation)) {
      sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("\ndataSource type : {}\nmyBatis configLocation : {}\nmyBatis mapperLocations : {}",
        type,
        configLocation,
        StringUtils.arrayToCommaDelimitedString(mappers));
    }

    return sqlSessionFactoryBean.getObject();
  }
}