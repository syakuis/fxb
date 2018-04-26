package org.fxb.module.data.mybatis.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "MEMBER")
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "userId")
@ToString(exclude = "users")
public class Member implements Serializable {
  @Id
  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "REG_DATE")
  private Date registryDate;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
  private List<User> users;

  public Member() {
  }

  public Member(String userId, String userName, Date registryDate) {
    this.userId = userId;
    this.userName = userName;
    this.registryDate = registryDate;
  }

  public void addUser(User user) {
    if (users == null) {
      users = new ArrayList<>();
    }

    users.add(user);
  }
}