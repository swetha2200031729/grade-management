package com.jsfd.sdp.grade_management_system.service;

import java.util.List;

import com.jsfd.sdp.grade_management_system.DTO.CreateGradeDTO;
import com.jsfd.sdp.grade_management_system.entity.GradeEntity;

public interface GradeService {

	GradeEntity findById(long id);
	
	List<GradeEntity> findAll();
	
	void deleteById(long id);

	
	GradeEntity createGrade(CreateGradeDTO gradeDto);
	
	
	
}
