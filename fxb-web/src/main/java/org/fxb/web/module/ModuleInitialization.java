package org.fxb.web.module;

import org.fxb.web.module.model.Module;

/**
 * 직접 모듈 정보를 생성할때 사용한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 19.
 */
public interface ModuleInitialization {
  Module getObject();
}
