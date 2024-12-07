package com.jsfd.sdp.grade_management_system;

import java.io.IOException;
import java.util.List;

public interface SubmissionService {

	
	public SubmissionEntity findById(long id);
	
	public List<SubmissionEntity> findAll();
		
	public void deleteById(long id);
	
	public void updateById(long id);
	
	public SubmissionEntity createSubmission(CreateSubmissionDTO submissionDTO)  throws IOException;
	
	public SubmissionEntity findSubmissionByUserAndAssignment(UserEntity user, AssignmentEntity assignment);
	
}
