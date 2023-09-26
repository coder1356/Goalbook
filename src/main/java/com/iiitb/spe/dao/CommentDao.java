package com.iiitb.spe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiitb.spe.entity.Comment;
import com.iiitb.spe.entity.Post;
@Repository
public interface CommentDao extends JpaRepository<Comment,Integer>{

	@Query("SELECT l FROM Comment l WHERE l.post = :post order by l.timestamp")
	List<Comment> viewComment(@Param("post") Post post);
}
