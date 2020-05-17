package com.amigoscode.awsimageupload.filestore;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class FileStore {

	private final AmazonS3 s3;

	public FileStore(AmazonS3 s3) {
		this.s3 = s3;
	}

	public void save(String path, String fileName, Map<String, String> mapMetaData, InputStream inputStream) {
		ObjectMetadata metadata = new ObjectMetadata();
		Optional.of( mapMetaData )
				.ifPresent( map -> {
					if (!map.isEmpty()) {
						map.forEach( metadata::addUserMetadata );
					}
				} );

		try {
			s3.putObject( path, fileName, inputStream, metadata );
		} catch (AmazonServiceException e) {
			throw new IllegalStateException( "Failed to store content to store file to s3", e );
		}
	}
}
