package com.faiton.logview.logview.util;

import java.util.Date;

import com.faiton.logview.logview.model.LogRecord;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.batch.item.file.LineMapper;


public class LogRecordLineMapper implements LineMapper<LogRecord> {
  
  @Override
  public LogRecord mapLine(String line, int lineNumber) throws Exception {
    String[] params = line.split("|");
    
    if (params.length < 1) {
      throw new DataIntegrityViolationException("Expecting at least one token in input line: " + line);
    }

    Date date = null;
    String ip = (params.length > 1)? params[1] : "";
    String requestMethod = (params.length >= 2)? params[1] : "";
    String requestStatus = (params.length >= 3)? params[2] : ""; 
    String userAgent = (params.length >= 4)? params[3] : "";;

    LogRecord logRecord = new LogRecord(date, ip, requestMethod, requestStatus, userAgent);
    
    return logRecord;
  }
  
  
  
}