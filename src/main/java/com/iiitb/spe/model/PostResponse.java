package com.iiitb.spe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iiitb.spe.entity.User;

public class PostResponse {

	  private String post_id;
		
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="uid")
	    private User user;
	    
	    @Column(length=3000)
	    private String goal;
	    
	    private String duration;
	    
	    
	    @Column
	    private String pic;
	    
	    private int likes;
	    

	    
	    
	    public int getLikes() {
			return likes;
		}

		public void setLikes(int likes) {
			this.likes = likes;
		}



		@Column(nullable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date timestamp;
	    
	   


		
		

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



	
	    
}
