package jgb.springboot_poc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jgb.springboot_poc.model.User;
import jgb.springboot_poc.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<User> getAllUsers() {
    return userService.findAll();
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUser(@PathVariable long userId) {
    User user = userService.findById(userId);
    ResponseEntity<User> response;
    if (user == null) {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      response = new ResponseEntity<>(user, HttpStatus.OK);
    }
    return response;
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<User> insertUser(@RequestBody User user) {
    User userSaved = userService.save(user);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(userSaved.getId()).toUri());

    return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateUser(@PathVariable long userId, @RequestBody User user) {
    User userOld = userService.findById(userId);
    if (userOld != null) {
      userService.update(userOld.copyFrom(user));
    }
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable long userId) {
    userService.delete(userId);
  }
}
