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

  User user1 = new User("leeclayberg", "clayberg123", "admin", "2020-06-06");
  User user2 = new User("jeffbarner", "barns14", "collector", "2020-06-07");
  User user3 = new User("johnwigner", "wigner234", "collector", "2020-06-08");
  User user4 = new User("mattsmith", "smith435", "collector", "2020-06-08");
  public List<String> history = new ArrayList<String>();
  public List<User> users = new ArrayList<User>();
  {
    users.add(user1);
    users.add(user2);
    users.add(user3);
    users.add(user4);
  }

  //Basic Operations

  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return userRepository.findAllUsers();
  }

  @GetMapping("/api/users/{userId}")
  public User findUserById(@PathVariable("userId") Integer id) {
    for(User user: users) {
      if(user.getId() == id) {
        return user;
      }
    }
    return null;
  }

  @PostMapping("/api/users")
  public User createUser(@RequestBody User user) {
    users.add(user);
    history.add(0, user.getId() + " -- created");
    return user;
  }

  @PutMapping("/api/users/{userId}")
  public User updateUser(@PathVariable("userId") Integer id, @RequestBody User newUser) {
    for(User user: users) {
      if(user.getId() == id) {
        user.updateUser(newUser);
        history.add(0, id + " -- updated profile");
        return user;
      }
    }
    return null;
  }

  @DeleteMapping("/api/users/{userId}")
  public List<User> deleteUser(@PathVariable("userId") Integer id) {
    for(User user: users) {
      if(user.getId() == id) {
        users.remove(user);
        history.add(0, user.getId() + " -- removed");
        break;
      }
    }
    return users;
  }

  //Login

  @GetMapping("/api/users/login/{username}/{password}")
  public User findUserById(@PathVariable("username") String username, @PathVariable("password") String password) {
    for(User user: users) {
      if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }

  //Comic Books Collection

  @GetMapping("/api/users/history")
  public List<String> findUserHistory() {
    return history;
  }
}
