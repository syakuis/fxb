package org.fxb.app.module;

import org.fxb.app.module.model.BasicModule;
import org.fxb.app.module.model.BasicModuleToModule;
import org.fxb.app.module.model.BasicOption;
import org.fxb.web.module.model.Module;
import org.fxb.web.module.model.ModuleBinding;
import org.fxb.web.module.model.ModuleDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http ://syaku.tistory.com
 * @since 2017. 11. 22.
 */
public class ModuleContextTest {
  private static final Logger logger = LoggerFactory.getLogger(ModuleContextTest.class);

  private ModuleContext moduleContext = new ModuleContext();
  private List<BasicModule> basicModules;

  @Before
  public void init() {
    this.basicModules = new ArrayList<>();

    for(int i = 0; i < 1000; i++) {
      this.basicModules.add(
        new BasicModule(String.valueOf(i), "test" + String.valueOf(i), "test" + String.valueOf(i),
          BasicModule.setOptions(
            new BasicOption(String.valueOf(i), 1, "name", "value", null),
            new BasicOption(String.valueOf(i), 2, "name1", "value1", null),
            new BasicOption(String.valueOf(i), 3, "name2", "value2", null),
            new BasicOption(String.valueOf(i), 4, "name3", "value3", null),
            new BasicOption(String.valueOf(i), 5, "name4", "value4", null)
          )
        )
      );
    }
  }

  private BasicModule getModuleObject(int i) {
    return new BasicModule(String.valueOf(i), "test" + String.valueOf(i), "test" + String.valueOf(i),
      BasicModule.setOptions(
        new BasicOption(String.valueOf(i), 1, "--name", "--value", null),
        new BasicOption(String.valueOf(i), 2, "--name1", "--value1", null),
        new BasicOption(String.valueOf(i), 3, "--name2", "--value2", null),
        new BasicOption(String.valueOf(i), 4, "--name3", "--value3", null),
        new BasicOption(String.valueOf(i), 5, "--name4", "--value4", null)
      )
    );
  }

  /**
   * 디비에 저장된 모듈 데이터를 moduleContext 저장한다.
   */
  @Test
  public void createModuleContext() {
    // client
    List<Module> modules = new ArrayList<>();
    for(BasicModule basicModule : basicModules) {
      Module module = new ModuleDetails(
        basicModule.getModuleIdx(), basicModule.getModuleId(), basicModule.getModuleName(),
        ModuleDetails.setOptions(basicModule.getOptions())
      );

      modules.add(module);
    }

    // factory
    moduleContext.set(modules);

    // check
    for (Module module : modules) {
      Assert.assertEquals(module, moduleContext.get(module.getModuleIdx()));
      Assert.assertTrue(module.getOptions().equals(moduleContext.get(module.getModuleIdx()).getOptions()));
    }
  }

  // todo 모듈 하나를 추가한다. (옵션 포함)
  @Test
  public void testModuleContextAdd() {
    // client
    this.createModuleContext();
    BasicModule basicModule = this.getModuleObject(100000);
    basicModule.setBrowserTitle("good");
    ModuleBinding binding = new BasicModuleToModule(basicModule);


    // factory
    Module module = binding.getModule();
    this.moduleContext.put(module);
    Assert.assertTrue(this.moduleContext.get(module.getModuleIdx()).equals(module));
  }

  // todo 모듈 하나를 삭제한다. (옵션 포함)
  @Test
  public void testModuleContextDel() {
    this.createModuleContext();
    logger.debug("{}", this.moduleContext.get("1"));
    this.moduleContext.del("1");
    logger.debug("{}", this.moduleContext.get("1"));
  }

  // 모듈 하나를 병합한다. 기존 값을 호출하여 병합한다. (frontend 에서 처리한다)

  // todo mid 로 모듈을 검색한다.
  @Test
  public void findMid() {
    createModuleContext();
    logger.debug("{} - {}", this.moduleContext.getMid("1"), this.moduleContext.get("1").getMid());
    Assert.assertEquals(this.moduleContext.getMid("1"), this.moduleContext.get("1").getMid());
  }


  // todo sid 로 모듈을 검색한다.
  @Test
  public void findSid() {
    createModuleContext();
    logger.debug("{} - {}", this.moduleContext.getSid("1"), this.moduleContext.get("1").getSid());
    Assert.assertEquals(this.moduleContext.getSid("1"), this.moduleContext.get("1").getSid());
  }

  // todo moduleIdx 로 모듈을 검색한다.
  @Test
  public void findModuleIdx() {
    createModuleContext();
    Assert.assertEquals(
      this.moduleContext.getModuleIdx(
        this.moduleContext.getMid("1"), this.moduleContext.getSid("1")),
        this.moduleContext.get("1").getModuleIdx());
  }
}
