package com.iiitb.spe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.spe.dao.FriendDao;
import com.iiitb.spe.dao.NotificationDao;
import com.iiitb.spe.dao.RegisterUserDao;
import com.iiitb.spe.entity.Friends;
import com.iiitb.spe.entity.Notification;
import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.FriendJson;

@Service
public class FriendServiceImp implements FriendService {

	
	@Autowired
	 FriendDao friendDao;
	
	@Autowired
	 NotificationDao notificationDao;

	@Autowired
	 RegisterUserDao profileDao;
	
	@Override
	public boolean friendRequestSend(FriendJson friend) {
		try {
		User user1 = profileDao.login(friend.getUser_id1());
		User user2 = profileDao.login(friend.getUser_id2());
		Notification notification = new Notification(user2,user1.getName()+
				" sent you friend Request","request",new Date());
		notificationDao.save(notification);
		friendDao.save(new Friends(user1,user2,1));
		friendDao.save(new Friends(user2,user1,1));
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}




	@Override
	public boolean friendRequestAccept(FriendJson friend) {
		try {
			User user1 = profileDao.login(friend.getUser_id1());
			User user2 = profileDao.login(friend.getUser_id2());
			
			Notification notification = new Notification(user2,user1.getName()+
					" accepted your friend Request","accept",new Date());
			notificationDao.save(notification);
			
			friendDao.save(new Friends(user1,user2,1));
			friendDao.acceptFriendreq(1, user2, user1);
			}
			catch(Exception e)
			{
				return false;
			}
			return true;
		
	}

	@Override
	public int checkStatus(FriendJson friend) {
		/*0-friendrequest send
		 * 1-friend 
		 * 3-friend pending
		 * -1 
	
		 */
		User user1 = profileDao.login(friend.getUser_id1());
		User user2 = profileDao.login(friend.getUser_id2());
		Friends status = friendDao.checkFriend(user1, user2);
		if(status==null)
		{
			 status = friendDao.checkFriend(user2, user1);
			if(status==null)
					return -1;
			return 3;
		}
		return (status.getStatus());
	}

	@Override
	public List<User> getFriends(int user_id) {
		User user1 = profileDao.findById(user_id).get();
	
		List<User> friends = friendDao.getFriend(user1);

		return friends;
	}

	@Override
	public boolean unfriend(FriendJson friend) {
		
		User user1 = profileDao.login(friend.getUser_id1());
		User user2 = profileDao.login(friend.getUser_id2());
		try
		{
			friendDao.unFriend(user1,user2);
			friendDao.unFriend(user2,user1);
			return true;
			
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	




	

}
