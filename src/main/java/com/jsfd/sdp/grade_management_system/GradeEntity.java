package com.jsfd.sdp.grade_management_system;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class GradeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private UserEntity gradedBy;
	
	@OneToOne
	private SubmissionEntity submission;
	
	@Min(value = 0, message = "Grade cannot be less than 0")
    @Max(value = 100, message = "Grade cannot be more than 100")
	private int grade;
	
	@CreationTimestamp
	private LocalDateTime gradedOn;
	
	private String feedback;
			
}
