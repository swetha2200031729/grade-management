package com.jsfd.sdp.grade_management_system;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SubmissionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private AssignmentEntity assignmentEntity; 
	
	@ManyToOne
	private UserEntity submittedBy;
	
	@CreationTimestamp
	private LocalDateTime submittedOn;
	
	@Lob 
	@Column(columnDefinition = "LONGBLOB")
	private byte[] submissionAnsFile;
	
	@OneToOne(mappedBy = "submission")
	private GradeEntity grade;
	
}
