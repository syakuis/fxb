package org.fxb.resource.bean.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * spring 빈을 가장 먼저 생성하기 위해 BeanDefinition 을 사용하였다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 24.
 */
@Deprecated
public class PropertiesBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	private final String beanName;
	private final String[] locations;
	private final Class<?> clazz;
	private String fileEncoding;

	public PropertiesBeanFactoryPostProcessor(String beanName, Class<?> clazz, String[] locations) {
		this.beanName = beanName;
		this.clazz = clazz;
		this.locations = locations;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		MutablePropertyValues property = new MutablePropertyValues();
		property.add("locations", locations);
		if (fileEncoding != null) {
			property.add("fileEncoding", fileEncoding);
		}

		RootBeanDefinition rootBean = new RootBeanDefinition(clazz, null, property);
		registry.registerBeanDefinition(this.beanName, rootBean);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}
}
