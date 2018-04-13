package org.fxb.module.data.mybatis.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 13.
 */
@Entity
@Table(name = "USER")
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

//  @Column(name = "USER_ID")
//  private String userId;

  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;

  @Column(name = "ADDRESS")
  private String address;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  private Member member;

  public User() {
  }

  public User(String phoneNumber, String address, Member member) {
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.member = member;
  }
}
