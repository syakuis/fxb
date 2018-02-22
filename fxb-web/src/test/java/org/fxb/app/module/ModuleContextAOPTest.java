package org.fxb.app.module;

import java.util.Date;
import org.fxb.app.module.domain.Module;
import org.fxb.app.module.service.ModuleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 23.
 */
@Transactional
public class ModuleContextAOPTest extends ModuleTestConfiguration {
  @Autowired
  private ModuleService moduleService;

  @Test
  public void test() {
    moduleService.saveModule(new Module("moduleid", "moduleid", new Date()));
  }

}
