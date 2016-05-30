package jgb.springboot_poc.services;

import java.util.List;

import jgb.springboot_poc.model.User;

public interface UserService {

  User findById(Long id);

  List<User> findAll();

  User save(User user);

  User update(User user);

  void delete(Long id);
}
