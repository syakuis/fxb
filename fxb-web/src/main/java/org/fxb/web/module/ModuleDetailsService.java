package org.fxb.web.module;

import org.fxb.web.module.model.Module;

/**
 * DB 에서 모듈 정보를 읽어 온다. 즉 가변 모듈 정보를 관리한다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleDetailsService {
  Module getModule(String moduleId);
}
