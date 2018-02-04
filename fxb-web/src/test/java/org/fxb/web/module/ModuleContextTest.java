package org.fxb.web.module;

import org.fxb.app.module.ModuleTestConfiguration;
import org.fxb.web.module.model.Module;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public class ModuleContextTest extends ModuleTestConfiguration {
  @Autowired
  private ModuleContextManager moduleContextManager;

  @Test
  public void test() {
    System.out.println("=========================== ");

    List<Module> modules = moduleContextManager.getModules();

    for (Module module : modules) {
      System.out.println(module);
    }
  }
}