package com.iiitb.spe.service;

import java.util.List;

import com.iiitb.spe.entity.Comment;
import com.iiitb.spe.entity.CommentJson;
import com.iiitb.spe.entity.Post;
import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.LikeJson;
import com.iiitb.spe.model.PostJson;

public interface PostService {
	
	public int createPost(PostJson post);
	public int like(LikeJson likeJson);
	public void comment(CommentJson commentJson);
	public List<User> viewLike(String post_id);
	public List<Comment> viewComment(String post_id);
	public List<Post> viewPost(String user_id);
	public List<Post> viewAllPost(String user_id);
	
	public Post getPost(String post_id);
	
}
