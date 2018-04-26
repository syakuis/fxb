package org.fxb.configure;

import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfiguration.class)
public class JDBCBootstrapTest {

  @Autowired
  private DataSource dataSource;

  @Test
  public void test() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

  }
}

@Configuration
class DataInitialization {
  @Bean
  public EmbeddedDatabaseBuilder embeddedDatabaseBuilder() {
    new EmbeddedDatabaseBuilder().set
  }
}
