package com.jsfd.sdp.grade_management_system;

import java.io.IOException;
import java.util.List;

public interface AssignmentService {

	AssignmentEntity findById(long id);
	
	List<AssignmentEntity> findAll();
	
	void deleteById(long id);
	
	void updateById(long id);
	
	AssignmentEntity createAssignment(CreatAssignmentDTO assignment) throws IOException;

	List<AssignmentEntity> findByCourseId(long courseId);

}
