package com.jsfd.sdp.grade_management_system;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGradeDTO {
	
	@Min(value = 0, message = "Grade cannot be less than 0")
    @Max(value = 100, message = "Grade cannot be more than 100")
	private int grade;
	
	private SubmissionEntity submission;
	
	@CreationTimestamp
	private LocalDateTime gradedOn;
	
	private String feedback;
	
	public GradeEntity toEntity(UserEntity user){
		
		GradeEntity entity = new GradeEntity();
		entity.setGrade(grade);
		entity.setGradedOn(gradedOn);
		entity.setFeedback(feedback);
		entity.setGradedBy(user);
		entity.setSubmission(submission);
		return entity;
	}
	
}
