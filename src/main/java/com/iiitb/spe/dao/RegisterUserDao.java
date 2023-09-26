package com.iiitb.spe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iiitb.spe.entity.User;

@Component
public interface RegisterUserDao extends JpaRepository<User,Integer> {
	
	@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
	User retrieveByEmail(@Param("email") String email);
	
	
	
	@Query("SELECT u FROM User u WHERE LOWER(u.name) like LOWER(:name)")
	List<User> retrieveByName(@Param("name") String name);
	
	
	@Transactional
	@Modifying
	@Query("Update User set password= :password where id= :user_id")
	int updatePassword(@Param("password") String password, @Param("user_id") int user_id);
	
	@Transactional
	@Modifying
	@Query("Update User set profile_pic= :profile_pic where id= :user_id")
	int updateProfile_pic(@Param("profile_pic") String profile_pic, @Param("user_id") int user_id);
	
	@Query("SELECT u FROM User u WHERE u.user_id= :userId")
	User login(@Param("userId") String userId);



	
	
	
	
	

}
