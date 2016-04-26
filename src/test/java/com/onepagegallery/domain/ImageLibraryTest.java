/**
 * 
 */
package com.onepagegallery.domain;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import com.onepagegallery.gateway.ImageLibraryGateway;
import com.onepagegallery.gateway.filesystem.FilesystemImageLibraryGateway;

/**
 * @author shahnawazshahin
 *
 */
public class ImageLibraryTest {

	private ImageLibraryGateway imageLibraryGateway;
	private ImageLibrary imageLibrary;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", "uploads-test/thumbnails");
		this.imageLibrary = new ImageLibrary(this.imageLibraryGateway);
	}

	@Test
	public void imageListIsNotNullAfterInstantiation() {

		assertNotNull(imageLibrary.getImages());
	}

	@Test
	public void loadImagesSuccess() {
		
		this.imageLibrary.loadImages();
	}

	@Test
	public void saveImageToLibrarySuccess() {

		BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
		Image image = new Image();
		image.setOriginalFileName("from_image_library.jpg");
		image.setOriginalImage(bufferedImage);

		this.imageLibrary.saveImageToLibrary(image);

		assertEquals(image.getThumbnailFileName(), "tmb_" + image.getOriginalFileName());
		assertNotNull(image.getThumbnailImage());
	}
}
