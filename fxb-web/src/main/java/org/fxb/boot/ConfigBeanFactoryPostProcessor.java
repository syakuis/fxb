package org.fxb.boot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * spring 빈을 가장 먼저 생성하기 위해 BeanDefinition 을 사용하였다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 24.
 */
public class ConfigBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	private final String beanName = "config";

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
				.genericBeanDefinition(ConfigFactoryBean.class)
				.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR);
		registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}
}
