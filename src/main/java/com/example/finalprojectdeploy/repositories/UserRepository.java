package com.example.finalprojectdeploy.repositories;

import com.example.finalprojectdeploy.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

  @Query("SELECT user FROM User user")
  public List<User> findAllUsers();
}
