package com.jsfd.sdp.grade_management_system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity,Long> {

	List<AssignmentEntity> findByCourseEntity_id(long id);
}
