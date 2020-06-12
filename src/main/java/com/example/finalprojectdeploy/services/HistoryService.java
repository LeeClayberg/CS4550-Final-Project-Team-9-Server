package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.repositories.HistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class HistoryService {

  @Autowired
  HistoryRepository historyRepository;

  @GetMapping("/api/history/recent")
  public List<HistoryAction> findRecentHistory() {
    List<HistoryAction> history = historyRepository.findRecentHistory();
    return history.size() > 20 ? history.subList(0, 20) : history;
  }

  @PostMapping("/api/history")
  public HistoryAction createHistoryAction(@RequestBody HistoryAction action) {
    return historyRepository.save(action);
  }
}
