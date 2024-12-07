package com.jsfd.sdp.grade_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grades")
public class GradeController {

	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/show-create-grade-form/{submissionId}")
	public String showCreateGradeForm(Model model,@PathVariable long submissionId) {
		CreateGradeDTO cg= new CreateGradeDTO();
		SubmissionEntity submissionDetails = submissionService.findById(submissionId);
		cg.setSubmission(submissionDetails);
		model.addAttribute("grade",cg);
		return "add-grade";
	}
	
	@PostMapping("/create-grade-form")
	public String createGrade(@ModelAttribute CreateGradeDTO grade) {
		SubmissionEntity submission = submissionService.findById(grade.getSubmission().getId());
		grade.setSubmission(submission);
		GradeEntity s = gradeService.createGrade(grade);
		return "redirect:/assignments/faculty/show-assignment-details/" + s.getSubmission().getAssignmentEntity().getId();
	}
	
	
	@GetMapping("/update-grade-form/{id}")
	public String updateGradeForm(Model model , @PathVariable long id) {
		 GradeEntity gradeDetails = gradeService.findById(id);
		 model.addAttribute("grade", gradeDetails);
		return "add-grade";
	}
	
	
	@GetMapping("/delete-grade/{id}")
	public String deleteGrade(Model model , @PathVariable long id) {
		long submissionId = gradeService.findById(id).getSubmission().getId();
		gradeService.deleteById(id);
		
		return "redirect:/assignments/faculty/show-assignment-details/" + submissionId;
	}
	
	@GetMapping("/view-grade-details/{id}")
	public String viewGradeDetails(@PathVariable long id, Model model) {
	    GradeEntity grade = gradeService.findById(id);
	    model.addAttribute("grade", grade);
	    return "view-grade-details";
	}

}
