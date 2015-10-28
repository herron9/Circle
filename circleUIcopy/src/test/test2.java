package test;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class test2 {

	private JFrame frame;
	private JTextField textField;

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
