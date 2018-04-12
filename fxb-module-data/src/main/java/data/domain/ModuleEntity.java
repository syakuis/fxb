package data.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 6.
 */
public interface ModuleEntity extends Serializable {
  String getId();
  String getName();
  String getBrowserTitle();
  Date getRegistryDate();
}
