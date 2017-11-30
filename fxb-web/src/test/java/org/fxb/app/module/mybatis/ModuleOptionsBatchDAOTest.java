package org.fxb.app.module.mybatis;

import org.fxb.app.module.domain.ModuleOptions;
import org.fxb.app.module.mybatis.config.DataInitialization;
import org.fxb.app.module.mybatis.config.ModuleOptionsConfiguration;
import org.fxb.boot.Bootstrapping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
@Import(ModuleOptionsConfiguration.class)
@ActiveProfiles({ "test", "mybatis" })
public class ModuleOptionsBatchDAOTest {

  @Resource(name = "moduleOptionsMapper")
  private ModuleOptionsMapper moduleOptionsMapper;

  @Resource(name = "moduleOptionsBatchDAO")
  private ModuleOptionsBatchDAO moduleOptionsBatchDAO;

  String moduleIdx = "MODUL000000000000031";
  int dataRow = 100;
  int updateDataRow = 4;
  List<ModuleOptions> moduleOptions = new ArrayList<>();

  @Before
  public void init() {
    moduleOptions = DataInitialization.moduleOptions(moduleIdx, dataRow);
  }

  @Test
  @Transactional
  public void test() {
    List<ModuleOptions> list = moduleOptionsMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("빈 데이터 검증", 0, list.size());

    // 데이터 추가
    moduleOptionsBatchDAO.save(moduleIdx, moduleOptions, false);

    // 추가된 데이터 검증
    List<ModuleOptions> insertList = moduleOptionsMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("추가된 데이터 검증", dataRow, insertList.size());

    List<ModuleOptions> result = new ArrayList<>();

    // 일부 데이터만 수정
    int stop = 0;
    for (ModuleOptions options : insertList) {
      if (stop < updateDataRow) {
        result.add(options);
        stop++;
      }
    }

    // 수정될 데이터 검증
    Assert.assertEquals("수정될 데이터 검증", updateDataRow, result.size());

    // 데이터 수정
    moduleOptionsBatchDAO.save(moduleIdx, result, false);

    // 데이터 수정 검증
    List<ModuleOptions> moduleOptionsUpdate = moduleOptionsMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("데이터 수정 검증", updateDataRow, moduleOptionsUpdate.size());

    // 모든 데이터 삭제
    moduleOptionsMapper.deleteByModuleIdx(moduleIdx);

    // 데이터 삭제 검증
    List<ModuleOptions> clean = moduleOptionsMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("데이터 삭제 검증", 0, clean.size());
  }
}
