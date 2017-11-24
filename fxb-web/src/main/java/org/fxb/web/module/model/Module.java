package org.fxb.web.module.model;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 22.
 */
public interface Module {

  void setSid(String sid);

  void setName(String name);

  void setLayout(String layout);

  void setMenu(String menu);

  void setSkin(String skin);

  void setOptions(Map<String, Option> options);

  String getModuleIdx();

  String getMid();

  String getSid();

  String getName();

  String getLayout();

  String getMenu();

  String getSkin();

  Map<String, Option> getOptions();
}
