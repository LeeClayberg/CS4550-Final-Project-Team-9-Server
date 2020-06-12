package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.model.User;
import com.example.finalprojectdeploy.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  HistoryService historyService = new HistoryService();

  //Basic Operations

  public List<User> findAllUsers() {
    return userRepository.findAllUsers();
  }

  public User findUserById(Integer id) {
    return userRepository.findUserById(id);
  }

  public User createUser(User user) {
    User newUser = userRepository.save(user);
    historyService.createHistoryAction(new HistoryAction(newUser.getId(), "created"));
    return newUser;
  }

  public User updateUser(Integer id, User newUser) {
    historyService.createHistoryAction(new HistoryAction(id, "updated"));
    User user = userRepository.findById(id).get();
    user.updateUser(newUser);
    return userRepository.save(user);
  }

  public void deleteUser(Integer id) {
    historyService.createHistoryAction(new HistoryAction(id, "deleted"));
    userRepository.deleteById(id);
  }

  //Login

  public User userLogin(String username, String password) {
    return userRepository.userLogin(username, password);
  }
}
