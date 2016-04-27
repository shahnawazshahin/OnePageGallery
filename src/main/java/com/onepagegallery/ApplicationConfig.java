/**
 * 
 */
package com.onepagegallery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.onepagegallery.controller.GalleryController;
import com.onepagegallery.gateway.ImageLibraryGateway;
import com.onepagegallery.gateway.filesystem.FilesystemImageLibraryGateway;
import com.onepagegallery.service.GalleryService;
import com.onepagegallery.service.impl.ImplGalleryService;

/**
 * @author shahnawazshahin
 *
 */
@Configuration
public class ApplicationConfig {

	public static final String UPLOADS_FILESYSTEM = "uploads";
	public static final String THUMBNAILS_FILESYSTEM = "uploads/thumbnails";

	@Bean
	public ImageLibraryGateway imageLibraryGateway() {
		
		return new FilesystemImageLibraryGateway(UPLOADS_FILESYSTEM, THUMBNAILS_FILESYSTEM);
	}
	
	@Bean
	public GalleryService galleryService() {
		
		return new ImplGalleryService(this.imageLibraryGateway());
	}
	
	@Bean
	public GalleryController galleryController() {
		
		return new GalleryController(this.galleryService());
	}
}
