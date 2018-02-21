package org.fxb.app.module.service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.fxb.app.module.ModuleTestConfiguration;
import org.fxb.app.module.dao.ModuleDAO;
import org.fxb.app.module.dao.ModuleOptionDAO;
import org.fxb.app.module.domain.Module;
import org.fxb.app.module.domain.ModuleOption;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 11.
 */
@Transactional
public class ModuleServiceTest extends ModuleTestConfiguration {
  @Resource(name = "moduleDAO")
  private ModuleDAO moduleDAO;

  @Resource(name = "moduleOptionDAO")
  private ModuleOptionDAO moduleOptionDAO;

  private void insert() {
    moduleDAO.insert(new Module("fxb", "fxb", new Date()));

    List<ModuleOption> moduleOptions = ModuleOption.createList(
        new ModuleOption("fxb", "a", "a", "title", 0),
        new ModuleOption("fxb", "b", "a", "title", 0),
        new ModuleOption("fxb", "c", "a", "title", 0)
    );

    for (ModuleOption moduleOption : moduleOptions) {
      moduleOptionDAO.insert(moduleOption);
    }
  }

  @Test
  public void insertTest() {
    insert();
    Assert.assertEquals(moduleDAO.findAll().size(), 5);
    Module module = moduleDAO.findOneByModuleId("fxb");
    Assert.assertNotNull(module);
    Assert.assertEquals(module.getModuleOptions().size(), 3);
  }

  private void save(Module module, List<ModuleOption> moduleOptions) {
    Module findModule = moduleDAO.findOneByModuleId(module.getModuleId());

    if (findModule == null) {
      moduleDAO.insert(module);
    }

    moduleOptionDAO.deleteByModuleId(module.getModuleId());
    for (ModuleOption moduleOption : moduleOptions) {
      moduleOptionDAO.insert(moduleOption);
    }
  }

  @Test
  public void saveTest() {
    save(
        new Module("fxb1", "fxb", new Date()),
        ModuleOption.createList(
            new ModuleOption("fxb1", "a", "a", "title", 0),
            new ModuleOption("fxb1", "b", "a", "title", 0),
            new ModuleOption("fxb1", "c", "a", "title", 0)
        )
    );

    Assert.assertEquals(moduleDAO.findAll().size(), 5);

    Assert.assertEquals(moduleDAO.findOneByModuleId("fxb1").getModuleOptions().size(), 3);

    insert();

    Assert.assertEquals(moduleDAO.findAll().size(), 6);

    save(
        new Module("fxb", "fxb", new Date()),
        ModuleOption.createList(
            new ModuleOption("fxb", "a", "a", "title", 0)
        )
    );

    Assert.assertEquals(moduleDAO.findAll().size(), 6);

    Assert.assertEquals(moduleDAO.findOneByModuleId("fxb").getModuleOptions().size(), 1);
  }
}
