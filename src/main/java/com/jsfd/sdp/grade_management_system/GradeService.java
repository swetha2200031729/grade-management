package com.jsfd.sdp.grade_management_system;

import java.util.List;

public interface GradeService {

	GradeEntity findById(long id);
	
	List<GradeEntity> findAll();
	
	void deleteById(long id);

	
	GradeEntity createGrade(CreateGradeDTO gradeDto);
	
	
	
}
