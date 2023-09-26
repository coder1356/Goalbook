package com.iiitb.spe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.spe.dao.CommentDao;
import com.iiitb.spe.dao.FriendDao;
import com.iiitb.spe.dao.LikeDao;
import com.iiitb.spe.dao.NotificationDao;
import com.iiitb.spe.dao.PostDao;
import com.iiitb.spe.dao.RegisterUserDao;
import com.iiitb.spe.entity.Comment;
import com.iiitb.spe.entity.CommentJson;
import com.iiitb.spe.entity.Like;
import com.iiitb.spe.entity.Notification;
import com.iiitb.spe.entity.Post;
import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.LikeJson;
import com.iiitb.spe.model.PostJson;



@Service
public class PostServiceImp implements PostService {

	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private RegisterUserDao profileDao;
	
	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	 FriendDao friendDao;
	
	
	@Autowired
	private NotificationDao notificationDao;
	
	
	
	
	@Override
	public int createPost(PostJson post) {
		try
		{
			
			Post newPost = new Post();//(profileDao.findById(post.getUser_id()).get(),post.getPost(),post.getPic(),post.getIsProject(),new Date());
			//newPost.set
			
			User user=profileDao.login((post.getUser_id()));
			newPost.setUser(user);
			newPost.setTimestamp(new Date());
			newPost.setDuration(post.getDuration());
			newPost.setGoal(post.getGoal());
			newPost.setPost_id(post.getPost_id());
			newPost.setPic(post.getPic());

			postDao.save(newPost);
			return 1;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return -1;
		}
		
	}


	


	@Override
	public int like(LikeJson likeJson) {
		// TODO Auto-generated method stub
		try {
			
			
			Post post = postDao.findByPost_id(likeJson.getPost_id());
			
			List<User> ur=likeDao.viewLike(postDao.findByPost_id(likeJson.getPost_id()));
			User user = profileDao.login(likeJson.getUser_id());
			boolean flag=false;
			for(int i=0;i<ur.size();i++)
			{
				if(user.getUser_id()==ur.get(i).getUser_id())
				{
					flag=true;
				}
			}
			
			Like like = new Like(post,user
					,new Date());
			Notification notification = new Notification(post.getUser(),user.getName()+
					" like your post","post",likeJson.getPost_id(),new  Date());
			if(!flag)
			{
			notificationDao.save(notification);
			likeDao.save(like);
			return 1;
			}
			else
			{
			int id=	likeDao.getLike(post,user);
			likeDao.deleteById(id);
			return 0;
			}
		}
		catch(Exception e)
		{
			return -1;
		}
	}


	@Override
	public void comment(CommentJson commentJson) {
		// TODO Auto-generated method stub
		
		try
		{
			Post post = postDao.findByPost_id(commentJson.getPost_id());
			User user = profileDao.login(commentJson.getUser_id());
			Comment comment = new Comment(post,
					user,commentJson.getComment(),new Date());
			
			Notification notification = new Notification(post.getUser(),user.getName()+
					" commented on your post","post",commentJson.getPost_id(),new  Date());
			if(post.getUser().getUser_id()!=user.getUser_id())
				notificationDao.save(notification);
			
			commentDao.save(comment);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	@Override
	public List<User> viewLike(String post_id) {
		
		
		return likeDao.viewLike(postDao.findByPost_id(post_id));
	}


	


	@Override
	public Post getPost(String post_id) {
		// TODO Auto-generated method stub
		return postDao.findByPost_id(post_id);
	}


	@Override
	public List<Comment> viewComment(String post_id) {
		
		return commentDao.viewComment(postDao.findByPost_id(post_id));
	}


	@Override
	public List<Post> viewPost(String user_id) {
		
		return postDao.getUserPost(profileDao.login(user_id));
	}


	@Override
	public List<Post> viewAllPost(String id) {
		
		User user1 = profileDao.login((id));
		
		List<User> friends = friendDao.getFriend(user1);
		friends.add(user1);
		List<Post> ans=new ArrayList<>();
		for(int i=0;i<friends.size();i++)
		{
		 ans.addAll(postDao.getUserPost(friends.get(i)));
	}
		return ans;
	

	}
}
