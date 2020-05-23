package com.faiton.logview.logview.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Log {
  
  
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private long id;
  
  private String description;
  
  
  @OneToMany( cascade = CascadeType.PERSIST )
  @JoinColumn( name="logId") 
  List<LogRecord> recordList;
  
  public  Log() {}
  
  public  Log(String description) {
    this.description = description;
  }
  
  /**
  * @param recordList
  */
  public Log(String desc, List<LogRecord> recordList) {
    this.description = desc;
    this.recordList = recordList;
  }
  
  /**
  * @return the recordList
  */
  public List<LogRecord> getRecordList() {
    return recordList;
  }
  
  /**
  * @param recordList the recordList to set
  */
  public void setRecordList(List<LogRecord> recordList) {
    this.recordList = recordList;
  }
  
  /**
  * @return the id
  */
  public long getId() {
    return id;
  }
  
  /**
  * @param id the id to set
  */
  public void setId(long id) {
    this.id = id;
  }
  
  /**
  * @return the description
  */
  public String getDescription() {
    return description;
  }
  
  /**
  * @param description the description to set
  */
  public void setDescription(String description) {
    this.description = description;
  }
  
}