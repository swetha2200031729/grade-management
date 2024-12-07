package com.jsfd.sdp.grade_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsfd.sdp.grade_management_system.DTO.CreateGradeDTO;
import com.jsfd.sdp.grade_management_system.dao.GradeRepository;
import com.jsfd.sdp.grade_management_system.dao.UserRepository;
import com.jsfd.sdp.grade_management_system.entity.GradeEntity;
import com.jsfd.sdp.grade_management_system.entity.UserEntity;

@Service
public class GradeServiceImpl implements GradeService{

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public GradeEntity findById(long id) {
		Optional<GradeEntity> grade = gradeRepository.findById(id);
		GradeEntity theGrade = null;
		if(grade.isPresent()) {
			theGrade = grade.get();
		}else {
			throw new RuntimeException("Did not find id" + theGrade);

		}
		
		return theGrade;
	}

	@Override
	public List<GradeEntity> findAll() {
	
		return gradeRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		
		gradeRepository.deleteById(id);
	}

	

	@Override
	public GradeEntity createGrade(CreateGradeDTO gradeDto) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userRepository.findByUsername(username);
		 if (user == null) {
	            throw new RuntimeException("User not found: " + username);
	        }
		System.out.println("Have fun " + gradeDto.toEntity(user).getSubmission().getId());
		 GradeEntity ge = gradeRepository.save(gradeDto.toEntity(user));
		 System.out.println("GEGE" + ge);
		return ge;
	}

	

}
