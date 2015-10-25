package test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame{

	public static JFrame mainFrame = new JFrame("Circle");
	public static CardLayout cl= new CardLayout();
	public static JPanel panelCont = new JPanel();
	public static LoginPanel panelLog = new LoginPanel();
	public static RegisterPanel panelReg = new RegisterPanel();
	

	
	MainLayout panelMain = new MainLayout();

	public MainFrame() {
		panelCont.setLayout(cl);
		panelCont.add("Log",panelLog);
		panelCont.add("Reg",panelReg);
		panelCont.add("Main",panelMain);
		//panelCont.add("main",panelMain)
		//cl.show(panelCont, "Main");
		cl.show(panelCont, "Log");
		mainFrame.setSize(600, 450);
		mainFrame.getContentPane().add(BorderLayout.CENTER,panelCont);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

