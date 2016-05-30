package jgb.springboot_poc.model;

import org.joda.time.LocalDate;

public class User {

  private Long id;
  private String name;
  private Integer type;
  private String phone;

  private LocalDate birthDate;

  public Long getId() {
    return id;
  }

  public User setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public User setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getType() {
    return type;
  }

  public User setType(Integer type) {
    this.type = type;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public User setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public User setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public User copyFrom(User user) {
    if (user.name != null) {
      this.name = user.name;
    }
    if (user.type != null) {
      this.type = user.type;
    }
    if (user.phone != null) {
      this.phone = user.phone;
    }
    if (user.birthDate != null) {
      this.birthDate = user.birthDate;
    }
    return this;
  }

}
