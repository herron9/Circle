package test;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

public class RegisterUI extends JPanel {

	private ImageIcon RegLogo = new ImageIcon("bin/Circle_LOGOss.png");//cant scale
	private JLabel RegLogo1 = new JLabel(RegLogo);
	public ImageIcon User = new ImageIcon("bin/avatar.png");//cant scale!
	public JLabel UserIcon = new JLabel(User);
	private JTextField Register_username = new JTextField();
	private JPasswordField Register_password = new JPasswordField();;
	public JButton RegButton = new JButton("Register");//switch to main
	JLabel Regtitle = new JLabel("Circle Register");
	
	
	JLabel Tip = new JLabel("(more than 6 characters)");
	
	

	public RegisterUI() {
		setBackground(SystemColor.window);
		setLayout(null);
		
		add(Regtitle);
		add(RegLogo1);
		add(UserIcon);	
		add(Register_username);
		add(Register_password);
		add(Tip);
		add(RegButton);
		Regtitle.setFont(new Font("Arial",Font.PLAIN, 40));
		Regtitle.setBounds(230, 75, 280, 50);
		RegLogo1.setBounds(140, 60, 80, 80);
		UserIcon.setBounds(130, 176, 100, 100);// need PS
		Register_username.setText("email address");
		Register_username.setBounds(240, 180, 220, 35);
		Register_username.setColumns(10);
		Register_password.setText("password");
		Register_password.setEchoChar((char) (0));
		Register_password.setBounds(240, 240,220, 35);
		Tip.setBounds(245, 252, 160, 20);
		RegButton.setBounds(240, 300, 120, 40);
		

	}

}
