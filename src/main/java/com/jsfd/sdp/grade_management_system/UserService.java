package com.jsfd.sdp.grade_management_system;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


/*
 * Spring Security needs something called UserDetailsService that provides
 * access to user info.
 * 
 * We write an impl of user details service and provide it to our security config
 * */
public interface UserService extends UserDetailsService {

	UserEntity findUserByUsername(String username);
	
	UserEntity createStudentUser(String username, String password, String firstName, String lastName, String email, Date dob);
	
	UserEntity createTeacherUser(String username, String password, String firstName, String lastName, String email, Date dob);

	List<UserEntity> findAll();
	
	void deleteById(long id);
	
	UserEntity findById(long id);
}
