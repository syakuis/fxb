package org.fxb.module;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.fxb.context.config.ConfigConfiguration;
import org.fxb.context.config.DataSourceConfiguration;
import org.fxb.context.config.MyBatisConfiguration;
import org.fxb.context.db.bean.factory.PopulatorDataSourceInitializer;
import org.fxb.module.config.ModuleConfiguration;
import org.fxb.module.dao.ModuleDAO;
import org.fxb.module.dao.ModuleOptionDAO;
import org.fxb.module.domain.ModuleEntity;
import org.fxb.module.domain.ModuleOptionEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 2. 11.
 */

@Transactional
public class ModuleDAOTest extends TestConfiguration {
  @Resource(name = "moduleDAO")
  private ModuleDAO moduleDAO;

  @Resource(name = "moduleOptionDAO")
  private ModuleOptionDAO moduleOptionDAO;

  private void insert() {
    moduleDAO.insert(new ModuleEntity("fxb", "fxb", new Date()));

    List<ModuleOptionEntity> moduleOptions = ModuleOptionEntity.createList(
        new ModuleOptionEntity("fxb", "a", "a", "title", 0),
        new ModuleOptionEntity("fxb", "b", "a", "title", 0),
        new ModuleOptionEntity("fxb", "c", "a", "title", 0)
    );

    for (ModuleOptionEntity moduleOption : moduleOptions) {
      moduleOptionDAO.insert(moduleOption);
    }
  }

  @Test
  public void insertTest() {
    insert();
    Assert.assertEquals(moduleDAO.findAll().size(), 5);
    ModuleEntity module = moduleDAO.findOneByModuleId("fxb");
    Assert.assertNotNull(module);
    Assert.assertEquals(module.getModuleOptions().size(), 3);
  }

  private void save(ModuleEntity module, List<ModuleOptionEntity> moduleOptions) {
    ModuleEntity findModule = moduleDAO.findOneByModuleId(module.getModuleId());

    if (findModule == null) {
      moduleDAO.insert(module);
    }

    moduleOptionDAO.deleteByModuleId(module.getModuleId());
    for (ModuleOptionEntity moduleOption : moduleOptions) {
      moduleOptionDAO.insert(moduleOption);
    }
  }

  @Test
  public void saveTest() {
    save(
        new ModuleEntity("fxb1", "fxb", new Date()),
        ModuleOptionEntity.createList(
            new ModuleOptionEntity("fxb1", "a", "a", "title", 0),
            new ModuleOptionEntity("fxb1", "b", "a", "title", 0),
            new ModuleOptionEntity("fxb1", "c", "a", "title", 0)
        )
    );

    Assert.assertEquals(moduleDAO.findAll().size(), 5);

    Assert.assertEquals(moduleDAO.findOneByModuleId("fxb1").getModuleOptions().size(), 3);

    insert();

    Assert.assertEquals(moduleDAO.findAll().size(), 6);

    save(
        new ModuleEntity("fxb", "fxb", new Date()),
        ModuleOptionEntity.createList(
            new ModuleOptionEntity("fxb", "a", "a", "title", 0)
        )
    );

    Assert.assertEquals(moduleDAO.findAll().size(), 6);

    Assert.assertEquals(moduleDAO.findOneByModuleId("fxb").getModuleOptions().size(), 1);
  }
}
