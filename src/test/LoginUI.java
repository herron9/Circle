package test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class LoginUI extends JPanel {
	private JLabel title = new JLabel("Circle Login");// login reg page
	private ImageIcon RegLogo = new ImageIcon("bin/Circle_LOGOss.png");//cant scale
	private JLabel RegLogo1 = new JLabel(RegLogo);
	public ImageIcon User = new ImageIcon("bin/avatar.png");//cant scale!
	public JLabel UserIcon = new JLabel(User);
	
	public JTextField Login_username = new JTextField();
	public JLabel lblRegister = new JLabel("Register");
	public JPasswordField Login_password = new JPasswordField();
	public JButton LogButton = new JButton("Log in");//switch to main
	
	/*	public static ImageIcon getImageIcon(String path, int width, int height) {
	  ImageIcon icon = new ImageIcon();
	  icon.getClass().getResource(path);
	  icon = new ImageIcon(icon.getImage()  
            .getScaledInstance(width, height, Image.SCALE_DEFAULT));  
	  icon.setImage().getImage().getScaledInstance(width, height,
	    Image.SCALE_DEFAULT);
	  return icon;
	 }*/
	
	public LoginUI() {
		setBackground(SystemColor.window);
		setLayout(null);
		
		add(title);
		add(RegLogo1);
		add(UserIcon);		
		add(Login_username);
		add(Login_password);
		add(lblRegister);
		add(LogButton);
				
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Arial",Font.PLAIN, 40));
		title.setBounds(230, 75, 220, 50);
		RegLogo1.setBounds(140, 60, 80, 80);
		UserIcon.setBounds(130, 176, 100, 100);// need PS
		LogButton.setBounds(240, 300, 120, 40);
		
		Login_username.setText("username");
		Login_username.setToolTipText("username");
		Login_username.setBounds(240, 180, 220, 35);
		Login_username.setColumns(10);
		
		lblRegister.setForeground(Color.BLUE);
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblRegister.setBounds(470, 180, 80, 35);

		Login_password.setToolTipText("password");
		Login_password.setEchoChar((char) (0));
		Login_password.setText("password");
		Login_password.setBounds(240, 240,220, 35);
	}

}
