package com.jsfd.sdp.grade_management_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfd.sdp.grade_management_system.entity.RoleEntity;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	RoleEntity findByRoleName(String roleName);
	
}
