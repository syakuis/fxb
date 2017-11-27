package org.fxb.app.module.service;

import org.fxb.app.module.dao.MyBatisModuleOptionsDAO;
import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.ModuleOptions;
import org.fxb.app.module.mapper.ModuleOptionsMapper;
import org.fxb.app.module.model.BasicModule;
import org.fxb.app.module.model.BasicOption;
import org.fxb.boot.Bootstrapping;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
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
  @Resource(name = "myBatisModuleService")
  private MyBatisModuleService moduleService;

  @Resource(name = "moduleOptionsMapper")
  private ModuleOptionsMapper moduleOptionsMapper;

  @Resource(name = "myBatisModuleOptionsDAO")
  private MyBatisModuleOptionsDAO moduleOptionsDAO;

  /*
  todo moduleOptionsMapper and DAO TDD 작성 (트랜잭션 포함)
  todo module Mapper and DAO TDD 작성 (트랜잭션 포함)
  todo module service TDD 작성 (트랜잭션 포함)
  todo 다중 dataSource and myBatis 테스트
  todo 소스 정리
  todo module react UI 개발
   */

  @Test
  public void batch() {
    String moduleIdx = "MODUL000000000000031";
    List<ModuleOptions> moduleOptions = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      ModuleOptions options = new ModuleOptions();
      options.setModuleIdx(moduleIdx);
      options.setName("good" + i);
      options.setOrder(i);

      moduleOptions.add(options);
    }

    moduleOptionsDAO.save(moduleIdx, moduleOptions);

    List<ModuleOptions> length = moduleOptionsMapper.selectByModuleId(moduleIdx);

    Assert.assertEquals(10, length.size());

    List<ModuleOptions> moduleOptionsUpdate = new ArrayList<>();

    int stop = 0;
    for (ModuleOptions options : length) {
      if (stop < 4) {
        moduleOptionsUpdate.add(options);
        stop++;
      }
    }

    List<Long> moduleOptionsSrl = moduleOptionsDAO.save(moduleIdx, moduleOptionsUpdate);

    moduleOptionsDAO.deleteByNotModuleOptionsSrl(moduleIdx, moduleOptionsSrl);

    List<ModuleOptions> length2 = moduleOptionsMapper.selectByModuleId(moduleIdx);
    Assert.assertEquals(4, length2.size());

    moduleOptionsMapper.deleteByModuleIdx(moduleIdx);

    List<ModuleOptions> length3 = moduleOptionsMapper.selectByModuleId(moduleIdx);
    Assert.assertEquals(0, length3.size());
  }

  @Test
  @Ignore
  public void good() {
    Assert.assertNotNull(moduleService);

    Module module = moduleService.getModule(null, "test");

    if (module == null) {
      module = new Module();
      module.setModuleName("test");
      module.setModuleId("test");
    } else {
      module.setBrowserTitle("good");
    }

    moduleService.saveModule(module);

    Assert.assertNotNull(module.getModuleIdx());

    List<Module> modules = moduleService.getModules(module.getModuleName());

    Assert.assertEquals(modules.size(), 1);

    Assert.assertNotNull(module);

    moduleService.deleteModule(module.getModuleIdx());

    module = moduleService.getModule(module.getModuleIdx(), null);

    Assert.assertNull(module);
  }

  // todo module service method 들 분리하기

  // todo module options 테스트

  // todo transaction test

}
