package org.syaku.spring;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.syaku.spring.beans.TestBean;

@ComponentScan(
    basePackages = "org.syaku.spring.beans",
    useDefaultFilters = false,
    includeFilters = @Filter(
        type = FilterType.ANNOTATION,
        classes = Component.class
    )
)
public class TestConfiguation {
  @Autowired
  private TestBean testBean;

  @PostConstruct
  public void setup() {
    System.out.println(testBean);
  }
}
