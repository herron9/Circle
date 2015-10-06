package test;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CLayout {

	public JFrame Circle = new JFrame("Circle");
	public static CardLayout cl= new CardLayout();
	public static JPanel panelCont = new JPanel();
	public static LoginPanel panelLog = new LoginPanel();
	public static RegisterPanel panelReg = new RegisterPanel();
	
	MainLayout panelMain = new MainLayout();

	public CLayout() {
		panelCont.setLayout(cl);
		panelCont.add("Log",panelLog);
		panelCont.add("Reg",panelReg);
		panelCont.add("Main",panelMain);

		cl.show(panelCont, "Log");
		
		Circle.getContentPane().add(panelCont);
		Circle.setBounds(100, 100, 600, 450);
		Circle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Circle.setResizable(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CLayout window = new CLayout();
					window.Circle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

