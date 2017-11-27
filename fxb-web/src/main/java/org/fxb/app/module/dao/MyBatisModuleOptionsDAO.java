package org.fxb.app.module.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fxb.app.module.domain.ModuleOptions;
import org.fxb.app.module.mapper.ModuleOptionsMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 11. 27.
 */
@Repository
public class MyBatisModuleOptionsDAO {
  @Resource(name = "fxbSqlSessionFactory")
  SqlSessionFactory sqlSessionFactory;

  /**
   *
   * @param moduleIdx
   * @param moduleOptions
   * @return
   */
  public List<Long> save(String moduleIdx, List<ModuleOptions> moduleOptions) {
    SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);

    List<Long> success = new ArrayList<>();

    try {
      ModuleOptionsMapper mapper = session.getMapper(ModuleOptionsMapper.class);

      for (ModuleOptions options : moduleOptions) {
        if (options.getModuleOptionsSrl() == null) {
          mapper.insert(options);
        } else {
          mapper.update(options);
        }

        success.add(options.getModuleOptionsSrl());
      }

      session.flushStatements();
      session.commit();
    } finally {
      session.close();
    }

    return success;
  }

  public void deleteByNotModuleOptionsSrl(String moduleIdx, List<Long> moduleOptionsSrl) {
    SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);

    try {
        ModuleOptionsMapper mapper = session.getMapper(ModuleOptionsMapper.class);

        List<ModuleOptions> moduleOptions = mapper.selectByModuleId(moduleIdx);

        List<Long> moduleOptionsSrlOriginal = new ArrayList<>();
        for (ModuleOptions options : moduleOptions) {
          moduleOptionsSrlOriginal.add(options.getModuleOptionsSrl());
        }

        moduleOptionsSrlOriginal.removeAll(moduleOptionsSrl);

        for (Long value : moduleOptionsSrlOriginal) {
          mapper.deleteByModuleOptionsSrl(moduleIdx, value);
        }

        session.flushStatements();
        session.commit();
    } finally {
      session.close();
    }
  }
}
