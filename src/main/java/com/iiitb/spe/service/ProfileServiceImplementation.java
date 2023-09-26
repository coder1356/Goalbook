package com.iiitb.spe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.spe.dao.FriendDao;
import com.iiitb.spe.dao.NotificationDao;
import com.iiitb.spe.dao.RegisterUserDao;
import com.iiitb.spe.entity.Friends;
import com.iiitb.spe.entity.Notification;
import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.LoginJson;
import com.iiitb.spe.model.UpdateProfileJson;

@Service
public class ProfileServiceImplementation implements ProfileService {

	@Autowired
	private RegisterUserDao profileDao;
	
	@Autowired
	NotificationDao notificationDao;
	
	@Autowired
	 FriendDao friendDao;
	
	@Override
	public boolean createProfile(User user) {
		try {
			profileDao.save(user);
		}
		catch (Exception e)
		{
			return false;
		}
		
		return true;
	}
	@Override
	public User getUser(int user_id)
	{
		try {
			return (User)profileDao.findById(user_id).get();
		}
		catch (Exception e)
		{
			return null;
		}
		
		
	}
	
	
	

	
	@Override
	public User login(LoginJson loginDetail)
	{
		return profileDao.login(loginDetail.getUserId());
	}
	@Override
	public List<Notification> getNotifiaction(int user_id) {
		User user = profileDao.findById(user_id).get();
		return notificationDao.getNotification(user);
	}
	@Override
	public void updateNotification(int user_id) {
		User user = profileDao.findById(user_id).get();
		notificationDao.updateNotification(user);
		
	}
	@Override
	public int getNotificationStat(int user_id) {
		User user = profileDao.findById(user_id).get();
		List<Notification> not = notificationDao.getNotificationStat(user);
		if(not.size()==0)
			return 0;
		else
			return 1;
	}
	@Override
	public void updateProfilePic(UpdateProfileJson data) {
		profileDao.updateProfile_pic(data.getValue(), data.getUser_id());
		
	}
	
	@Override
	public void updatePassword(UpdateProfileJson data) {
		// TODO Auto-generated method stub
		profileDao.updatePassword(data.getValue(), data.getUser_id());
		
	}
	@Override
	public List<UserResponse> search(ProfileSearch Key) {
		// TODO Auto-generated method stub
		String key='%'+Key.getName()+'%';
		List<User> dbres=profileDao.retrieveByName(key);
		List<UserResponse> ans=new ArrayList<>();
		for(int i=0;i<dbres.size();i++)
		{
			UserResponse UserResponse=new UserResponse();
		Friends status = friendDao.checkFriend(profileDao.login(dbres.get(i).getUser_id()), profileDao.login(Key.getUser_id()));
		UserResponse.setName(dbres.get(i).getName());
		UserResponse.setDob(dbres.get(i).getDob());
		UserResponse.setEmail(dbres.get(i).getEmail());
		UserResponse.setUrl(dbres.get(i).getUrl());
		UserResponse.setUser_id(dbres.get(i).getUser_id());
		UserResponse.setUsername(dbres.get(i).getUsername());
		if(status==null)
		{
			UserResponse.setIsfriend("0");
		}
		else
		{
			UserResponse.setIsfriend("1");	
		}
		
		ans.add(UserResponse);
		}
		return ans;
	}
	
	
	
	
	

}
