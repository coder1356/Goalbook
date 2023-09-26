package com.iiitb.spe.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.spe.dao.LikeDao;
import com.iiitb.spe.dao.PostDao;
import com.iiitb.spe.entity.Comment;
import com.iiitb.spe.entity.CommentJson;
import com.iiitb.spe.entity.Post;
import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.LikeJson;
import com.iiitb.spe.model.PostJson;
import com.iiitb.spe.model.PostResponse;
import com.iiitb.spe.model.Post_Id;
import com.iiitb.spe.model.UserId;
import com.iiitb.spe.service.PostService;

@CrossOrigin(origins="*")
@RestController
public class PostController {
	
	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private PostDao postDao;

	Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@PostMapping(path="/create-post")
	public ResponseEntity<?> createPost(@RequestBody PostJson post)
	{
		try
		{
			logger.info("Create post");
			int post_id=this.postService.createPost(post);
			return new ResponseEntity<>(post_id,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/view-post")
	public ResponseEntity<?> viewPost(@RequestBody String post_id)
	{
		try
		{
			logger.info("View post");

			Post post = this.postService.getPost((post_id));

			return new ResponseEntity<>(post,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping(path="/get-user-post")
	public ResponseEntity<?> getuserPost(@RequestBody UserId user_id)
	{
		try
		{
			logger.info("Get post");
			List<PostResponse> response=new ArrayList<>();

			List<Post> post = this.postService.viewPost((user_id.getUser_id()));
			
			for(int i=0;i<post.size();i++)
			{
				PostResponse postResponse=new PostResponse();
				postResponse.setDuration(post.get(i).getDuration());
				postResponse.setGoal(post.get(i).getGoal());
				postResponse.setPic(post.get(i).getPic());
				postResponse.setPost_id(post.get(i).getPost_id());
				postResponse.setTimestamp(post.get(i).getTimestamp());
				postResponse.setUser(post.get(i).getUser());
				List<User>  us= likeDao.viewLike(postDao.findByPost_id(post.get(i).getPost_id()));
				postResponse.setLikes(us.size());
				 response.add(postResponse);
			}

			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/get-all-post")
	public ResponseEntity<?> getAllPost(@RequestBody UserId user_id)
	{
		try
		{
			logger.info("Get all post");
			List<PostResponse> response=new ArrayList<>();
			List<Post> post = this.postService.viewAllPost(user_id.getUser_id());

			for(int i=0;i<post.size();i++)
			{
				PostResponse postResponse=new PostResponse();
				postResponse.setDuration(post.get(i).getDuration());
				postResponse.setGoal(post.get(i).getGoal());
				postResponse.setPic(post.get(i).getPic());
				postResponse.setPost_id(post.get(i).getPost_id());
				postResponse.setTimestamp(post.get(i).getTimestamp());
				postResponse.setUser(post.get(i).getUser());
				List<User>  us= likeDao.viewLike(postDao.findByPost_id(post.get(i).getPost_id()));
				postResponse.setLikes(us.size());
				 response.add(postResponse);
			}
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping(path="/like-post")
	public ResponseEntity<?> likePost(@RequestBody LikeJson likeJson)
	{
		try
		{
			logger.info("Like post");

			int res=this.postService.like(likeJson);

			if(res==1)
			{
			return new ResponseEntity<>(true,HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(false,HttpStatus.OK);
				
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/comment-on-post")
	public ResponseEntity<?> commentPost(@RequestBody CommentJson commentJson)
	{
		try
		{
			logger.info("Comment post");

			this.postService.comment(commentJson);

			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping(path="/get-like")
	public ResponseEntity<?> getLike(@RequestBody Post_Id post_id)
	{
		try
		{
			logger.info("Get like of post");

			List<User> usr=this.postService.viewLike((post_id.getPost_id()));

			return new ResponseEntity<>(usr,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/get-comment")
	public ResponseEntity<?> getComment(@RequestBody Post_Id post_id)
	{
		try
		{
			logger.info("Get comment of post");

			List<Comment> cmt=this.postService.viewComment((post_id.getPost_id()));

			return new ResponseEntity<>(cmt,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}







}
