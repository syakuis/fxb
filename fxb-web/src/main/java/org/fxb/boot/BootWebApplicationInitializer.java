package org.fxb.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 10.
 */

//public class BootWebApplicationInitializer implements WebApplicationInitializer {
public class BootWebApplicationInitializer {
	private static final Logger logger = LoggerFactory.getLogger(BootWebApplicationInitializer.class);
//
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		logger.debug("><>< Web Application Initializer ...");
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//
//		// characterEncoding fixed
//		FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
//		characterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
//
//		servletContext.setInitParameter("spring.profiles.default", "default");
//
//		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//		context.register(Bootstrapping.class);
//
//		servletContext.addListener(new ContextLoaderListener(context));
//
//		AnnotationConfigWebApplicationContext dispatcherServletContext = new AnnotationConfigWebApplicationContext();
//		dispatcherServletContext.register(ApplicationContextConfiguration.class);
//
//		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("fxb-web", new DispatcherServlet(dispatcherServletContext));
//		dispatcher.setLoadOnStartup(1);
//		dispatcher.addMapping("/");
//	}
}
