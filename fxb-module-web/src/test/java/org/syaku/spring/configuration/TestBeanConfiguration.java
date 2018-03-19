package org.syaku.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.syaku.spring.beans.TestBean;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 7.
 */
@Configuration
 @ComponentScan(
     basePackages = "org.syaku.spring.beans",
     useDefaultFilters = false,
     includeFilters = @Filter(type = FilterType.ANNOTATION, value = Component.class)
)
public class TestBeanConfiguration {
  @Autowired
  private TestBean testBean;
}
