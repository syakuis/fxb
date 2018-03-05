package org.fxb.context;

import javax.sql.DataSource;
import org.fxb.context.config.ConfigConfiguration;
import org.fxb.context.config.DataSourceConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ConfigConfiguration.class, DataSourceConfiguration.class })
@ActiveProfiles("test")
public class DataSourceConfigurationTest {

  @Autowired
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplate;

  @Before
  public void before() {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Test
  @SqlGroup({
      @Sql("/org/fxb/context/schemas/test.table.h2.sql"),
      @Sql("/org/fxb/context/schemas/test.data.h2.sql")
  })
  public void test() {
    Assert.assertTrue(JdbcTestUtils.countRowsInTable(jdbcTemplate, "test") == 5);
  }
}
