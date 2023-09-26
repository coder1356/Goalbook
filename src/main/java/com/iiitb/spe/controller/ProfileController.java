package com.iiitb.spe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.spe.entity.Notification;
import com.iiitb.spe.entity.User;
import com.iiitb.spe.model.LoginJson;
import com.iiitb.spe.service.ProfileSearch;
import com.iiitb.spe.service.ProfileService;
import com.iiitb.spe.service.UserResponse;



@RestController
@CrossOrigin(origins="*")
public class ProfileController {

	Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	public ProfileService profileService;


	@PostMapping(path="/Profile")
	public ResponseEntity<?> getUser(@RequestBody String user_id)
	{
		try
		{
			logger.info("Get profile for user with user_id "+user_id);
			User user = this.profileService.getUser(Integer.parseInt(user_id));

			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping(path="/Search")
	public ResponseEntity<?> search(@RequestBody ProfileSearch key)
	{
		try
		{
			logger.info("Search for "+key);
			List<UserResponse> user = this.profileService.search(key);

			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}





	@CrossOrigin(origins="*")
	@PostMapping("/register")
	public ResponseEntity<HttpStatus> createUser(@RequestBody User user)
	{
		try
		{
			logger.info("Create new user");
			this.profileService.createProfile(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/login",consumes = "application/JSON")
	public ResponseEntity<?> login(@RequestBody LoginJson loginDetail)
	{
		try
		{
			logger.info("Login for a user");
			User user_id = this.profileService.login(loginDetail);
			return new ResponseEntity<>(user_id,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			return new ResponseEntity<>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/notification")
	public ResponseEntity<?> notification(@RequestBody String user_id)
	{
		try
		{
			logger.info("Get notifications for user with user_id "+user_id);
			List<Notification> notification = this.profileService.getNotifiaction(Integer.parseInt(user_id));

			return new ResponseEntity<>(notification,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/uodateNotification")
	public ResponseEntity<?> updatenotification(@RequestBody String user_id)
	{
		try
		{
			logger.info("Update notifications for user with user_id "+user_id);
			this.profileService.updateNotification(Integer.parseInt(user_id));

			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PostMapping(path="/getNotificationStat")
	public ResponseEntity<?> getNotificationStat(@RequestBody String user_id)
	{
		try
		{
			logger.info("Get status of notificaitons for user with user_id "+user_id);
			int stat = this.profileService.getNotificationStat(Integer.parseInt(user_id));

			return new ResponseEntity<>(stat,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/updateProfilePic")
	public ResponseEntity<?> UpdateProfilePic(@RequestBody com.iiitb.spe.model.UpdateProfileJson user)
	{
		try
		{
			logger.info("Updated profile picture");
			this.profileService.updateProfilePic(user);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody com.iiitb.spe.model.UpdateProfileJson user)
	{
		try
		{
			logger.info("Updated password");
			this.profileService.updatePassword(user);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
