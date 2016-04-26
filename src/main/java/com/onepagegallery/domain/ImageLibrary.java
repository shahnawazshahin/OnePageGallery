/**
 * 
 */
package com.onepagegallery.domain;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.onepagegallery.gateway.ImageLibraryGateway;

/**
 * @author shahnawazshahin
 *
 * The Gallery class
 */
public class ImageLibrary {

	private ImageLibraryGateway imageLibraryGateway;
	
	public ImageLibrary(ImageLibraryGateway imageLibraryGateway) {
		
		this.imageLibraryGateway = imageLibraryGateway;
		this.images = new ArrayList<Image>();
	}

	private List<Image> images;

	public List<Image> getImages() {
		return images;
	}


	public void setImages(List<Image> images) {
		this.images = images;
	}

	/*
	 * Load images into the Gallery.
	 */
	public void loadImages() {
		
		this.imageLibraryGateway.loadImageLibrary(this);
	}
	
	public void saveImageToLibrary(Image image) {
		
		// Generate random number to make the file unique.
		//
		image.setOriginalFileName(System.currentTimeMillis() + image.getOriginalFileName());
		
		// Generate the thumbnail.
		//
		this.generateThumbnail(image);
		
		// Save the image to library.
		//
		this.imageLibraryGateway.saveImage(image);
	}
	
	private void generateThumbnail(Image image) {
		
		BufferedImage originalImage = image.getOriginalImage();

		// Determine the thumbnail size and create a scaled image of the original.
		//
		int widthToScale, heightToScale;
		
		if (originalImage.getWidth() > originalImage.getHeight()) {
		 
		    heightToScale = (int)(1.1 * 150);
		    widthToScale = (int)((heightToScale * 1.0) / originalImage.getHeight() 
		                    * originalImage.getWidth());
		 
		} else {
		    widthToScale = (int)(1.1 * 150);
		    heightToScale = (int)((widthToScale * 1.0) / originalImage.getWidth() 
		                    * originalImage.getHeight());
		}
		
		BufferedImage thumbnailImage = new BufferedImage(widthToScale, 
			    heightToScale, originalImage.getType());
		
		Graphics2D g = thumbnailImage.createGraphics();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 
		g.drawImage(originalImage, 0, 0, widthToScale, heightToScale, null);
		g.dispose();

		image.setThumbnailFileName("tmb_" + image.getOriginalFileName());
		image.setThumbnailImage(thumbnailImage);
	}
}
