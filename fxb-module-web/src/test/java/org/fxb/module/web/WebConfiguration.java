package org.fxb.module.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 6.
 */
@Configuration
@ComponentScan(
    basePackages = "org.fxb.module.web",
    useDefaultFilters = false,
    includeFilters = @Filter(type = FilterType.ANNOTATION, classes = {
        RestController.class,
        Controller.class
    })
)
public class WebConfiguration extends WebMvcConfigurerAdapter {
}
