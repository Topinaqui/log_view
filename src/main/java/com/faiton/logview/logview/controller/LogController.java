package com.faiton.logview.logview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.faiton.logview.logview.repository.LogRepository;
import com.faiton.logview.logview.model.Log;


@RestController
public class LogController {

@Autowired
private LogRepository logRepository;

@GetMapping("/logs")
public List<Map<String, Object>> showLogs() {

  List<Log> logList = (List<Log>) logRepository.findAll();
  
  List<Map<String, Object>> logReferenceList = new ArrayList<>();

  logList.forEach(log -> {
    Map<String, Object> logReferenceMap  = new HashMap<>();
    logReferenceMap.put("id", log.getId());
    logReferenceMap.put("description", log.getDescription());

    logReferenceList.add(logReferenceMap);
  });
    
  return logReferenceList;
}

@GetMapping(value="/log/{id}")
public Log getMethodName(@PathVariable long id) {
    return logRepository.findById(id).orElse(new Log());
}

  
}
