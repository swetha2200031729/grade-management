package com.jsfd.sdp.grade_management_system;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Controller
@RequestMapping("/registrations")
public class CourseRegistrationController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/upload-registration-file")
	public String showUploadeForm(Model model) {
		model.addAttribute("uploadForm", new FileUploadDTO());

		return "add-courses-registration";
	}

	@PostMapping("/upload-registration-file")
	public String getfile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		if (file.isEmpty()) {
			model.addAttribute("message", "Select a valid csv file.");
			return "add-courses-registration";
		}

		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = csvMapper.schemaFor(CreateCourseRegistrationDTO.class)
				.withColumnSeparator(',');

		List<Object> registationsObject = csvMapper.readerFor(CreateCourseRegistrationDTO.class).with(schema)
				.readValues(file.getInputStream()).readAll();
		List<CreateCourseRegistrationDTO> registrations = registationsObject.stream()
				.map(obj -> (CreateCourseRegistrationDTO) obj).collect(Collectors.toList());
		
		System.out.println("Sure " + registrations);

		courseService.userRegistration(registrations);

		model.addAttribute("meassage", "Registraions mapped successfully");
		return "redirect:/";
	}
}
