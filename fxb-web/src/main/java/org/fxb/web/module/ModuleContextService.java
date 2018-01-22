package org.fxb.web.module;

import org.fxb.web.module.model.Module;

import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public interface ModuleContextService {
  List<String> getModuleIdx();
  List<String> getId();
  String createId(String mid, String sid);

  /**
   * module 정보 전체를 읽는 다.
   * @return
   */
  Map<String, Module> getModuleContext();
}
