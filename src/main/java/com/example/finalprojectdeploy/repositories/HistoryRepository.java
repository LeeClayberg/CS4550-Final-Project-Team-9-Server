package com.example.finalprojectdeploy.repositories;

import com.example.finalprojectdeploy.model.HistoryAction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<HistoryAction, Integer> {

  @Query("SELECT action FROM HistoryAction action ORDER BY timestamp DESC")
  public List<HistoryAction> findRecentHistory();
}
