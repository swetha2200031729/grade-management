package com.jsfd.sdp.grade_management_system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity , Long>{
	SubmissionEntity findBySubmittedByAndAssignmentEntity(UserEntity submittedBy, AssignmentEntity assignmentEntity);
}
