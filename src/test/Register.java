package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import test.Login.FocusHandler;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Register {

	private JFrame frame;
	private JTextField Register_username;
	private JPasswordField Register_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Register_username = new JTextField();
		Register_username.setText("email");
		Register_username.setBounds(214, 136, 150, 30);
		frame.getContentPane().add(Register_username);
		Register_username.setColumns(10);
		
		JLabel label = new JLabel("Circle Login");
		label.setFont(new Font("Arial", Font.PLAIN, 40));
		label.setBounds(227, 74, 210, 50);
		frame.getContentPane().add(label);
		
		Register_password = new JPasswordField();
		Register_password.setBounds(214, 195, 150, 30);
		
		
		frame.getContentPane().add(Register_password);
		
		JLabel lblmoreThan = new JLabel("(more than 6 characters)");
		lblmoreThan.setBounds(214, 225, 160, 20);
		frame.getContentPane().add(lblmoreThan);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(230, 270, 117, 29);
		frame.getContentPane().add(btnRegister);
		
		ImageIcon RegLogo = new ImageIcon("src/Circle_LOGOs.png");
		//RegLogo.setImage(RegLogo.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
		JLabel RegLogo1 = new JLabel(RegLogo);
		//RegLogo.setIcon(new ImageIcon("src/Circle_LOGOs.png"));
		RegLogo1.setBounds(100, 50, 100, 100);
		frame.getContentPane().add(RegLogo1);
	}
}
