package com.jsfd.sdp.grade_management_system;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String courseName;
	
	@OneToMany(mappedBy = "courseEntity")
	private List<AssignmentEntity> assignmentEntity = new ArrayList<>();
	   
    @Lob // Specifies that this field should be treated as a large object (BLOB).
    @Column(columnDefinition = "LONGBLOB") // Explicitly map to MySQL's LONGBLOB.
	private byte[] courseImage;
	
    @ManyToMany (mappedBy = "enrolledCourses")
    private List<UserEntity> usersRegistered = new ArrayList<>();
}
