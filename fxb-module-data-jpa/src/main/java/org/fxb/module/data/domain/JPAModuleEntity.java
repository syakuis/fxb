package org.fxb.module.data.domain;

import data.domain.ModuleEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 6.
 */
@Entity(name = "moduleEntity")
@Table(name = "MODULE")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class JPAModuleEntity implements ModuleEntity {
  private static final long serialVersionUID = -462499339253108744L;

  @Id
  @Column(name = "MODULE_ID")
  private String id;
  @Column(name = "MODULE_NAME")
  private String name;
  @Column(name = "BROWSER_TITLE")
  private String browserTitle;
  @Column(name = "REG_DATE")
  private Date registryDate;

  public JPAModuleEntity() {
  }

  public JPAModuleEntity(String id, String name) {
    this.id = id;
    this.name = name;
    this.registryDate = new Date();
  }
}
