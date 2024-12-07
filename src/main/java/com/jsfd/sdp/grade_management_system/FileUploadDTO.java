package com.jsfd.sdp.grade_management_system;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadDTO {
	
	private MultipartFile file;
}
