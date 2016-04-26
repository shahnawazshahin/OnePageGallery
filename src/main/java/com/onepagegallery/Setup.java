/**
 * 
 */
package com.onepagegallery;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author shahnawazshahin
 *
 * A one-off call for setting up the application environment.
 * 
 */
@Component
public class Setup implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {

		File rootFolder = new File(ApplicationConfig.UPLOADS_FILESYSTEM);
		
		if (!rootFolder.exists()) {
			
			rootFolder.mkdirs();
		}

		File thumbnailFolder = new File(ApplicationConfig.THUMBNAILS_FILESYSTEM);
		
		if (!thumbnailFolder.exists()) {
			
			thumbnailFolder.mkdirs();
		}		
	}
}
