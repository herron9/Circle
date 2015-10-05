package test;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;

public class MainpagePanel extends JPanel {

	MainChatPanel MCPanel = new MainChatPanel();
	
	public MainpagePanel() {
		setBackground(new Color(51, 204, 204));
		CardLayout MainpageCl = new CardLayout();
		setLayout(MainpageCl);
		setBounds(100,100,600,400);
		add(MCPanel);


	}

}
