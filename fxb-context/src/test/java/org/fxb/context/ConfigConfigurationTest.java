package org.fxb.context;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;
import org.fxb.config.Config;
import org.fxb.context.config.ConfigConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ConfigConfiguration.class)
@ActiveProfiles("test")
@TestPropertySource("/config-test.properties")
public class ConfigConfigurationTest {
  @Autowired
  Config config;

  @Autowired
  private Environment env;

  private String[] getStringArray(String key) {
    return StringUtils.delimitedListToStringArray(env.getProperty(key), ",");
  }

  private List<String> getList(String key) {
    return Arrays.asList(getStringArray(key));
  }

  @Test
  public void test() {
    String[] stringArray = config.getArray("test.list");
    List<String> stringList = config.getList("test.stringArray");

    Assert.assertEquals(config.getString("test.string"), env.getProperty("test.string"));
    Assert.assertArrayEquals(config.getStringArray("test.stringArray"), this.getStringArray("test.stringArray"));
    Assert.assertArrayEquals(stringArray, new String[]{ "a", "b b", "c" });
    Assert.assertThat(stringList, is(getList("test.stringArray")));
    Assert.assertTrue(config.getBoolean("test.boolean"));
    Assert.assertEquals(config.getLong("test.number"), 1L);
    Assert.assertEquals(config.getInt("test.number"), 1);
  }

}
