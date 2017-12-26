package org.fxb.app.module.mybatis.config;

import org.fxb.context.db.bean.factory.PopulatorDataSourceInitializer;
import org.fxb.web.module.ModuleContext;
import org.fxb.web.module.ModuleContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 29.
 */
@Configuration
public class ModuleConfiguration {
  @Autowired
  @Qualifier("fxbDataSource")
  DataSource dataSource;

  @PostConstruct
  public void init() throws Exception {
    new PopulatorDataSourceInitializer(
      dataSource,
      "org/fxb/app/module/schemas/module.table.h2.sql"
    ).afterPropertiesSet();
  }

  @Resource(name = "myBatisModuleContextService")
  private ModuleContextService moduleContextService;

  @Bean
  public ModuleContext moduleContext() {
    return new ModuleContext(moduleContextService);
  }
}
