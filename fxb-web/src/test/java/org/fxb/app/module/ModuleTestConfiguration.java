package org.fxb.app.module;

import javax.sql.DataSource;
import org.fxb.boot.Bootstrapping;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StopWatch;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Bootstrapping.class})
@ActiveProfiles({ "test", "mybatis" })
public class ModuleTestConfiguration {
  private final Logger logger = LoggerFactory.getLogger(ModuleTestConfiguration.class);
  private StopWatch watch = new StopWatch();

  @Autowired
  @Qualifier("fxbDataSource")
  DataSource dataSource;

  @Before
  public void before() {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(new ClassPathResource("org/fxb/app/module/schemas/module.data.h2.sql"));
    DatabasePopulatorUtils.execute(populator, dataSource);
    watch.start("test method");
  }

  @After
  public void after() {
    watch.stop();
    logger.debug(watch.toString());
  }
}
