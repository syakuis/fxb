package org.fxb.boot;

import org.fxb.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 13.
 */
@Configuration
@ComponentScan(
		basePackages = {"org.fxb.config"},
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
@Import(PropertiesConfiguration.class)
public class Bootstrapping {
	private final Logger logger = LoggerFactory.getLogger(Bootstrapping.class);

	@Autowired
	private Config config;

	@PostConstruct
	public void intro() {
		StringBuilder print = new StringBuilder();
		print.append("\n_________________________________________________________________________________\n");

		try {
			InputStream input = getClass().getResourceAsStream(config.getString("default.intro.path"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			String line;
			while ((line = reader.readLine()) != null) {
				print.append(line).append("\n");
			}
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
		}

		print.append("                                                                  version ")
				.append(config.getVersion())
				.append("  \n")
				.append("                                                                                 \n")
				.append("                                                                                 \n")
				.append("                           Frontend X Backend (Fxb) by 52572 49437 44512         \n")
				.append("                                                                                 \n")
				.append("_________________________________________________________________________________\n\n")
				.append("* Date: ").append(new Date()).append("\n")
				.append("* charset: ").append(config.getCharset()).append("\n")
				.append("* Profile: ").append(config.getProfile()).append("\n")
				.append("* profiles: ").append(config.getString("profiles")).append("\n")
				.append("* rootAbsolutePath: ").append(config.getRootAbsolutePath()).append("\n")
				.append("_________________________________________________________________________________\n\n");

		System.out.println(print.toString());

		Assert.isTrue(
			config.getString("profiles").indexOf("mybatis") > -1 ||
				config.getString("profiles").indexOf("jpa") > -1,
			"profiles requires mybatis or jpa."
		);
	}
}