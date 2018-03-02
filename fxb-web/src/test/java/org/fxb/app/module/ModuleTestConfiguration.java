package org.fxb.app.module;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.fxb.boot.Bootstrapping;
import org.fxb.context.db.bean.factory.PopulatorDataSourceInitializer;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Bootstrapping.class)
@ActiveProfiles({ "test", "mybatis" })
@Transactional
public class ModuleTestConfiguration {
  private final Logger logger = LoggerFactory.getLogger(ModuleTestConfiguration.class);
  private StopWatch watch = new StopWatch();

  @Configuration
  static class ContextConfiguration {
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

  @Autowired
  DataSource dataSource;

  @Before
  public void start() {
    PopulatorDataSourceInitializer dataSourceInitializer = new PopulatorDataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setAfterResources("org/fxb/app/module/schemas/module.data.h2.sql");
    dataSourceInitializer.afterPropertiesSet();
    watch.start("test method");
  }

  @After
  public void end() {
    watch.stop();
    logger.debug(watch.toString());
  }
}
