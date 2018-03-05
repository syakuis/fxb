package org.fxb.module;

import javax.sql.DataSource;
import org.fxb.context.config.ConfigConfiguration;
import org.fxb.context.config.DataSourceConfiguration;
import org.fxb.context.config.MyBatisConfiguration;
import org.fxb.context.db.bean.factory.PopulatorDataSourceInitializer;
import org.fxb.module.config.ModuleConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
    ConfigConfiguration.class,
    DataSourceConfiguration.class,
    ModuleInitConfiguration.class,
    MyBatisConfiguration.class,
    ModuleConfiguration.class
})
@ActiveProfiles({ "test", "mybatis" })
public class TestConfiguration {

  @Autowired
  private DataSource dataSource;

  @Before
  public void prepared() {
    PopulatorDataSourceInitializer dataSourceInitializer = new PopulatorDataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setAfterResources("org/fxb/module/schemas/module.data.h2.sql");
    dataSourceInitializer.afterPropertiesSet();
  }

}
