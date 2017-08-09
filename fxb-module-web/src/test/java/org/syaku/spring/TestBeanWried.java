package org.syaku.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.syaku.spring.beans.TestBean;
import org.syaku.spring.configuration.TestBeanConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestBeanConfiguration.class)
public class TestBeanWried {
  @Autowired
  TestBean testBean;

  @Test
  public void test() {
    testBean.test();
  }
}
