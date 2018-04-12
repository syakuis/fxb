package org.fxb.module.data.domain;

import data.domain.ModuleOptionEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 6.
 */
@Entity(name = "moduleOptionEntity")
@Table(name = "MODULE_OPTION")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class JPAModuleOptionEntity implements ModuleOptionEntity {
  @Id
  @Column(name = "MODULE_ID")
  private String id;
  @Id
  @Column(name = "OPTION_NAME")
  private String name;
  @Column(name = "OPTION_VALUE")
  private String value;
  @Column(name = "OPTION_TITLE")
  private String title;
  @Column(name = "OPTION_ORDER")
  private int order;
}
