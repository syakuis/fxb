package org.fxb.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 28.
 */
public interface Module {
  String getModuleName();
  String getModuleId();
  <T extends Module> T getObject(Class<T> type);
}
