package data.dao;

import data.domain.ModuleEntity;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 6.
 */
public interface ModuleDAO {

  ModuleEntity findOne(String moduleId);

  List<ModuleEntity> findAll();

  void delete(String moduleId);

  void save(ModuleEntity moduleEntity);
}
