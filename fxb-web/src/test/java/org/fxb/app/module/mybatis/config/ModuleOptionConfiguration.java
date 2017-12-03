package org.fxb.app.module.mybatis.config;

import org.fxb.context.db.bean.factory.PopulatorDataSourceInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 29.
 */
@Configuration
public class ModuleOptionConfiguration {
  @Autowired
  @Qualifier("fxbDataSource")
  DataSource dataSource;

  @PostConstruct
  public void init() throws Exception {
    new PopulatorDataSourceInitializer(
      dataSource,
      "org/fxb/app/module/schemas/moduleOption.table.h2.sql"
    ).afterPropertiesSet();
  }
}
