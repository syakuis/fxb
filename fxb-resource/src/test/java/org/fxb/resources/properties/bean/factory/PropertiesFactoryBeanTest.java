package org.fxb.resources.properties.bean.factory;

import java.util.Properties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PropertiesConfiguration.class)
@WebAppConfiguration
@TestPropertySource(locations = {
    "/org/fxb/resources/properties/first.properties",
    "/org/fxb/resources/properties/second.properties"
})
public class PropertiesFactoryBeanTest {

  @Autowired
  private Environment environment;

  @Autowired
  private Properties config;

  @Autowired
  @Qualifier("stringPathConfig")
  private Properties stringPathConfig;

  @Autowired
  @Qualifier("profileConfig")
  private Properties profileConfig;

  @Test
  public void test() {
    // locations 자료형을 배열과 문자로 했을 때 두 결과가 같은지 판단한다.
    Assert.assertTrue(config.equals(stringPathConfig));
    // Spring PropertySource 과 비교한다.
    Assert.assertEquals(config.getProperty("name"), environment.getProperty("name"));
    // profile 사용하여 프로퍼티를 잘 읽어오는 지 판단한다.
    Assert.assertEquals(profileConfig.getProperty("name"), "test");
  }
}
