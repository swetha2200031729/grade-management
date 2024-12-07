package com.jsfd.sdp.grade_management_system.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfd.sdp.grade_management_system.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
		
	Optional<CourseEntity> findByCourseName(String name);
	
	
}
