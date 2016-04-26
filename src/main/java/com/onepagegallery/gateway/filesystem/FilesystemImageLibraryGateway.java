/**
 * 
 */
package com.onepagegallery.gateway.filesystem;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.onepagegallery.domain.Image;
import com.onepagegallery.domain.ImageLibrary;
import com.onepagegallery.gateway.ImageLibraryGateway;

/**
 * @author shahnawazshahin
 *
 * An implementation of the GalleryGateway based on storing and retrieving data from
 * the filesystem.
 */
public class FilesystemImageLibraryGateway implements ImageLibraryGateway {

	private String rootFilesystem;
	private String thumbnailFilesystem;

	public FilesystemImageLibraryGateway(String rootFilesystem, String thumbnailFilesystem) {

		this.rootFilesystem = rootFilesystem;
		this.thumbnailFilesystem = thumbnailFilesystem;
	}

	/* (non-Javadoc)
	 * @see com.onepagegallery.gateway.GalleryGateway#loadImageLibrary(com.onepagegallery.domain.ImageLibrary)
	 */
	@Override
	public void loadImageLibrary(ImageLibrary imageLibrary) {

		// Check that the root folder exists on the filesystem. If not, throw an exception.
		//
		File rootFolder = new File(this.rootFilesystem);
		
		if (!rootFolder.exists()) {
			
			throw new FolderDoesNotExistException("The folder " + this.rootFilesystem + " does not exist on the filesystem. Has the folder been set up or initialised?");
		}
		
		
		// Check that the thumbnail folder exists on the filesystem. If not, throw an exception.
		//
		File thumbnailFolder = new File(this.thumbnailFilesystem);
		
		if (!thumbnailFolder.exists()) {
			
			throw new FolderDoesNotExistException("The folder " + this.thumbnailFilesystem + " does not exist on the filesystem. Has the folder been set up or initialised?");
		}

		// Build the list of images found in the library.
		//
		List<File> thumbnailFileNames = Arrays.asList(thumbnailFolder.listFiles());

		Image image = null;

		for (File thumbnailFileName : thumbnailFileNames) {
			
			image = new Image();
			image.setThumbnailFileName(thumbnailFileName.getName());
			image.setThumbnailPath(thumbnailFileName.getPath());
			
			imageLibrary.getImages().add(image);
		}
	}

	/* (non-Javadoc)
	 * @see com.onepagegallery.gateway.GalleryGateway#saveImage(com.onepagegallery.domain.Image)
	 */
	@Override
	public void saveImage(Image image) {

		// Save the image and thumbnail.
		//
		try {
			ImageIO.write(image.getOriginalImage(), "JPG", new File(this.rootFilesystem + "/" + image.getOriginalFileName()));
			ImageIO.write(image.getThumbnailImage(), "JPG", new File(this.thumbnailFilesystem + "/" + image.getThumbnailFileName()));
		} catch (Exception ex) {

			throw new CannotWriteToFileException("Cannot write to file. " + ex.getMessage());
		}
	}
}
