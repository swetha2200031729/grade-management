package com.jsfd.sdp.grade_management_system.DTO;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.jsfd.sdp.grade_management_system.entity.AssignmentEntity;
import com.jsfd.sdp.grade_management_system.entity.SubmissionEntity;
import com.jsfd.sdp.grade_management_system.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateSubmissionDTO {
	
	private MultipartFile submissionAnsFile;
	
	private AssignmentEntity assignmentEntity;
	
	
	public SubmissionEntity toEntity(UserEntity user) throws IOException {
		
		SubmissionEntity entity = new SubmissionEntity();
		
		entity.setAssignmentEntity(assignmentEntity);
		
		entity.setSubmissionAnsFile(submissionAnsFile.getBytes());
		
		entity.setSubmittedBy(user);
		
		return entity;
	}
	
}
