package com.faiton.logview.logview.util;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faiton.logview.logview.model.LogRecord;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.batch.item.file.LineMapper;


public class LogRecordLineMapper implements LineMapper<LogRecord> {
  
  private static final Logger logger = LoggerFactory.getLogger(LogRecordLineMapper.class);
  
  @Override
  public LogRecord mapLine(String line, int lineNumber) throws Exception {
    // if (line.contains("|")) {
    //   throw new DataIntegrityViolationException("Incorrect format: " + line);
    // }
    
    String[] params =  new String[5];
    String param = "";
    
    int paramIndex = 0;
    
    char actualChar = '\n';
    
    int lineLimit = line.length() - 1;
    
    for (int i = 0; i < line.length(); i++) {
      actualChar = line.charAt(i);

      if (actualChar != '|' && i != lineLimit) {
        
        param += line.charAt(i);
      } else {
       
        params[paramIndex] = param;
        
        System.out.println("Param added: " + param + " Position: " + paramIndex);

        param = "";
        paramIndex++;
      }
     
    }
    
    // Date date = null;
    // String ip = (params.length > 1)? params[1] : "";
    // String requestMethod = (params.length >= 2)? params[1] : "";
    // String requestStatus = (params.length >= 3)? params[2] : ""; 
    // String userAgent = (params.length >= 4)? params[3] : ""    
    System.out.println(line);
    
    // for (String string : params) {
      //   System.out.println(string);
      // };
      
      SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");   
      
      // Date tempDate = dateFormat.parse(params[0]);
      // Date date =  dateFormat.format(tempDate);
      
      Date date = dateFormat.parse(params[0]);
      
      String ip = params[1];
      String requestMethod =params[2];
      String requestStatus = params[3]; 
      String userAgent = params[4];
      
      LogRecord logRecord = new LogRecord(date, ip, requestMethod, requestStatus, userAgent);
      
      return logRecord;
    }
    
    
    
  }