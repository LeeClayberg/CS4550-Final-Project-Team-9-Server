package com.example.finalprojectdeploy.controllers;

import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.model.User;
import com.example.finalprojectdeploy.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("/api/users/{userId}")
  public User findUserById(@PathVariable("userId") Integer id) {
    return userService.findUserById(id);
  }

  @PostMapping("/api/users")
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/api/users/{userId}")
  public User updateUser(@PathVariable("userId") Integer id, @RequestBody User newUser) {
    return userService.updateUser(id, newUser);
  }

  @DeleteMapping("/api/users/{userId}")
  public void deleteUser(@PathVariable("userId") Integer id) {
    userService.deleteUser(id);
  }

  //Login

  @GetMapping("/api/users/login/{username}/{password}")
  public User findUserById(@PathVariable("username") String username, @PathVariable("password") String password) {
    return userService.userLogin(username, password);
  }
}
