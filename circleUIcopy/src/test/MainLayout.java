package test;


import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;



public class MainLayout extends JPanel {
	
	public static JPanel MainUppage = new JPanel ();// will be cardlayout
	MainmenuPanel Mainmenu = new MainmenuPanel();
	
	public static CardLayout MainpageCl = new CardLayout();
	public static MainChatPanel MCPanel = new MainChatPanel();
	public static FriendPanel FriendList = new FriendPanel(LoginPanel.names);
	public static ProfilePanel panelPro = new ProfilePanel();
	
	
	public MainLayout() {
		setBackground(new Color(240, 255, 240));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//the overall look will be box
		MainUppage.setAlignmentY(0.0f);
		MainUppage.setLayout(MainpageCl);
		MainUppage.setPreferredSize(new Dimension(600,400));
		Mainmenu.setPreferredSize(new Dimension(600, 50));
		
		panelPro.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		MainUppage.add(MCPanel,"MCPanel");
		MainUppage.add(FriendList,"FriendList");
		MainUppage.add(panelPro, "ProPanel");
	
		add(MainUppage);
		Mainmenu.setAlignmentY(0.0f);
		add(Mainmenu);
		
		
		
		
	}

}
