package com.faiton.logview.logview.controller;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.validation.constraints.Positive;

import java.util.Date;

import com.faiton.logview.logview.repository.LogRepository;
import com.faiton.logview.logview.util.LogRecordLineMapper;
import com.faiton.logview.logview.model.Log;
import com.faiton.logview.logview.model.LogRecord;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



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
  
  @PostMapping(value="/log/create-from-file")
  public Log createLogFromFile(@RequestParam("logFile") MultipartFile logFile) {
    Log log = new Log();
    
    try {     
      int counter = 0;
      
      
      FlatFileItemReader<LogRecord> fileItemReader = new FlatFileItemReaderBuilder<LogRecord>()
      .name("logRecordJob")
      .resource(logFile.getResource())
      .linesToSkip(1)
      .lineMapper(new LogRecordLineMapper())
      .build();
      
      ExecutionContext executionContext = new ExecutionContext();      
      fileItemReader.open(executionContext);
      
      List<LogRecord> logRecords = new ArrayList<>();
      LogRecord logRecord = null;
      
      while((logRecord = fileItemReader.read()) != null) {
        logRecords.add(logRecord);       
        
        if (counter > 100) {
          break;
        }
        
        counter++;      
      }
      
      System.out.println("Counter: " + counter);
      
      log.setRecordList(logRecords);
      
      logRepository.save(log);
      
      
      
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    
    return log;
  }
  
  
  
}
