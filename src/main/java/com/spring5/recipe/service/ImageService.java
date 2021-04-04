package com.spring5.recipe.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	void saveImage(Long reicpeId,MultipartFile file);
}
