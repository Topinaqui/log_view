package com.faiton.logview.logview.repository;

import org.springframework.data.repository.CrudRepository;

import com.faiton.logview.logview.model.LogRecord;


public interface LogRecordRepository extends CrudRepository<LogRecord, Long> {
  
}