package org.fxb.boot;

import org.fxb.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 13.
 */
@Configuration
@ComponentScan(
		basePackages = "org.fxb.config",
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
@Import(PropertiesConfiguration.class)
public class Bootstrapping {
	private final Logger logger = LoggerFactory.getLogger(Bootstrapping.class);

	@Autowired
	private Config config;

	@PostConstruct
	public void intro() throws IOException {
		StringBuilder print = new StringBuilder();
		print.append("\n_________________________________________________________________________________\n");

		try {
			InputStream input = getClass().getResourceAsStream("/org/fxb/config/intro.txt");
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
				.append("* Profile: ").append(config.getProfile()).append("\n")
				.append("* profiles: ").append(config.getString("profiles")).append("\n")
				.append("* rootAbsolutePath: ").append(config.getRootAbsolutePath()).append("\n")
				.append("_________________________________________________________________________________\n\n");

		System.out.println(print.toString());
	}
}