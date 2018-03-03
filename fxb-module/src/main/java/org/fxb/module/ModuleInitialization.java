package org.fxb.module;

import org.fxb.module.config.bean.factory.ModuleContextFactoryBean;
import org.fxb.module.model.Module;

/**
 * Module Initialization
 *
 * @ModuleInit 주석이 있는 Module 구현체 클래스를 찾아 ModuleContextManager 에 담는 다.
 *
 * @see ModuleContextFactoryBean
 * @see ModuleContextManager
 * @see org.fxb.module.data.ModuleInit
 * @see org.fxb.module.model.ModuleDetails
 * @see org.fxb.module.model.Module
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 19.
 */
public interface ModuleInitialization {
  Module getObject();
}
