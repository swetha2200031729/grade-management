package com.jsfd.sdp.grade_management_system.service;

import java.io.IOException;
import java.util.List;

import com.jsfd.sdp.grade_management_system.DTO.CreatAssignmentDTO;
import com.jsfd.sdp.grade_management_system.entity.AssignmentEntity;

public interface AssignmentService {

	AssignmentEntity findById(long id);
	
	List<AssignmentEntity> findAll();
	
	void deleteById(long id);
	
	void updateById(long id);
	
	AssignmentEntity createAssignment(CreatAssignmentDTO assignment) throws IOException;

	List<AssignmentEntity> findByCourseId(long courseId);

}
