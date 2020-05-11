package com.faiton.logview.logview.repository;

import org.springframework.data.repository.CrudRepository;

import com.faiton.logview.logview.model.Log;


public interface LogRepository extends CrudRepository<Log, Long> {
  
}