package com.jsfd.sdp.grade_management_system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLogin(Model model) {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("user", new UserEntity());
		return "register";
	}
	
	@GetMapping("/registerTeacher")
	public String showTeacherRegister(Model model) {
		model.addAttribute("user",new UserEntity());
		return "registerTeacher";
		
	}
	@PostMapping("/registerTeacher")
	public String registerTeacherUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String dateOfBirth, @RequestParam String firstName, @RequestParam String lastName) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 
	        Date dob = null;

	        try {
	            // Parse the date string from the form input
	            dob = dateFormat.parse(dateOfBirth);
	        } catch (ParseException e) {
	            return "Invalid date format. Please use yyyy-MM-dd.";
	        }
		
		userService.createTeacherUser(username, password, email,firstName, lastName, dob);
		return "redirect:/login";

	}
	
	
	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String dateOfBirth, @RequestParam String firstName, @RequestParam String lastName) {
		
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 
	        Date dob = null;

	        try {
	            // Parse the date string from the form input
	            dob = dateFormat.parse(dateOfBirth);
	        } catch (ParseException e) {
	            return "Invalid date format. Please use yyyy-MM-dd.";
	        }
		
		userService.createStudentUser(username, password, email,firstName, lastName, dob);
		return "redirect:/login";
	}
																					
	@GetMapping("/userList")
	public String showUserList(Model model) {
	    List<UserEntity> userList = userService.findAll(); // Fetch user list from your service
	    model.addAttribute("userList", userList);
	    return "userList"; // Thymeleaf template name
	}
	

	@GetMapping("/updatelist")
	public String updateUser(@RequestParam("userId") long id , Model model) {
		UserEntity user = userService.findById(id);
		
		model.addAttribute("user",user);
		
		
		return "register";
		
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") long id) {
		return "redirect:/register";
		
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
	
	@GetMapping("/")
	public String showHomePage(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userService.findUserByUsername(username);
		model.addAttribute("user", user);
		System.out.println("Role: " + user.getRoleEntity().getRoleName());
		model.addAttribute("allowCreate", user.getRoleEntity().getRoleName().equals("ROLE_Faculty"));
		return "homepage";
	}
	
	@GetMapping("/about")
	public String aboutpage() {
		return "about";
	}
	
}
