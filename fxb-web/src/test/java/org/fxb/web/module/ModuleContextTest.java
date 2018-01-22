package org.fxb.web.module;

import org.fxb.app.module.init.ModuleTestConfiguration;
import org.fxb.app.module.service.ModuleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
public class ModuleContextTest extends ModuleTestConfiguration {
  @Autowired
  @Qualifier("fxbCacheManager")
  CacheManager cacheManager;

  @Resource(name = "moduleService")
  private ModuleService moduleService;

  @Autowired
  private ModuleContext moduleContext;

  String moduleId = "test";
  String moduleName = "test";
  String moduleIdx = null;
  int dataRow = 10;

  @Before
  public void before() {
//    this.init();
//    Assert.assertEquals(cacheManager.getCache("fxb.module").getName(), "fxb.module");
//
//    Assert.assertNotNull(moduleService);
//    Assert.assertThat(moduleService.getModules(moduleName), IsEmptyCollection.empty());
//
//    for (int i = 0; i < dataRow; i++) {
//      Module moduleEntity = DataInitialization.module(moduleName, i == 0 ? moduleId : moduleId + i, false);
//      moduleEntity.setModuleOptions(DataInitialization.moduleOptions(null, dataRow));
//      moduleService.saveModule(moduleEntity);
//      if (i == 0) moduleIdx = moduleEntity.getModuleIdx();
//    }
//
//    Assert.assertThat("초기화 데이터 검증", moduleService.getModules().size(), is(dataRow));
  }

  @Test
  public void test() {
    System.out.println("=========================== ");
//    Map<String, org.fxb.web.module.model.Module> module = moduleContext.get();
//    Cache cache = cacheManager.getCache("fxb.module");
//    System.out.println(module);
//    System.out.println(cache.get("moduleContext"));
//    Cache.ValueWrapper valueWrapper = cacheManager.getCache("fxb.module").get("moduleContext");
//    System.out.println("--------------->" + valueWrapper);
//    Assert.assertThat(
//      module,
//      is((Map<String, org.fxb.web.module.model.Module>) valueWrapper.get())
//    );
//
//    for(String moduleIdx : module.keySet()) {
//      org.fxb.web.module.model.Module moduleData = module.get(moduleIdx);
//
//      Assert.assertEquals(moduleData.getMid(), moduleContext.getMid(moduleIdx));
//      Assert.assertEquals(moduleData.getSid(), moduleContext.getSid(moduleIdx));
//    }
  }
}