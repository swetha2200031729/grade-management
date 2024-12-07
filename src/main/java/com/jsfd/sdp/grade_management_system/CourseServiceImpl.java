package com.jsfd.sdp.grade_management_system;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public CourseEntity findCourseByCourseName(String name) {
		courseRepository.findByCourseName(name);
		return null;
		
	}

	@Override
	public CourseEntity createCourse(CourseEntity course)  {
		
	    return courseRepository.save(course);
		
	}

	

	@Override
	public List<CourseEntity> findAll() {
		return courseRepository.findAll();
	
	}

	@Override
	public void deleteById(long id) {
		
		courseRepository.deleteById(id);
		
	}

	@Override
	public CourseEntity findById(long id) {
		
		Optional<CourseEntity> course =  courseRepository.findById(id);
		
		CourseEntity theCourse = null;
		
		if(course.isPresent()) {
			theCourse = course.get();
		}
		else 
		{ 
			throw new RuntimeException("Did not find user id - " + theCourse);
		}
		return theCourse;
		
		
	}

	@Override
	@Transactional
	public void userRegistration(List<CreateCourseRegistrationDTO> registrations) {
		System.out.println("Whaat" + registrations.size());
		registrations.forEach(registration -> {
			long studentId = Long.parseLong(String.valueOf(registration.getStudentId()));
			long courseId = Long.parseLong(String.valueOf(registration.getCourseId()));
			System.out.println("RRRR" + studentId + " " + courseId);
			CourseEntity ce =  courseRepository.findById(courseId).get();
			UserEntity ue = userRepository.findById(studentId).get();
			ue.getEnrolledCourses().add(ce);
			// Note: join table side u should do the save
			userRepository.save(ue);
			System.out.println("DUDEEEEEEE");
		} );
		courseRepository.flush();
		
	}
	
}
