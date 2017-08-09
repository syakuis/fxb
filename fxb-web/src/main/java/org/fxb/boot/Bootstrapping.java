package org.fxb.boot;

import org.fxb.boot.beans.factory.config.ConfigurePropertiesConfigurer;
import org.springframework.context.annotation.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 13.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
		basePackages = "org.fxb.config",
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class Bootstrapping {
	@Bean
	public static ConfigurePropertiesConfigurer configurePropertiesConfigurer() {
		return new ConfigurePropertiesConfigurer();
	}
}
