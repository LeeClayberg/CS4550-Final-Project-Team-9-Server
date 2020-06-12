package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.User;
import com.example.finalprojectdeploy.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserService {

  @Autowired
  UserRepository userRepository;

  //Basic Operations

  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return userRepository.findAllUsers();
  }

  @GetMapping("/api/users/{userId}")
  public User findUserById(@PathVariable("userId") Integer id) {
    return userRepository.findUserById(id);
  }

  @PostMapping("/api/users")
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("/api/users/{userId}")
  public User updateUser(@PathVariable("userId") Integer id, @RequestBody User newUser) {
    User user = userRepository.findById(id).get();
    user.updateUser(newUser);
    return userRepository.save(user);
  }

  @DeleteMapping("/api/users/{userId}")
  public void deleteUser(@PathVariable("userId") Integer id) {
    userRepository.deleteById(id);
  }

  //Login

  @GetMapping("/api/users/login/{username}/{password}")
  public User findUserById(@PathVariable("username") String username, @PathVariable("password") String password) {
    return userRepository.userLogin(username, password);
  }



  //History (come back to)

  @GetMapping("/api/users/history")
  public List<String> findUserHistory() {
    return new ArrayList<>();
  }
}
