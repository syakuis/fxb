package org.fxb.module.data.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 6.
 */
@Entity(name = "moduleEntity")
@Table(name = "MODULE", uniqueConstraints = {
    @UniqueConstraint(columnNames = "MODULE_ID")
})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class JPAModuleEntity implements ModuleEntity {
  private static final long serialVersionUID = -462499339253108744L;

  @Id
  @Column(name = "MODULE_ID", unique = true, nullable = false)
  private String moduleId;

  @Column(name = "MODULE_NAME", nullable = false)
  private String moduleName;

  @Column(name = "BROWSER_TITLE")
  private String browserTitle;

  @Temporal(TemporalType.DATE)
  @Column(name = "REG_DATE")
  private Date registryDate;

  @OneToMany(targetEntity = JPAModuleOptionEntity.class)
  @JoinColumn(name = "MODULE_ID")
  private List<? extends ModuleOptionEntity> moduleOptionEntities;

  public void setModuleOptionEntities(
      List<JPAModuleOptionEntity> moduleOptionEntities) {
    this.moduleOptionEntities = moduleOptionEntities;
  }

  public List<? extends ModuleOptionEntity> getModuleOptionEntities() {
    return moduleOptionEntities;
  }

  public JPAModuleEntity() {
  }

  public JPAModuleEntity(String moduleId, String moduleName) {
    this.moduleId = moduleId;
    this.moduleName = moduleName;
    this.registryDate = new Date();
  }
}
