 package com.jsfd.sdp.grade_management_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfd.sdp.grade_management_system.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);
	
}
									