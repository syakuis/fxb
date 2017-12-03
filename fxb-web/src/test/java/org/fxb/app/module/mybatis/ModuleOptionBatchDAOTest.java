package org.fxb.app.module.mybatis;

import org.fxb.app.module.domain.ModuleOptionEntity;
import org.fxb.app.module.mybatis.config.DataInitialization;
import org.fxb.app.module.mybatis.config.ModuleOptionConfiguration;
import org.fxb.boot.Bootstrapping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
@Import(ModuleOptionConfiguration.class)
@ActiveProfiles({ "test", "mybatis" })
public class ModuleOptionBatchDAOTest {

  @Autowired
  private ModuleOptionMapper moduleOptionMapper;

  @Autowired
  private ModuleOptionBatchDAO moduleOptionBatchDAO;

  String moduleIdx = "MODUL000000000000031";
  int dataRow = 100;
  int updateDataRow = 4;
  List<ModuleOptionEntity> moduleOptions = new ArrayList<>();

  @Before
  public void init() {
    moduleOptions = DataInitialization.moduleOptions(moduleIdx, dataRow);
  }

  @Test
  @Transactional
  public void test() {
    List<ModuleOptionEntity> list = moduleOptionMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("빈 데이터 검증", 0, list.size());

    // 데이터 추가
    moduleOptionBatchDAO.save(moduleIdx, moduleOptions, false);

    // 추가된 데이터 검증
    List<ModuleOptionEntity> insertList = moduleOptionMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("추가된 데이터 검증", dataRow, insertList.size());

    List<ModuleOptionEntity> result = new ArrayList<>();

    // 일부 데이터만 수정
    int stop = 0;
    for (ModuleOptionEntity option : insertList) {
      if (stop < updateDataRow) {
        result.add(option);
        stop++;
      }
    }

    // 수정될 데이터 검증
    Assert.assertEquals("수정될 데이터 검증", updateDataRow, result.size());

    // 데이터 수정
    moduleOptionBatchDAO.save(moduleIdx, result, false);

    // 데이터 수정 검증
    List<ModuleOptionEntity> moduleOptionsUpdate = moduleOptionMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("데이터 수정 검증", updateDataRow, moduleOptionsUpdate.size());

    // 모든 데이터 삭제
    moduleOptionMapper.deleteByModuleIdx(moduleIdx);

    // 데이터 삭제 검증
    List<ModuleOptionEntity> clean = moduleOptionMapper.selectByModuleIdx(moduleIdx);
    Assert.assertEquals("데이터 삭제 검증", 0, clean.size());
  }
}
