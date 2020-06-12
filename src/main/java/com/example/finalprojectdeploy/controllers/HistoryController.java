package com.example.finalprojectdeploy.controllers;

import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.services.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HistoryController {

  @Autowired
  HistoryService historyService;

  @GetMapping("/api/history/recent")
  public List<HistoryAction> findRecentHistory() {
    return historyService.findRecentHistory();
  }
}
