package com.jsfd.sdp.grade_management_system.DTO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsfd.sdp.grade_management_system.entity.AssignmentEntity;
import com.jsfd.sdp.grade_management_system.entity.CourseEntity;
import com.jsfd.sdp.grade_management_system.entity.DeadLineEntity;
import com.jsfd.sdp.grade_management_system.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatAssignmentDTO {

	private String assignmentName;
	
	private String assignmentDescription;
	
	private String deadLine;
	
	private MultipartFile assignmentQueFile;
	
	private CourseEntity course;
	
	public AssignmentEntity toEntity(UserEntity user) throws IOException {
		
		AssignmentEntity entity = new AssignmentEntity();
		
		DeadLineEntity dl = new DeadLineEntity();
		
		entity.setAssignmentName(assignmentName);
		
		entity.setAssignmentDescription(assignmentDescription);
		
        entity.setDeadLine( List.of(dl));
        
        entity.setAssignmentQueFile(assignmentQueFile.getBytes());
        
        entity.setCreatedBy(user);
        
        entity.setCourseEntity(course);
        
        return entity;
        
        
	}
	
	public DeadLineEntity toDeadLineEntity(AssignmentEntity ae) {
		
		    DeadLineEntity dl = new DeadLineEntity();
		    
		    dl.setAssignment(ae);
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

	        LocalDateTime duedate = LocalDateTime.parse(deadLine, formatter);
	       
	        dl.setDueDate(duedate);
	        
	        return dl;
			
	}

	
	
}
