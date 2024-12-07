package com.jsfd.sdp.grade_management_system;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseDTO {
		
	private String courseName;
	
	private MultipartFile courseImage;
	
	public CourseEntity toEntity() throws IOException {
		
		CourseEntity entity = new CourseEntity();
		entity.setCourseName(courseName);
		entity.setCourseImage(courseImage.getBytes());
		return entity;
		
	}
}
