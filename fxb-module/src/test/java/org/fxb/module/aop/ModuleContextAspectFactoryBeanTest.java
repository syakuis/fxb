package org.fxb.module.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;
import org.fxb.module.Module;
import org.fxb.module.ModuleContextManager;
import org.fxb.module.ModuleContextService;
import org.fxb.module.ModuleDetails;
import org.fxb.module.ModuleRedefinition;
import org.fxb.module.ModuleRedefinition.Mode;
import org.fxb.module.ModuleRedefinition.Scope;
import org.fxb.module.TestConfiguration;
import org.fxb.module.bean.factory.ModuleRedefinitionAspectFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AspectConfiguration.class})
public class ModuleContextAspectFactoryBeanTest {

  @Autowired
  private BoardService boardService;

  @Autowired
  private ModuleContextManager moduleContextManager;

  @Test
  public void test() {
    Board board = new Board();

    boardService.insert(board);

    Assert.assertEquals(moduleContextManager.get(board.getModuleId()).getModuleId(),
        board.getModuleId());

    boardService.update(board);

    Assert.assertEquals(moduleContextManager.get(board.getModuleId()).getModuleId(),
        board.getModuleId());

    boardService.del(board.getModuleId());

    Assert.assertEquals(moduleContextManager.get(board.getModuleId()),
        null);
  }
}

class BoardService {

  private Database database;

  public BoardService(Database database) {
    this.database = database;
  }

  @ModuleRedefinition(expression = "args[0].moduleId", scope = Scope.THIS)
  public void insert(Board board) {
    String moduleId = "board_" + new Random().nextInt(100);

    board.setModuleId(moduleId);
    board.setModuleName(moduleId);

    database.add(board);
  }

  @ModuleRedefinition(expression = "result.moduleId", scope = Scope.THIS)
  public Board update(Board board) {
    return board;
  }

  @ModuleRedefinition(expression = "args[0]", scope = Scope.ALL, mode = Mode.REMOVE)
  public void del(String moduleId) {
    // del code
  }
}

@Getter @Setter
class Board {
  private String moduleId;
  private String moduleName;

  public Board() {
  }

  public Board(String moduleId, String moduleName) {
    this.moduleId = moduleId;
    this.moduleName = moduleName;
  }
}

class BasicModuleContextService implements ModuleContextService {
  private Database database;

  public BasicModuleContextService(Database database) {
    this.database = database;
  }

  @Override
  public List<Module> getModules() {
    return null;
  }

  @Override
  public Module getModule(String moduleId) {
    Board board = database.getBoard(moduleId);

    return new ModuleDetails(board.getModuleName(), board.getModuleId());
  }
}

class Database {
  private final static Map<String, Board> store = new HashMap<>();

  public void add(Board board) {
    store.put(board.getModuleId(), board);
  }

  public Board getBoard(String moduleId) {
    return store.get(moduleId);
  }
}


@Configuration
@EnableAspectJAutoProxy
@Import(TestConfiguration.class)
class AspectConfiguration {
  @Autowired
  private ModuleContextManager moduleContextManager;

  @Bean
  public Database database() {
    return new Database();
  }

  @Bean
  public BoardService boardService() {
    return new BoardService(database());
  }

  @Bean
  public ModuleContextService moduleContextService() {
    return new BasicModuleContextService(database());
  }

  @Bean
  public Advisor moduleContextAspect() {
    ModuleRedefinitionAspectFactoryBean bean = new ModuleRedefinitionAspectFactoryBean(
        moduleContextManager, moduleContextService()
    );

    return bean.getObject();
  }
}