/**
 * 
 */
package com.onepagegallery.gateway.filesystem;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.onepagegallery.domain.Image;
import com.onepagegallery.domain.ImageLibrary;
import com.onepagegallery.gateway.ImageLibraryGateway;

/**
 * @author shahnawazshahin
 *
 */
public class FileSystemImageLibraryGatewayTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=NullPointerException.class)
	public void nullRootFolder() {

		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway(null, "uploads/thumbnails");
		ImageLibrary imageLibrary = new ImageLibrary(imageLibraryGateway);

		imageLibraryGateway.loadImageLibrary(imageLibrary);
	}
	
	@Test(expected=FolderDoesNotExistException.class)
	public void nonExistentRootFolder() {

		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway("doesnotexist", "uploads-test/thumbnails");
		ImageLibrary imageLibrary = new ImageLibrary(imageLibraryGateway);

		imageLibraryGateway.loadImageLibrary(imageLibrary);
	}
	
	@Test(expected=NullPointerException.class)
	public void nullThumbnailFolder() {

		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", null);
		ImageLibrary imageLibrary = new ImageLibrary(imageLibraryGateway);

		imageLibraryGateway.loadImageLibrary(imageLibrary);
	}
	
	@Test(expected=FolderDoesNotExistException.class)
	public void nonExistentThumbnailFolder() {

		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", "uploads-test/doesnotexist");
		ImageLibrary imageLibrary = new ImageLibrary(imageLibraryGateway);

		imageLibraryGateway.loadImageLibrary(imageLibrary);
	}	

	@Test
	public void validFolders() {

		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", "uploads-test/thumbnails");
		ImageLibrary imageLibrary = new ImageLibrary(imageLibraryGateway);

		imageLibraryGateway.loadImageLibrary(imageLibrary);
	}
	
	@Test
	public void loadEmptyLibrary() {

		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-empty", "uploads-empty/thumbnails");
		ImageLibrary imageLibrary = new ImageLibrary(imageLibraryGateway);

		imageLibraryGateway.loadImageLibrary(imageLibrary);

		assertTrue(imageLibrary.getImages().isEmpty());
	}

	@Test(expected=CannotWriteToFileException.class)
	public void saveImageCannotWriteToFile() throws IOException {

		BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);

		Image image = new Image();
		image.setOriginalFileName("test.jpg");
		image.setThumbnailFileName("test.jpg");
		image.setOriginalImage(bufferedImage);
		image.setThumbnailImage(bufferedImage);
		
		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway("nonexistent", "nonexistent/thumbnails");
		imageLibraryGateway.saveImage(image);
	}

	@Test
	public void saveImageSuccess() {
		
		BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);

		Image image = new Image();
		image.setOriginalFileName("test.jpg");
		image.setThumbnailFileName("test.jpg");
		image.setOriginalImage(bufferedImage);
		image.setThumbnailImage(bufferedImage);
		
		ImageLibraryGateway imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", "uploads-test/thumbnails");
		imageLibraryGateway.saveImage(image);
	}
}
