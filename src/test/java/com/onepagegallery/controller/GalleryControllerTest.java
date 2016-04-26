/**
 * 
 */
package com.onepagegallery.controller;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.onepagegallery.domain.ImageLibrary;
import com.onepagegallery.gateway.ImageLibraryGateway;
import com.onepagegallery.gateway.filesystem.FilesystemImageLibraryGateway;
import com.onepagegallery.service.GalleryService;
import com.onepagegallery.service.impl.ImplGalleryService;

/**
 * @author shahnawazshahin
 *
 */
public class GalleryControllerTest {

	private GalleryController galleryController;
	private GalleryService galleryService;
	private ImageLibraryGateway imageLibraryGateway;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", "uploads-test/thumbnails");
		this.galleryService = new ImplGalleryService(this.imageLibraryGateway);
		this.galleryController = new GalleryController(this.galleryService);
	}

	@Test
	public void pullFromGallerySuccess() {

		assertSame(new ImageLibrary(this.imageLibraryGateway).getClass(), this.galleryController.pullFromGallery().getClass());
	}

	@Test(expected=NullPointerException.class)
	public void pushToGalleryNullFile() {

		this.galleryController.pushToGallery(null);
	}

	@Test(expected=FolderSeparatorException.class)
	public void pushToGalleryFolderSeparator() {

		MockMultipartFile file = new MockMultipartFile("file", "dummy/file.txt", "multipart/form-data", "".getBytes());
		
		this.galleryController.pushToGallery(file);
	}

	@Test(expected=MultipartFileIsEmptyException.class)
	public void pushToGalleryEmptyFile() {

		MockMultipartFile file = new MockMultipartFile("file", "emptyFile.txt", "multipart/form-data", "".getBytes());

		this.galleryController.pushToGallery(file);
	}

	@Test(expected=FileNotImageException.class)
	public void pushToGalleryTextFileToImage() {

		MockMultipartFile file = new MockMultipartFile("file", "file.bmp", "multipart/form-data", "text".getBytes());
		
		this.galleryController.pushToGallery(file);
	}

	@Test
	public void pushToGallerySuccess() throws IOException {

		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "JPG", byteArrayOutputStream);
		MockMultipartFile file = new MockMultipartFile("file", "file.jpg", "multipart/form-data", byteArrayOutputStream.toByteArray());

		this.galleryController.pushToGallery(file);
	}
}