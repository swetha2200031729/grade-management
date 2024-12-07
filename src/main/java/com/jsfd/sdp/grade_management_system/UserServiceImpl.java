package com.jsfd.sdp.grade_management_system;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// In spring user details will have username, password and roles
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("%s not found", username));
		}
		return new User(
				user.getUsername(),
				user.getPassword(),
				List.of(new SimpleGrantedAuthority(user.getRoleEntity().getRoleName())) // your user role as string
		);
	}

	@Override
	public UserEntity findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	@Transactional
	@Override
	public UserEntity createStudentUser(String username, String password, String firstName, String lastName,
			String email, Date dateOfBirth) {
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setDateOfBirth(dateOfBirth);
		
		
		RoleEntity role = roleRepository.findByRoleName("Student");
	    if (role == null) {
	        throw new IllegalArgumentException("Student Role not found");
	    }
	    user.setRoleEntity(role);
	    
		return userRepository.save(user);
	}
	@Override
	public UserEntity createTeacherUser(String username, String password, String firstName, String lastName,
			String email, Date dob) {
			UserEntity user = new UserEntity();
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setDateOfBirth(dob);
			RoleEntity role = roleRepository.findByRoleName("ROLE_Faculty");
		    if (role == null) {
		        throw new IllegalArgumentException("Faculty Role not found");
		    }
		    
		    user.setRoleEntity(role);
		    
			return userRepository.save(user);
			
		
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		
		userRepository.deleteById(id);
		
	}

	@Override
	public UserEntity findById(long id) {
		Optional<UserEntity> res = userRepository.findById(id);
		
		UserEntity theUser = null;
		
		if(res.isPresent()) {
			theUser = res.get();
		}
		else {
			throw new RuntimeException("Did not find user id - " + theUser);
		}
		return theUser;
	}

	
	

}
