package com.iiitb.spe.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="post")
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
    private String post_id;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="uid")
    private User user;
    
    @Column(length=3000)
    private String goal;
    
    private String duration;
    
    
    @Column
    private String pic;
    

    
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    
    @OneToMany(mappedBy="post")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();
    
    @OneToMany(mappedBy="post")
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();
    
//    private String like;
//
//    public String getLike() {
//		return like;
//	}
//
//
//
//	public void setLike(String like) {
//		this.like = like;
//	}



	public Post()
    {
    	
    }
    
	

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}



	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public String getGoal() {
		return goal;
	}



	public void setGoal(String goal) {
		this.goal = goal;
	}



	public String getDuration() {
		return duration;
	}



	public void setDuration(String duration) {
		this.duration = duration;
	}



	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
    
    
    
    
}
