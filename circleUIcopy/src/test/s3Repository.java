package test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
public class s3Repository {
	AmazonS3 s3Client;
	String bucketName;
	s3Repository() {
		   AWSCredentials credentials = null;
	        try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " ,
	                    e);
	        }
	        this.s3Client = new AmazonS3Client(credentials);
	        Region usEast1 = Region.getRegion(Regions.US_EAST_1);
	        this.s3Client.setRegion(usEast1);
	        this.bucketName = "circleuserfiles";
	}
	
	public boolean uploadFile (String key,String filePath) {
		try {
			this.s3Client.putObject(new PutObjectRequest(bucketName, key, createFileByFilePath(filePath)));
			return true;
		} catch (IOException e) {
            return false;
		}
	
	}
	
	private  File createFileByFilePath(String filePath) throws IOException {
	        File file = new File(filePath);
	        return file;
	}
	
	public static void main(String[] args) {
		s3Repository s3= new s3Repository();
		String key = ""+UUID.randomUUID()+".jpg";
		String filePath =SwingFileChooserDemo.chooseAFileFromCurrentMachine();
		s3.uploadFile(key, filePath);
        
	}
}
