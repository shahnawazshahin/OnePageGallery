/**
 * 
 */
package com.onepagegallery.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onepagegallery.domain.Image;
import com.onepagegallery.domain.ImageLibrary;
import com.onepagegallery.service.GalleryService;

/**
 * @author shahnawazshahin
 *
 * A REST interface for accessing the gallery features of the application.
 */
@RestController
@RequestMapping("/gallery")
public class GalleryController {

	private final GalleryService galleryService;

	@Autowired
	public GalleryController(GalleryService galleryService) {
		
		this.galleryService = galleryService;
	}
	
	@RequestMapping("/pullFromGallery")
	public ImageLibrary pullFromGallery() {
		
		return this.galleryService.retriveGallery();
	}
	
	@RequestMapping("/pushToGallery")
	public void pushToGallery(@RequestParam("file") MultipartFile file) {

		String originalFileName = file.getOriginalFilename();

		// Check that the file received does not contain any folder separators within the name.
		//
		if (originalFileName.contains("/")) {

			throw new FolderSeparatorException("Folder separator(s) (/) were used as the original file name.");
		}
		
		// Check if the file is empty. If so, throw an exception.
		//
		if (file.isEmpty()) {
			
			throw new MultipartFileIsEmptyException("The Multipart file received is empty.");
		}
		
		// Convert the MultipartFile into an image. If the file is not an image, throw an exception.
		//
		BufferedImage imageFromFile = null;

		try {

			imageFromFile = ImageIO.read(file.getInputStream());
			
			if (imageFromFile == null) {
				
				throw new FileNotImageException("The file received is not an image file.");
			}
		} catch (IOException ioEx) {
			
			throw new FileNotImageException("The file received is not an image file.");
		}

		// Transfer the information obtained from the MultipatFile to the Image.
		//
		Image image = new Image();
		image.setOriginalFileName(originalFileName);
		image.setOriginalImage(imageFromFile);

		this.galleryService.saveImageToGallery(image);
	}
}
