package org.fxb.web.module;

import org.fxb.web.module.model.Module;

/**
 * 정적인 모듈 정보를 최초 생성한다. Java Class 의해 만들어진다.
 *
 * @see org.fxb.web.module.annotation.ModuleInit
 * @see org.fxb.web.module.beans.factory.ModuleContextFactoryBean
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 19.
 */
public interface ModuleInitialization {
  Module getObject();
}
