package org.fxb.app.module.service;

import org.fxb.app.module.model.BasicModule;
import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.condition.ModuleSearch;
import org.fxb.app.module.mapper.ModuleMapper;
import org.fxb.boot.Bootstrapping;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Bootstrapping.class)
@ActiveProfiles({ "dev", "mybatis" })
public class MyBatisModuleServiceTest {
  @Resource(name = "moduleMapper")
  private ModuleMapper moduleMapper;

  @Test
  public void good() {
    Assert.assertNotNull(moduleMapper);

    Module basicModule = moduleMapper.selectOne(null, "test");

    if (basicModule == null) {
      basicModule = new Module();
      basicModule.setModuleName("test");
      basicModule.setModuleId("test");
      moduleMapper.insert(basicModule);
    } else {
      basicModule.setBrowserTitle("good");
      moduleMapper.update(basicModule);
    }

    Assert.assertNotNull(basicModule.getModuleIdx());

    List<Module> modules = moduleMapper.select(basicModule.getModuleName(), new ModuleSearch());

    Assert.assertEquals(modules.size(), 1);

    Assert.assertNotNull(basicModule);

    moduleMapper.delete(basicModule.getModuleIdx());

    long count = moduleMapper.selectCount(basicModule.getModuleName(), new ModuleSearch());

    Assert.assertEquals(count, 0);
  }
}
