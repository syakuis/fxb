package org.fxb.config.context;

import javax.annotation.PostConstruct;
import org.fxb.config.support.FreemarkerSupport;
import org.fxb.context.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 13.
 */
@Configuration
@EnableWebMvc
@ComponentScan(
  basePackages = "org.fxb.app",
  useDefaultFilters = false,
  includeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
      Controller.class,
      ControllerAdvice.class,
      RestController.class,
      RestControllerAdvice.class,
    })
  }
)
public class ApplicationContextConfiguration extends WebMvcConfigurerAdapter implements WebMvcConfigurer {
  private Validator validator;

  @Autowired
  private Config config;

  @Autowired
  @Qualifier(value = "freemarkerConfig")
  private FreeMarkerConfigurer freeMarkerConfigurer;

  @PostConstruct
  public void freeMarkerGlobalVariable() {
    FreemarkerSupport freemarkerSupport = new FreemarkerSupport(freeMarkerConfigurer);
    freemarkerSupport.variable("config", config);
  }

  @Autowired
  public void setValidator(Validator validator) {
    this.validator = validator;
  }

  /**
   <mvc:annotation-driven validator="validator"/>
   * @return
   */
  @Override
  public Validator getValidator() {
    return validator;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String[] resourceHandlers = config.getStringArray("resourceHandlers");
    String[] resourceLocations = config.getStringArray("resourceLocations");
    int size = resourceHandlers.length;

    for (int i = 0; i < size; i++) {
      registry.addResourceHandler(resourceHandlers[i]).addResourceLocations(resourceLocations[i]).setCachePeriod(0);
    }
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    WebContentInterceptor wci = new WebContentInterceptor();
    wci.setCacheSeconds(0);
    registry.addInterceptor(wci);
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
    viewResolver.setExposeSpringMacroHelpers(true);

    viewResolver.setContentType("text/html; charset=" + config.getString("charset") +";");
    viewResolver.setCache(true);
    viewResolver.setPrefix("");
    viewResolver.setSuffix(".ftl");

    registry.viewResolver(viewResolver);
  }
}
