package org.fxb.boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.fxb.commons.environment.Env;
import org.fxb.context.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 13.
 */
@Configuration
@ComponentScan(
    basePackages = {"org.fxb.config.prepared", "org.fxb.config.context"},
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class Bootstrapping {
  private final Logger logger = LoggerFactory.getLogger(Bootstrapping.class);

  @Autowired
  private Environment environment;

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

    String version = "1.0.0";

    print.append("                                                                  version ")
        .append(version)
        .append("  \n")
        .append("                                                                                 \n")
        .append("                                                                                 \n")
        .append("                           Frontend X Backend (Fxb) by 52572 49437 44512         \n")
        .append("                                                                                 \n")
        .append("_________________________________________________________________________________\n\n")
        .append("* Date: ").append(new Date()).append("\n")
        .append("* charset: ").append(config.getString("charset")).append("\n")
        .append("* profiles: ").append(Env.getProfiles(environment)).append("\n")
        .append("_________________________________________________________________________________\n\n");

    System.out.println(print.toString());

    Assert.isTrue(
      config.getString("profiles").indexOf("mybatis") > -1 ||
        config.getString("profiles").indexOf("jpa") > -1,
      "profiles requires mybatis or jpa."
    );
  }
}