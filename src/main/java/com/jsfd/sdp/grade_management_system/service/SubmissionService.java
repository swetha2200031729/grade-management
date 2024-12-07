package com.jsfd.sdp.grade_management_system.service;

import java.io.IOException;
import java.util.List;

import com.jsfd.sdp.grade_management_system.DTO.CreateSubmissionDTO;
import com.jsfd.sdp.grade_management_system.entity.AssignmentEntity;
import com.jsfd.sdp.grade_management_system.entity.SubmissionEntity;
import com.jsfd.sdp.grade_management_system.entity.UserEntity;

public interface SubmissionService {

	
	public SubmissionEntity findById(long id);
	
	public List<SubmissionEntity> findAll();
		
	public void deleteById(long id);
	
	public void updateById(long id);
	
	public SubmissionEntity createSubmission(CreateSubmissionDTO submissionDTO)  throws IOException;
	
	public SubmissionEntity findSubmissionByUserAndAssignment(UserEntity user, AssignmentEntity assignment);
	
}
