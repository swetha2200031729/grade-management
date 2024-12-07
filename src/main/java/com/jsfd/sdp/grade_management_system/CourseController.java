package com.jsfd.sdp.grade_management_system;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.ToString;

@Controller
@RequestMapping("/courses")
@ToString
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private UserRepository userRepository;
    
    private boolean isUserStudent() {
    	return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getRoleEntity().getRoleName().equals( "Student");
    }
    
    @GetMapping("/list")
    public String getAllCourses(Model model) {
    	model.addAttribute("allowCreate", !isUserStudent());
       model.addAttribute("courses", courseService.findAll());
        return "course-list";
    }

    
    @GetMapping("/showCreateCourseForm")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("course", new CreateCourseDTO());
        return "add-course"; 
    }

   
    @PostMapping
    public String createCourse(@ModelAttribute CreateCourseDTO course  ) throws IOException {
        courseService.createCourse(course.toEntity());
        return "redirect:/courses/list"; 
    }

   
    @GetMapping("/edit/{courseName}")
    public String showUpdateCourseForm(@PathVariable String courseName, Model model) {
        CourseEntity course = courseService.findCourseByCourseName(courseName);
        if (course == null) {
            throw new RuntimeException("Course not found with name: " + courseName);
        }
        model.addAttribute("course", course);
        return "add-course"; 
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
       CourseEntity entity = courseService.findById(id);
        byte[] imageData = entity.getCourseImage();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Set correct MIME type
                .body(imageData);
    }
    
    // Handle deleting a course
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable long id) {
        courseService.deleteById(id);
        return "redirect:/courses/list"; // Redirect to list of courses after deletion
    }
    
    
}
