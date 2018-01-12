package org.fxb.web.module;

import org.fxb.app.module.config.ModuleConfiguration;
import org.fxb.app.module.domain.ModuleEntity;
import org.fxb.app.module.mapper.config.DataInitialization;
import org.fxb.app.module.mapper.config.ModuleInitializationConfiguration;
import org.fxb.app.module.service.ModuleService;
import org.fxb.boot.Bootstrapping;
import org.fxb.web.module.model.Module;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Map;

import static org.hamcrest.core.Is.is;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Bootstrapping.class)
@Import(value = {ModuleConfiguration.class, ModuleInitializationConfiguration.class})
@ActiveProfiles({"test", "mybatis"})
public class ModuleContextTest {
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
  public void init() {
    Assert.assertEquals(cacheManager.getCache("fxb.module").getName(), "fxb.module");

    Assert.assertNotNull(moduleService);
    Assert.assertThat(moduleService.getModules(moduleName), IsEmptyCollection.empty());

    for (int i = 0; i < dataRow; i++) {
      ModuleEntity moduleEntity = DataInitialization.module(moduleName, i == 0 ? moduleId : moduleId + i, false);
      moduleEntity.setModuleOptions(DataInitialization.moduleOptions(null, dataRow));
      moduleService.saveModule(moduleEntity);
      if (i == 0) moduleIdx = moduleEntity.getModuleIdx();
    }

    Assert.assertThat("초기화 데이터 검증", moduleService.getModules().size(), is(dataRow));
  }

  @Test
  public void test() {
    Map<String, Module> module = moduleContext.get();
    Cache.ValueWrapper valueWrapper = cacheManager.getCache("fxb.module").get("moduleContext");
    Assert.assertThat(
      module,
      is((Map<String, Module>) valueWrapper.get())
    );

    for(String moduleIdx : module.keySet()) {
      Module moduleData = module.get(moduleIdx);

      Assert.assertEquals(moduleData.getMid(), moduleContext.getMid(moduleIdx));
      Assert.assertEquals(moduleData.getSid(), moduleContext.getSid(moduleIdx));
    }
  }
}