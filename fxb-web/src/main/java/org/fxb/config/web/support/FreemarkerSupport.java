package org.fxb.config.web.support;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class FreemarkerSupport {
	private static final Logger logger = LoggerFactory.getLogger(FreemarkerSupport.class);
	private final Configuration cfg;

	public FreemarkerSupport(FreeMarkerConfigurer freemarkerConfig) {
		cfg = freemarkerConfig.getConfiguration();
	}

	public void variable(String name, Object object) {
		try {
			cfg.setSharedVariable(name, object);
		} catch (TemplateModelException e) {
			logger.error(e.getMessage(), e);

		}
	}

}
