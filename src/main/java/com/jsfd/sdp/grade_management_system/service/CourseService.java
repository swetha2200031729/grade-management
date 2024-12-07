package com.jsfd.sdp.grade_management_system.service;

import java.io.IOException;
import java.util.List;

import com.jsfd.sdp.grade_management_system.DTO.CreateCourseRegistrationDTO;
import com.jsfd.sdp.grade_management_system.entity.CourseEntity;

public interface CourseService {

	CourseEntity findCourseByCourseName(String name);
	
	CourseEntity findById(long id);
	
	List<CourseEntity> findAll();
	 
	void deleteById(long id);

	CourseEntity createCourse(CourseEntity course) throws IOException;
	
	void userRegistration(List<CreateCourseRegistrationDTO> registrations);
}
