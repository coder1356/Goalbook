package com.iiitb.spe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiitb.spe.entity.Post;
import com.iiitb.spe.entity.User;
@Repository
public interface PostDao extends JpaRepository<Post,Integer>{
	
	@Query("SELECT p FROM Post p WHERE p.user = :user order by p.timestamp desc")
	List<Post> getUserPost(@Param("user") User user);
	
	@Query("SELECT p FROM Post p order by p.timestamp desc")
	List<Post> getAllPost();

	@Query("SELECT p FROM Post p WHERE p.post_id = :id")
	Post findByPost_id(@Param("id") String post_id);
	
	

}
