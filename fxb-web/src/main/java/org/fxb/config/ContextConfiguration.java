package org.fxb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 3.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
		basePackages = "org.fxb",
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				classes = {
						Component.class,
						Service.class,
				}
		)
)
public class ContextConfiguration {

	private Properties properties;

	@Autowired
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Bean
	public Config config() {
		Assert.notNull(properties, "The class must not be null");
		return new Config(this.properties, TimeZone.getDefault(), Locale.getDefault());
	}
}
