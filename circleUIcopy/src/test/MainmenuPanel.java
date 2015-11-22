package test;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class MainmenuPanel extends JPanel {
	
	JLabel MenuLabChat = new JLabel("Chats");
	JLabel MenuLabFriend = new JLabel("Friends");
	JLabel MenuLabMom = new JLabel("Moments");
	JLabel MenuLabMe = new JLabel("Me");
	
	

	public MainmenuPanel() {
		setBackground(new Color(220, 220, 220));
		setBounds(0,0,600,50);
		setLayout(new GridLayout(0, 4, 0, 0));
		MenuLabChat.setBackground(new Color(220, 220, 220));
		
		MenuLabChat.setHorizontalAlignment(SwingConstants.CENTER);
		//MenuLabChat.setBorder(new LineBorder(new Color(64, 64, 64)));
		MenuLabChat.setOpaque(true);
		MenuLabChat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabChat);
		MenuLabFriend.setBackground(new Color(220, 220, 220));
		
		MenuLabFriend.setOpaque(true);
		MenuLabFriend.setHorizontalAlignment(SwingConstants.CENTER);
//		MenuLabFriend.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		MenuLabFriend.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabFriend);
		MenuLabMom.setBackground(new Color(220, 220, 220));
		
		MenuLabMom.setOpaque(true);
		MenuLabMom.setHorizontalAlignment(SwingConstants.CENTER);
//		MenuLabMon.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		MenuLabMom.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabMom);
		MenuLabMe.setBackground(new Color(220, 220, 220));
		
		MenuLabMe.setOpaque(true);
		MenuLabMe.setHorizontalAlignment(SwingConstants.CENTER);
//		MenuLabMe.setBorder(BorderFactory.createLineBorder(Color.darkGray));
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
            	MainFrame.mainFrame.setTitle("Circle - Friends");
            	LoginPanel.operation="friendRequestList-request?";
       		  	LoginFunction.CheckFriendRequest(LoginPanel.operation, LoginFunction.AccessToken);
       		  	LoginPanel.operation="friendList-request?";
       		  	LoginFunction.GetFriendList(LoginPanel.operation, LoginFunction.AccessToken);
       		  	LoginPanel.operation="get-friend-result-list?";
            	LoginFunction.GetFriendResultList(LoginPanel.operation, LoginFunction.AccessToken);
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "FriendList");
            	MainFrame.mainFrame.setTitle("Circle - FriendList");
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
		MenuLabMom.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	MainFrame.mainFrame.setTitle("Circle - Moments");
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "MPane");
            	LoginPanel.operation="moments-record-request?";
            	LoginFunction.GetMoments(LoginPanel.operation, LoginFunction.AccessToken);
            	MainLayout.MomPanel.DisplayMoments();
             }
             public void  mouseExited(MouseEvent e) {
            	 MenuLabMom.setBackground(null);
             }
             public void  mouseEntered(MouseEvent e) {
            	 MenuLabMom.setBackground(Color.LIGHT_GRAY);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
		MenuLabMe.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
    			MainFrame.mainFrame.setTitle("Circle - Profile");
      		  if (LoginFunction.Iconurl==null) {
    			  //MainLayout.panelPro.setInfo(Nickname,Gender,Phonenumber,Iconurl);
      			MainLayout.panelPro.setInfo(LoginFunction.Gender, LoginFunction.Phonenumber,"src/dio.jpg");
      		  }
    		  else {
    			MainLayout.panelPro.setInfo(LoginFunction.Gender, LoginFunction.Phonenumber,LoginFunction.Iconurl);
    			System.out.println("LoginFunction.Iconurl: "+LoginFunction.Iconurl);
    		  }
            	
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
