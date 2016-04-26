/**
 * 
 */
package com.onepagegallery.domain;

import java.awt.image.BufferedImage;

/**
 * @author shahnawazshahin
 *
 */
public class Image {

	private String originalFileName;
	private String thumbnailFileName;
	private String thumbnailPath;
	private String imageType;
	private BufferedImage originalImage;
	private BufferedImage thumbnailImage;
	
	public String getOriginalFileName() {
		return originalFileName;
	}
	
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	
	public String getThumbnailFileName() {
		return thumbnailFileName;
	}
	
	public void setThumbnailFileName(String thumbnailFileName) {
		this.thumbnailFileName = thumbnailFileName;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}

	public BufferedImage getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(BufferedImage thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
}
