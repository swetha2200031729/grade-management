package com.jsfd.sdp.grade_management_system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfd.sdp.grade_management_system.entity.AssignmentEntity;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity,Long> {

	List<AssignmentEntity> findByCourseEntity_id(long id);
}
