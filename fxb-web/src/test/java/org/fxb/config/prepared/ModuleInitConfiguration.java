package org.fxb.config.prepared;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.fxb.context.db.bean.factory.PopulatorDataSourceInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 6.
 */
public class ModuleInitConfiguration {
  @Autowired
  DataSource dataSource;

  @PostConstruct
  public void dataSourceInitializer() {
    PopulatorDataSourceInitializer dataSourceInitializer = new PopulatorDataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setBeforeResources(
      "org/fxb/app/module/schemas/module.table.h2.sql",
      "org/fxb/app/module/schemas/moduleOption.table.h2.sql");
    dataSourceInitializer.afterPropertiesSet();
  }
}
