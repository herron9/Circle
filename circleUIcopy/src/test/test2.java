package test;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class test2 {

	private JFrame frame;
	private JTextField textField;
	private JLabel RegLogo1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 window = new test2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		s3Repository s3= new s3Repository();
		String key = ""+UUID.randomUUID()+".jpg";
		String filePath =SwingFileChooserDemo.chooseAFileFromCurrentMachine();
		s3.uploadFile(key,filePath);
		String fileurl="https://s3.amazonaws.com/circleuserfiles/"+key;
		System.out.println(filePath);
		BufferedImage bufferedImage = null;
		try {
			URL myURL = new URL(filePath);
			bufferedImage = ImageIO.read(myURL);
		} catch (IOException e) {
		}
		
		ImageIcon image=new ImageIcon(filePath);
		Image img = image.getImage();
		BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 50, 50, null);
		ImageIcon newIcon = new ImageIcon(bi);
		System.out.println(fileurl);


		RegLogo1 = new JLabel(newIcon);
		RegLogo1.setBounds(0, 0, 50, 50);
		frame.getContentPane().add(RegLogo1);
//		String s1="username";
//		String s2="timeStamp";
//		String s3="textUrl";
//		String s4="photoUrl";
//		String s5="videoUrl";
//		String s6="}";
//		String username;
//		String timeStamp;
//		String textUrl;
//		String photoUrl;
//		String videoUrl;
//		int index1,index2,index3,index4,index5,index6;
//		String response = LoginFunction.excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+"moments-record-request?"+"accessToken="+"Circle-Auth-e20d04b9-7535-4453-a5be-4a2a3580b69cb841a5d6-be91-4b3d-aaf2-cd2c95ae7d93","");
//		System.out.println("GetMoments: "+response);
//		index1 = response.indexOf(s1);
//		index2 = response.indexOf(s2);
//		index3 = response.indexOf(s3);
//		index4 = response.indexOf(s4);
//		index5 = response.indexOf(s5);
//		index6 = response.indexOf(s6);
//
//		while (index1 >= 0) {
//			username=response.substring(index1+11, index2-3);
//			timeStamp=response.substring(index2+12, index3-3);
//			textUrl=response.substring(index3+10, index4-3);
//			photoUrl=response.substring(index4+11, index5-3);
//			videoUrl=response.substring(index5+11, index6-1);
//		    Moments newmoments =new Moments();
//		    newmoments.name=username;
//		    newmoments.time=timeStamp;
//		    if(textUrl!=null){
//		    	newmoments.text=textUrl;
//		    }
//		    else if(photoUrl!=null){
//		    	ImageIcon image=new ImageIcon(photoUrl);
//				Image img = image.getImage();
//				int height = image.getIconHeight()*300/image.getIconWidth();
//				BufferedImage bi = new BufferedImage(300, height, BufferedImage.TYPE_INT_ARGB);
//				Graphics g = bi.createGraphics();
//				g.drawImage(img, 0, 0, 300, height, null);
//				newmoments.image = new ImageIcon(bi);
//		    }
//		    else if(videoUrl!=null){
//		    	
//		    }
//			System.out.println(newmoments.name);
//			System.out.println(newmoments.time);
//			System.out.println(newmoments.text);
//
//		    index1 = response.indexOf(s1, index1 + 1);
//		    index2 = response.indexOf(s2, index2 + 1);
//		    index3 = response.indexOf(s3, index3 + 1);
//			index4 = response.indexOf(s4, index4 + 1);
//			index5 = response.indexOf(s5, index5 + 1);
//			index6 = response.indexOf(s6, index6 + 1);
//		}

		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("male");
		rdbtnNewRadioButton.setBounds(77, 92, 141, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemale = new JRadioButton("female");
		rdbtnFemale.setBounds(49, 128, 141, 23);
		frame.getContentPane().add(rdbtnFemale);
		
		textField = new JTextField();
		textField.setBounds(118, 167, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		ButtonGroup gb=new ButtonGroup();
		gb.add(rdbtnNewRadioButton);
		gb.add(rdbtnFemale);
	}
}
