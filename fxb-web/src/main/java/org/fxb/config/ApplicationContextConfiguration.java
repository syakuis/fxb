package org.fxb.config;

import org.fxb.config.web.support.FreemarkerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;

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
		includeFilters = @ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				classes = {
						Component.class,
						Service.class,
						Repository.class,
						Controller.class,
						ControllerAdvice.class,
						RestController.class,
						RestControllerAdvice.class
				}
		)
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
		String[] resourceHandlers = config.getResourceHandlers();
		String[] resourceLocations = config.getResourceLocations();
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

		viewResolver.setContentType("text/html; charset=" + config.getCharset() +";");
		viewResolver.setCache(true);
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".ftl");

		registry.viewResolver(viewResolver);
	}
}