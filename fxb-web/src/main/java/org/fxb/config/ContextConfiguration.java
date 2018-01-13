package org.fxb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
@Configuration
@ComponentScan(
  basePackages = "org.fxb.app",
  useDefaultFilters = false,
  includeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
      Configuration.class,
      Service.class,
      Repository.class
    })
  }
)
public class ContextConfiguration {

}
