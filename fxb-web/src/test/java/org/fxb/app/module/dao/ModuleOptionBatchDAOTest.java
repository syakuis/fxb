package org.fxb.app.module.dao;

import org.fxb.app.module.ModuleTestConfiguration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
public class ModuleOptionBatchDAOTest extends ModuleTestConfiguration {
//
//  @Autowired
//  private ModuleOptionDAO moduleOptionDAO;
//
//  @Autowired
//  private ModuleOptionBatchDAO moduleOptionBatchDAO;
//
//  String moduleIdx = "MODUL000000000000031";
//  int dataRow = 100;
//  int updateDataRow = 4;
//  List<ModuleOption> moduleOptions = new ArrayList<>();
//
//  @Before
//  public void before() {
//    moduleOptions = DataInitialization.moduleOptions(moduleIdx, dataRow);
//  }
//
//  @Test
//  @Transactional
//  public void test() {
//    List<ModuleOption> list = moduleOptionDAO.findByModuleIdx(moduleIdx);
//    Assert.assertEquals("빈 데이터 검증", 0, list.size());
//
//    // 데이터 추가
//    moduleOptionBatchDAO.save(moduleIdx, moduleOptions, false);
//
//    // 추가된 데이터 검증
//    List<ModuleOption> insertList = moduleOptionDAO.findByModuleIdx(moduleIdx);
//    Assert.assertEquals("추가된 데이터 검증", dataRow, insertList.size());
//
//    List<ModuleOption> result = new ArrayList<>();
//
//    // 일부 데이터만 수정
//    int stop = 0;
//    for (ModuleOption option : insertList) {
//      if (stop < updateDataRow) {
//        result.add(option);
//        stop++;
//      }
//    }
//
//    // 수정될 데이터 검증
//    Assert.assertEquals("수정될 데이터 검증", updateDataRow, result.size());
//
//    // 데이터 수정
//    moduleOptionBatchDAO.save(moduleIdx, result, false);
//
//    // 데이터 수정 검증
//    List<ModuleOption> moduleOptionsUpdate = moduleOptionDAO.findByModuleIdx(moduleIdx);
//    Assert.assertEquals("데이터 수정 검증", updateDataRow, moduleOptionsUpdate.size());
//
//    // 모든 데이터 삭제
//    moduleOptionDAO.deleteByModuleIdx(moduleIdx);
//
//    // 데이터 삭제 검증
//    List<ModuleOption> clean = moduleOptionDAO.findByModuleIdx(moduleIdx);
//    Assert.assertEquals("데이터 삭제 검증", 0, clean.size());
//  }
}
