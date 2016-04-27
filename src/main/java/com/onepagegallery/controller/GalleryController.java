/**
 * 
 */
package com.onepagegallery.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onepagegallery.domain.Image;
import com.onepagegallery.service.GalleryService;

/**
 * @author shahnawazshahin
 *
 * A REST interface for accessing the gallery features of the application.
 */
@Controller
public class GalleryController {

	private final GalleryService galleryService;

	@Autowired
	public GalleryController(GalleryService galleryService) {
		
		this.galleryService = galleryService;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public String pullFromGallery(Model model) {
		
		model.addAttribute("imageLibrary", this.galleryService.retriveGallery());
		
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value="/pushToGallery")
	public String pushToGallery() {
		
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/pushToGallery")
	public String pushToGallery(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		String originalFileName = file.getOriginalFilename();

		// Check that the file received does not contain any folder separators within the name.
		//
		if (originalFileName.contains("/")) {

			redirectAttributes.addFlashAttribute("message", "Folder separator(s) (/) were used as the original file name.");
			
			return "redirect:/";
		}
		
		// Check if the file is empty. If so, throw an exception.
		//
		if (file.isEmpty()) {
			
			redirectAttributes.addFlashAttribute("message", "The file received is empty.");
			return "redirect:/";
		}
		
		// Convert the MultipartFile into an image. If the file is not an image, throw an exception.
		//
		BufferedImage imageFromFile = null;

		try {

			imageFromFile = ImageIO.read(file.getInputStream());
			
			if (imageFromFile == null) {
				
				redirectAttributes.addFlashAttribute("message", "The file received is not an image file.");
				return "redirect:/";
			}
		} catch (IOException ioEx) {
			
			redirectAttributes.addFlashAttribute("message", "The file received is not an image file.");
			return "redirect:/";
		}

		// Transfer the information obtained from the MultipatFile to the Image.
		//
		Image image = new Image();
		image.setOriginalFileName(originalFileName);
		image.setOriginalImage(imageFromFile);

		this.galleryService.saveImageToGallery(image);
		
		return "redirect:/";
	}
}
