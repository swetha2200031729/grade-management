package com.jsfd.sdp.grade_management_system;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImp implements AssignmentService{

	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DeadLineRepository deadLineRepository;

	@Override
	public AssignmentEntity findById(long id) {
		
		Optional<AssignmentEntity> assignment = assignmentRepository.findById(id);
		
		AssignmentEntity theAssignment = null;
		
		if(assignment.isPresent()) {
			theAssignment = assignment.get();
		}
		else {
			throw new RuntimeException("Did not find user id - " + theAssignment);

		}
		return theAssignment;
	}

	@Override
	public List<AssignmentEntity> findAll() {
		
		return assignmentRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		assignmentRepository.deleteById(id);
		
	}

	@Override
	public void updateById(long id) {
		
		
	}

	@Override
	public AssignmentEntity createAssignment(CreatAssignmentDTO assignment) throws IOException {
		
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }
        
        AssignmentEntity as =   assignmentRepository.save(assignment.toEntity(user));
        assignmentRepository.flush();
        
        deadLineRepository.save(assignment.toDeadLineEntity(as));
        
        return as;
	}

	@Override
	public List<AssignmentEntity> findByCourseId(long courseId) {
		
		return assignmentRepository.findByCourseEntity_id(courseId);
	}
	
}
