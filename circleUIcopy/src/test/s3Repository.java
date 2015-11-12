package test;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
		s3.uploadFile(key,filePath);
		String fileurl="https://s3.amazonaws.com/circleuserfiles/"+key;
		System.out.println(fileurl);
		BufferedImage img = null;
		try {
			URL myURL = new URL(fileurl);
			img = ImageIO.read(myURL);
		} catch (IOException e) {
		}
		System.out.println(img.getWidth());
		ImageIcon image=new ImageIcon(img);
		System.out.println(image);
		JLabel RegLogo1 = new JLabel(image);
	}
}
