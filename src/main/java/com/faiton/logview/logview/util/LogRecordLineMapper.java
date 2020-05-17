package com.faiton.logview.logview.util;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faiton.logview.logview.model.LogRecord;
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

        param = "";
        paramIndex++;
      }
     
    }
          
      SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");   
      
      Date date = dateFormat.parse(params[0]);
      
      String ip = params[1];
      String requestMethod =params[2];
      String requestStatus = params[3]; 
      String userAgent = params[4];
      
      LogRecord logRecord = new LogRecord(date, ip, requestMethod, requestStatus, userAgent);
      
      return logRecord;
    }
    
    
    
  }