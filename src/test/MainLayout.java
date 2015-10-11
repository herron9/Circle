package test;


import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;



public class MainLayout extends JPanel {
	
	public static JPanel MainUppage = new JPanel ();
	MainmenuPanel Mainmenu = new MainmenuPanel();
	
	public static CardLayout MainpageCl = new CardLayout();
	public static MainChatPanel MCPanel = new MainChatPanel();
	public static ChattingPanel Chatting = new ChattingPanel();
	public static FriendPanel FriendList = new FriendPanel();
	
	
	public MainLayout() {
		setBackground(new Color(240, 255, 240));
		setBounds(100,100,600,450);
		MainUppage.setBounds(0, 0, 600, 400);
		MainUppage.setLayout(MainpageCl);
		MainUppage.add(MCPanel,"MCPanel");
		MainUppage.add(Chatting,"Chatting");
		MainUppage.add(FriendList,"FriendList");

		Mainmenu.setBounds(0, 400, 600, 50);
		setLayout(null);
		add(MainUppage);
		add(Mainmenu);
		
		
		
	}

}
