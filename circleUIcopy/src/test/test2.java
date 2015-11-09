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
		System.out.println(fileurl);
		BufferedImage bufferedImage = null;
		try {
			URL myURL = new URL(fileurl);
			bufferedImage = ImageIO.read(myURL);
		} catch (IOException e) {
		}
		
		ImageIcon image=new ImageIcon(bufferedImage);
		Image img = image.getImage();
		BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 50, 50, null);
		ImageIcon newIcon = new ImageIcon(bi);
		System.out.println(newIcon.getIconWidth());
		System.out.println(newIcon.getIconHeight());


		RegLogo1 = new JLabel(newIcon);
		RegLogo1.setBounds(0, 0, 50, 50);
		frame.getContentPane().add(RegLogo1);

		
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
