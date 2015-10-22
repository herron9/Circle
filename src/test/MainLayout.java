package test;


import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;



public class MainLayout extends JPanel {
	
	public static JPanel MainUppage = new JPanel ();// will be cardlayout
	MainmenuPanel Mainmenu = new MainmenuPanel();
	
	public static CardLayout MainpageCl = new CardLayout();
	public static MainChatPanel MCPanel = new MainChatPanel();
	public static ChattingPanel CPanel = new ChattingPanel();
	public static FriendPanel FriendList = new FriendPanel(LoginPanel.names);
	//public static FriendPanel FPanel = new FriendPanel();
	//public static ChattingPanel Chatting = new ChattingPanel();
	
	
	public MainLayout() {
		setBackground(new Color(240, 255, 240));
		//setBounds(100,100,600,450);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//the overall look will be box
		MainUppage.setAlignmentY(0.0f);
		MainUppage.setLayout(MainpageCl);
		MainUppage.setPreferredSize(new Dimension(600,400));
		Mainmenu.setPreferredSize(new Dimension(600, 50));
		//setPreferredSize(new Dimension(200, 200));
		MainUppage.add(MCPanel,"MCPanel");
		MainLayout.MainUppage.add(FriendList,"FriendList");
		MainLayout.MainUppage.add(CPanel, "CPanel");
		//MainUppage.add(FPanel,"FPanel");
		add(MainUppage);
		Mainmenu.setAlignmentY(0.0f);
		add(Mainmenu);
		
		
		
		
	}

}
