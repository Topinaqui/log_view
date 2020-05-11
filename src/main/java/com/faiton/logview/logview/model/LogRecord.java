package com.faiton.logview.logview.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.faiton.logview.logview.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
public class LogRecord {

  @Autowired
  private static LogRecordRepository logRecordRepository;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private long id;
	
	private long logId;
	
	private Date date;
	
	private String ip;
	
	private String requestMethod;
	
	private String requestStatus;
	
	private String userAgent;
	
	public LogRecord() {}
	
	/**
	* @param date
	* @param ip
	* @param requestMethod
	* @param requestStatus
	* @param userAgent
	*/
	public LogRecord(Date date, String ip, String requestMethod, String requestStatus, String userAgent) {
		this.date = date;
		this.ip = ip;
		this.requestMethod = requestMethod;
		this.requestStatus = requestStatus;
		this.userAgent = userAgent;
	}
	
	
	
	/**
	* @return the date
	*/
	public Date getDate() {
		return date;
	}
	
	/**
	* @param date the date to set
	*/
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	* @return the ip
	*/
	public String getIp() {
		return ip;
	}
	
	/**
	* @param ip the ip to set
	*/
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	* @return the requestMethod
	*/
	public String getRequestMethod() {
		return requestMethod;
	}
	
	/**
	* @param requestMethod the requestMethod to set
	*/
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	/**
	* @return the requestStatus
	*/
	public String getRequestStatus() {
		return requestStatus;
	}
	
	/**
	* @param requestStatus the requestStatus to set
	*/
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	/**
	* @return the userAgent
	*/
	public String getUserAgent() {
		return userAgent;
	}
	
	/**
	* @param userAgent the userAgent to set
	*/
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the logId
	 */
	public long getLogId() {
		return logId;
	}

	/**
	 * @param logId the log_id to set
	 */
	public void setLogId(long logId) {
		this.logId = logId;
	}

	public LogRecord save() {

		LogRecord logRecord = null;

		if (this.logId > 0 ) {
			logRecord = logRecordRepository.save(this);
		}

		return logRecord;
	}
	
}