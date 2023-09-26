package com.iiitb.spe.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friends {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id1")
    private User user1;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id2")
    private User user2;
    
    @Column(nullable=false)
    private int status;

    public Friends()
    {
    	
    }
    
    
	public Friends( User user1, User user2, int status) {
		super();
		
		this.user1 = user1;
		this.user2 = user2;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

    
    
    
}
