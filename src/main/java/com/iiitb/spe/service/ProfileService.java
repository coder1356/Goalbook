package com.iiitb.spe.service;

import java.util.List;

import com.iiitb.spe.entity.Notification;
import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.LoginJson;
import com.iiitb.spe.model.UpdateProfileJson;

public interface ProfileService {
	
	public boolean createProfile(User user);
	
	public User getUser(int user_id);
	
	public List<UserResponse> search(ProfileSearch Key);
	
	
	
	public User login(LoginJson loginDetail);
	
	public List<Notification> getNotifiaction(int user_id);
	
	public void updateNotification(int user_id);
	public int  getNotificationStat(int user_id);
	
	public void updateProfilePic(UpdateProfileJson data);
	

	public void updatePassword(UpdateProfileJson data);

}
