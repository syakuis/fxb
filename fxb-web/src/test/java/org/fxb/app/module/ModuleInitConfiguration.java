package org.fxb.app.module;

import javax.sql.DataSource;
import org.fxb.context.db.bean.factory.PopulatorDataSourceInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 6.
 */
@Configuration
public class ModuleInitConfiguration {
  @Autowired
  @Qualifier("fxbDataSource")
  DataSource dataSource;

  @Bean
  public DataSourceInitializer dataSourceInitializer() {
    PopulatorDataSourceInitializer dataSourceInitializer = new PopulatorDataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setBeforeResources(
      "org/fxb/app/module/schemas/module.table.h2.sql",
      "org/fxb/app/module/schemas/moduleOption.table.h2.sql");
    return dataSourceInitializer;
  }
}
