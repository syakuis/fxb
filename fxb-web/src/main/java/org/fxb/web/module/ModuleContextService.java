package org.fxb.web.module;

import javax.annotation.PostConstruct;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleContextService {
  @PostConstruct
  void init();
  void setModule(String moduleName);
}
