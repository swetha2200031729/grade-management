package com.jsfd.sdp.grade_management_system;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentSerivce;

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubmissionService submissionService;

	private boolean isUserFaculty() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_Faculty"));
	}

	@GetMapping("/list/{id}")
	public String getAssignmentList(Model model, @PathVariable("id") long courseId) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("assignments", assignmentSerivce.findByCourseId(courseId));
		model.addAttribute("allowCreate", isUserFaculty());
		return "assignment-list";
	}

	private boolean isCurrentUserEnrolledToCourse(long courseId) {
		UserEntity user = userService
				.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return user.getEnrolledCourses().stream().filter(course -> course.getId() == courseId).findAny().isPresent();
	}

	@GetMapping("/show-create-assignment-form/{id}")
	public String showCreateAssignmentForm(Model model, @PathVariable("id") long courseId) {
		if (!isCurrentUserEnrolledToCourse(courseId)) {
			return "redirect:/";
		}

		CreatAssignmentDTO cr = new CreatAssignmentDTO();

		CourseEntity ce = courseService.findById(courseId);

		cr.setCourse(ce);

		model.addAttribute("assignment", cr);
		return "add-assignment";
	}

	@PostMapping("/create-assignment-form")
	public String createAssignment(@ModelAttribute CreatAssignmentDTO assignment,
			@AuthenticationPrincipal UserDetails user) throws IOException {
		if (!isCurrentUserEnrolledToCourse(assignment.getCourse().getId())) {
			return "redirect:/";
		}
		AssignmentEntity as = null;
		try {
			as = assignmentSerivce.createAssignment(assignment);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "redirect:/assignments/list/" + as.getCourseEntity().getId();

	}

	@GetMapping("/delete-assignment/{id}")
	public String deleteAssignment(Model model, @PathVariable long id) {
		long courseId = assignmentSerivce.findById(id).getCourseEntity().getId();
		if (!isCurrentUserEnrolledToCourse(courseId)) {
			return "redirect:/";
		}
		assignmentSerivce.deleteById(id);

		return "redirect:/assignments/list/" + courseId;
	}

	@GetMapping("/show-assignment-details/{id}")
	public String showAssignmentDetails(Model model, @PathVariable long id) {
		if (isUserFaculty()) {
			return "redirect:/assignments/faculty/show-assignment-details/" + id;
		}
		AssignmentEntity assignmentDetails = assignmentSerivce.findById(id);
		if (!isCurrentUserEnrolledToCourse(assignmentDetails.getCourseEntity().getId())) {
			return "redirect:/";
		}
		UserEntity user = userService
				.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		SubmissionEntity submissionEntity = submissionService.findSubmissionByUserAndAssignment(user,
				assignmentDetails);

		CreateSubmissionDTO createSubmissionDTO = new CreateSubmissionDTO();
		createSubmissionDTO.setAssignmentEntity(assignmentDetails);
		model.addAttribute("getAssignmentDetails", assignmentDetails);
		model.addAttribute("submission", createSubmissionDTO);
		model.addAttribute("existingSubmission", submissionEntity);

		return "view-assignment-details";
	}

	@ResponseBody
	@GetMapping("/download-question-file/{id}")
	public ResponseEntity<byte[]> downloadQueFile(Model model, @PathVariable long id) {
		AssignmentEntity ae = assignmentSerivce.findById(id);
		byte[] queFile = ae.getAssignmentQueFile();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_PDF);
		httpHeaders.setContentDispositionFormData(ae.getAssignmentName() + ".pdf", ae.getAssignmentName() + ".pdf");
		httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		return new ResponseEntity<>(queFile, httpHeaders, HttpStatus.OK);
	}

	@GetMapping("/faculty/show-assignment-details/{id}")
	public String showFacultyAssignmentDetails(Model model, @PathVariable long id) {
		AssignmentEntity assignmentDetails = assignmentSerivce.findById(id);
		model.addAttribute("assignmentDetails", assignmentDetails);
		return "view-assignment-details-faculty";
	}

	@PostMapping("/submit-assignment")
	public String submitAssignment(@ModelAttribute CreateSubmissionDTO submission) throws IOException {
	    SubmissionEntity se = submissionService.createSubmission(submission);
		return "redirect:/assignments/show-assignment-details/" + se.getAssignmentEntity().getId();
	}
   // updation for assignment upload
	
	@GetMapping("/submissions/file/{id}")
	public ResponseEntity<byte[]> getSubmissionFile(@PathVariable long id) {
		SubmissionEntity se = submissionService.findById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_PDF);
		String fileName = se.getAssignmentEntity().getAssignmentName() + "-submission.pdf";
		httpHeaders.setContentDispositionFormData(fileName, fileName);
		httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		httpHeaders.set("X-Frame-Options", "ALLOW");

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(se.getSubmissionAnsFile());
	}

}