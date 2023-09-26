package com.iiitb.spe.service;

import java.util.List;

import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.FriendJson;



public interface FriendService {
	
	
	public boolean friendRequestSend(FriendJson friend);
	public boolean friendRequestAccept(FriendJson friend);
	public int checkStatus(FriendJson friend);
	public List<User> getFriends(int user_id);
	public boolean unfriend(FriendJson friend);
	

}
