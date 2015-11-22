package test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;


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
		mainFrame.setResizable(false); 
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				try {
//				    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//				} catch (Exception e) {
//				    e.printStackTrace();
//				}
				try {
					//UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
					MainFrame window = new MainFrame();
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					window.mainFrame.setVisible(true);
					
					//window.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

