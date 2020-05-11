package com.faiton.logview.logview.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.faiton.logview.logview.repository.LogRepository;

@Entity
public class Log {
  
  
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private long id;
  
  private String description;
  
  
  @OneToMany( cascade = CascadeType.PERSIST )
  @JoinTable( joinColumns = @JoinColumn( name="log_id", referencedColumnName="id")) 
  List<LogRecord> recordList;
  
  public  Log() {}
  
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
  
  public Log save() {
    Log log = null;
    
    try {
      
      log = logRepository.save(this);
      
    } catch (Exception e) {

      System.out.println("---Erro---");
      System.out.println(logRepository);

      System.out.println("-------------------------------------------------");   
      System.out.println(this);
      
      System.out.println("-------------------------------------------------");
      System.out.println("* Verificando log salvo ");
      System.out.print("* Objeto: ");
      System.out.println(log);

      System.out.println("-------------------------------------------------");   
     
      return log;
    }
    
    /*
 else {
      System.out.println("Algo deu errado.");
    }
    */
    
    return log;
  }

  public static void sl() {

    System.out.println(" No sl: ");
    System.out.println(logRepository);
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