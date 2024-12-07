package com.jsfd.sdp.grade_management_system.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsfd.sdp.grade_management_system.DTO.CreateSubmissionDTO;
import com.jsfd.sdp.grade_management_system.dao.SubmissionRepository;
import com.jsfd.sdp.grade_management_system.dao.UserRepository;
import com.jsfd.sdp.grade_management_system.entity.AssignmentEntity;
import com.jsfd.sdp.grade_management_system.entity.SubmissionEntity;
import com.jsfd.sdp.grade_management_system.entity.UserEntity;

@Service
public class SubmissionServiceImpl implements SubmissionService {

	@Autowired
	private SubmissionRepository submissionRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public SubmissionEntity findById(long id) {

		Optional<SubmissionEntity> submition = submissionRepository.findById(id);

		SubmissionEntity theSubmission = null;

		if (submition.isPresent()) {
			theSubmission = submition.get();
		} else {
			throw new RuntimeException("Did not find id" + theSubmission);
		}

		return theSubmission;
	}

	@Override
	public List<SubmissionEntity> findAll() {

		return submissionRepository.findAll();
	}

	@Override
	public void deleteById(long id) {

		submissionRepository.deleteById(id);

	}

	@Override
	public void updateById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SubmissionEntity createSubmission(CreateSubmissionDTO submissionDTO) throws IOException {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userRepository.findByUsername(username);
		 if (user == null) {
	            throw new RuntimeException("User not found: " + username);
	        }
		SubmissionEntity existingSubmission = submissionRepository.findBySubmittedByAndAssignmentEntity(user, submissionDTO.getAssignmentEntity());
		if (existingSubmission != null) {
			submissionRepository.deleteById(existingSubmission.getId());
		}
		SubmissionEntity se = submissionRepository.save(submissionDTO.toEntity(user));

		return se;
	}

	@Override
	public SubmissionEntity findSubmissionByUserAndAssignment(UserEntity user, AssignmentEntity assignment) {
		return submissionRepository.findBySubmittedByAndAssignmentEntity(user, assignment);
	}

}
