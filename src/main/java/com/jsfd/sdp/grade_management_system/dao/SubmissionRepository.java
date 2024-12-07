package com.jsfd.sdp.grade_management_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfd.sdp.grade_management_system.entity.AssignmentEntity;
import com.jsfd.sdp.grade_management_system.entity.SubmissionEntity;
import com.jsfd.sdp.grade_management_system.entity.UserEntity;
@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity , Long>{
	SubmissionEntity findBySubmittedByAndAssignmentEntity(UserEntity submittedBy, AssignmentEntity assignmentEntity);
}
