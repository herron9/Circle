package test;

import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.UIManager;


public class MainmenuPanel extends JPanel {
	
	JLabel MenuLabChat = new JLabel("Chats");
	JLabel MenuLabFriend = new JLabel("Friends");
	JLabel MenuLabMon = new JLabel("Moments");
	JLabel MenuLabMe = new JLabel("Me");
	
	

	public MainmenuPanel() {
		setBackground(UIManager.getColor("CheckBox.background"));
		setBounds(0,0,600,50);
		setLayout(new GridLayout(0, 4, 0, 0));
		
		MenuLabChat.setHorizontalAlignment(SwingConstants.CENTER);
		MenuLabChat.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		MenuLabChat.setOpaque(true);
		MenuLabChat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabChat);
		
		MenuLabFriend.setOpaque(true);
		MenuLabFriend.setHorizontalAlignment(SwingConstants.CENTER);
		MenuLabFriend.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		MenuLabFriend.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabFriend);
		
		MenuLabMon.setOpaque(true);
		MenuLabMon.setHorizontalAlignment(SwingConstants.CENTER);
		MenuLabMon.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		MenuLabMon.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabMon);
		
		MenuLabMe.setOpaque(true);
		MenuLabMe.setHorizontalAlignment(SwingConstants.CENTER);
		MenuLabMe.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		MenuLabMe.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabMe);
		
		MenuLabChat.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	MainFrame.mainFrame.setTitle("Circle - Chat");
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "MCPanel");
             }
             public void  mouseExited(MouseEvent e) {
            	 MenuLabChat.setBackground(null);
             }
             public void  mouseEntered(MouseEvent e) {
            	 MenuLabChat.setBackground(Color.LIGHT_GRAY);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
		MenuLabFriend.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	MainFrame.mainFrame.setTitle("Circle - FriendList");
//            	LoginFunction.GetFriendList("friendList-request?", LoginFunction.AccessToken);
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "FriendList");
//            	MainLayout.MainUppage.updateUI();
             }
             public void  mouseExited(MouseEvent e) {
            	 MenuLabFriend.setBackground(null);
             }
             public void  mouseEntered(MouseEvent e) {
            	 MenuLabFriend.setBackground(Color.LIGHT_GRAY);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
		MenuLabMon.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "MCPanel");
             }
             public void  mouseExited(MouseEvent e) {
            	 MenuLabMon.setBackground(null);
             }
             public void  mouseEntered(MouseEvent e) {
            	 MenuLabMon.setBackground(Color.LIGHT_GRAY);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
		MenuLabMe.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "ProPanel");
             }
             public void  mouseExited(MouseEvent e) {
            	 MenuLabMe.setBackground(null);
             }
             public void  mouseEntered(MouseEvent e) {
            	 MenuLabMe.setBackground(Color.LIGHT_GRAY);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
		

	}

}
