package com.jsfd.sdp.grade_management_system;

import java.io.IOException;
import java.util.List;

public interface CourseService {

	CourseEntity findCourseByCourseName(String name);
	
	CourseEntity findById(long id);
	
	List<CourseEntity> findAll();
	 
	void deleteById(long id);

	CourseEntity createCourse(CourseEntity course) throws IOException;
	
	void userRegistration(List<CreateCourseRegistrationDTO> registrations);
}
