package com.jsfd.sdp.grade_management_system;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
		
	Optional<CourseEntity> findByCourseName(String name);
	
	
}
