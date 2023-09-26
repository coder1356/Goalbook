package com.iiitb.spe.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Notification {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    
    @Column
    private String notification;
    
    @Column
    private String type;
    
    @Column 
    private String pid;
    
    @Column
    private int stat;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    
    public Notification()
    {
    	
    }
    
	public Notification(User user, String notification, String type, String pid, Date timestamp) {
		super();
		
		this.user = user;
		this.notification = notification;
		this.type = type;
		this.pid = pid;
		this.timestamp = timestamp;
		this.stat=0;
	
	}
	public Notification( User user, String notification, String type,Date timestamp) {
		super();
		
		this.user = user;
		this.notification = notification;
		this.type = type;
		this.timestamp = timestamp;
		this.stat=0;
		
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


    
    
    
	
	
	
	
	

}
