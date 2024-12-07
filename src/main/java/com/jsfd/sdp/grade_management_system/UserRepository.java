 package com.jsfd.sdp.grade_management_system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);
	
}
									