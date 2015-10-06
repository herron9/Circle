package test;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Color;



public class MainLayout extends JPanel {
	
	MainpagePanel Mainpage = new MainpagePanel();
	MainmenuPanel Mainmenu = new MainmenuPanel();
	
	public MainLayout() {
		setBackground(new Color(240, 255, 240));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBounds(100,100,600,450);
		add(Mainpage);
		add(Mainmenu);
		
		
		
	}

}
