package com.amigoscode.awsimageupload.buckets;

import lombok.Getter;

@Getter
public enum BucketName {
	PROFILE_IMAGE( "amigoscode-image-upload-tutorial" );

	private final String bucketName;

	BucketName(String bucketName) {
		this.bucketName = bucketName;
	}
}
