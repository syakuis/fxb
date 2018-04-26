package org.fxb.configure;

import java.util.Iterator;
import org.fxb.resources.properties.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PropertiesConfiguration.class)
public class BootstrapTest {
  @Autowired
  private Config config;

  @Test
  public void test() {
    Iterator iterator = config.getProperties().entrySet().iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
