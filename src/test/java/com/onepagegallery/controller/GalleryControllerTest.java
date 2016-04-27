/**
 * 
 */
package com.onepagegallery.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.onepagegallery.Application;
import com.onepagegallery.gateway.ImageLibraryGateway;
import com.onepagegallery.gateway.filesystem.FilesystemImageLibraryGateway;
import com.onepagegallery.service.GalleryService;
import com.onepagegallery.service.impl.ImplGalleryService;

/**
 * @author shahnawazshahin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GalleryControllerTest {

	private GalleryController galleryController;
	private GalleryService galleryService;
	private ImageLibraryGateway imageLibraryGateway;
	
	private MockMvc mockMvc;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", "uploads-test/thumbnails");
		this.galleryService = new ImplGalleryService(this.imageLibraryGateway);
		this.galleryController = new GalleryController(this.galleryService);
		
		mockMvc = MockMvcBuilders.standaloneSetup(new GalleryController(this.galleryService)).build();
	}

	@Test
	public void pullFromGallerySuccess() throws Exception {

		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test(expected=NullPointerException.class)
	public void pushToGalleryNullFile() {

		this.galleryController.pushToGallery(null, null);
	}

	@Test
	public void pushToGalleryFolderSeparator() throws Exception {

		MockMultipartFile file = new MockMultipartFile("file", "dummy/file.txt", "multipart/form-data", "".getBytes());
		
		mockMvc.perform(fileUpload("/pushToGallery").file(file))
			.andExpect(status().is3xxRedirection())
			.andExpect(flash().attribute("message", "Folder separator(s) (/) were used as the original file name."));
	}

	@Test
	public void pushToGalleryEmptyFile() throws Exception {

		MockMultipartFile file = new MockMultipartFile("file", "emptyFile.txt", "multipart/form-data", "".getBytes());
		
		mockMvc.perform(fileUpload("/pushToGallery").file(file))
			.andExpect(status().is3xxRedirection())
			.andExpect(flash().attribute("message", "The file received is empty."));
	}

	@Test
	public void pushToGalleryTextFileToImage() throws Exception {

		MockMultipartFile file = new MockMultipartFile("file", "file.bmp", "multipart/form-data", "text".getBytes());

		mockMvc.perform(fileUpload("/pushToGallery").file(file))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("message", "The file received is not an image file."));
	}

	@Test
	public void pushToGalleryGetRequest() throws Exception {
		
		mockMvc.perform(get("/pushToGallery")).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void pushToGallerySuccess() throws Exception {

		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "JPG", byteArrayOutputStream);
		MockMultipartFile file = new MockMultipartFile("file", "file.jpg", "multipart/form-data", byteArrayOutputStream.toByteArray());

		mockMvc.perform(fileUpload("/pushToGallery").file(file))
		.andExpect(status().is3xxRedirection());
	}
}