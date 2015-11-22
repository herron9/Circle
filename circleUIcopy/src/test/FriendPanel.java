package test;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;

public class FriendPanel extends JPanel {
	
	public static JList list;	
	public static JScrollPane Scroller=new JScrollPane(list);
	JPanel South=new JPanel();
	public static String friendname;
	ImageIcon AddIcon = new ImageIcon("src/addfriend.png");
	JLabel AddFriend = new JLabel("Add Friend",ClientFunction.resizeIcon(AddIcon,25,25), 0);
	JLabel FriendRequest = new JLabel(ClientFunction.resizeIcon(new ImageIcon("src/newrequest.png"),33,25));
	
	public FriendPanel(String names[]) {
		
		setBackground(UIManager.getColor("CheckBox.background"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{30, 281, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		AddFriend.setHorizontalAlignment(SwingConstants.CENTER);
		AddFriend.setPreferredSize(new Dimension(600, 30));
		GridBagConstraints gbc_AddFriend = new GridBagConstraints();
		gbc_AddFriend.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddFriend.anchor = GridBagConstraints.WEST;
		gbc_AddFriend.insets = new Insets(0, 0, 0, 0);
		gbc_AddFriend.weightx = 0.1;
		gbc_AddFriend.gridy = 0;
		gbc_AddFriend.gridx = 0;
		add(AddFriend);
		AddFriend.setOpaque(true);
		AddFriend.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
        		String inputValue = JOptionPane.showInputDialog("Enter the friend you want to add");
        		if(inputValue!=null){
                	LoginPanel.operation="friend-request?";
                	LoginFunction.AddAFriend(LoginPanel.operation,LoginFunction.AccessToken,inputValue);
        		}
            	LoginPanel.operation="friendRequestList-request?";
       		  	LoginFunction.CheckFriendRequest(LoginPanel.operation, LoginFunction.AccessToken);
       		 	LoginPanel.operation="friendList-request?";
            	LoginFunction.GetFriendList(LoginPanel.operation, LoginFunction.AccessToken);
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "FriendList");
             }
             public void  mouseExited(MouseEvent e) {
            	 AddFriend.setBackground(null);
             }
             public void  mouseEntered(MouseEvent e) {
            	 AddFriend.setBackground(Color.LIGHT_GRAY);
             }
             public void  mouseReleased(MouseEvent e) {            	 
             }
             public void  mousePressed(MouseEvent e) { 
             }
         });
		
		list = new JList(names);
		
		//Scroller.setPreferredSize(new Dimension(600, 400));
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		list.setBorder(BorderFactory.createTitledBorder("Friend Lists"));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Scroller.setViewportView(list);
		GridBagConstraints gbc_Scroller = new GridBagConstraints();
		gbc_Scroller.gridwidth = 2;
		gbc_Scroller.fill = GridBagConstraints.BOTH;
		gbc_Scroller.gridx = 0;
		gbc_Scroller.gridy = 1;
		add(Scroller, gbc_Scroller);
		
		list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
            	friendname=(String) list.getSelectedValue();
            	if(friendname.equals(LoginPanel.circleAccessToken)){
            		JOptionPane.showMessageDialog(null,"you can not chat with yourself", null, JOptionPane.INFORMATION_MESSAGE);
            	}
            	else{
//	            	JOptionPane.showMessageDialog(null, "chat with "+ friendname, null, JOptionPane.INFORMATION_MESSAGE);
	            	boolean find=false;
	            	if (LoginFunction.receiver.isEmpty()) {
	            		ClientFunction.CreateChatting(friendname);
	                	ChatList.CreateEntry(friendname);
	                	//return;
					}
	            	else{
	            		for(int i=0;i<LoginFunction.receiver.size();i++){
		            		if(friendname.equals(LoginFunction.receiver.get(i).friendname)){
		            			find=true;
		            		}
		            		if(find==true){
		                		ClientFunction.RecallChatting(friendname);
		                	}
		                	else{
		                    	ClientFunction.CreateChatting(friendname);
		                    	ChatList.CreateEntry(friendname);
		                	}
		            	}
	            	}
	            	
            	
            	}
            }
		});
		
		
		FriendRequest.setOpaque(true);
		FriendRequest.setPreferredSize(new Dimension(35, 30));
		GridBagConstraints gbc_FriendRequest = new GridBagConstraints();
		gbc_FriendRequest.insets = new Insets(0, 0, 0, 0);
		gbc_FriendRequest.weightx = 0.1;
		gbc_FriendRequest.gridy = 0;
		gbc_FriendRequest.gridx = 1;
		
		if(LoginFunction.friendrequest==true){
			AddFriend.setPreferredSize(new Dimension(565, 30));
			add(FriendRequest, gbc_FriendRequest);
		}
		else{
			AddFriend.setPreferredSize(new Dimension(600, 30));
			remove(FriendRequest);
		}

		
		FriendRequest.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	 LoginPanel.operation="friendRequestList-request?";
       		  	 LoginFunction.GetFriendRequest(LoginPanel.operation, LoginFunction.AccessToken);
             }
             public void  mouseExited(MouseEvent e) {
            	FriendRequest.setBackground(null);
             }
             public void  mouseEntered(MouseEvent e) {
            	FriendRequest.setBackground(Color.LIGHT_GRAY);
             }
             public void  mouseReleased(MouseEvent e) {            	 
             }
             public void  mousePressed(MouseEvent e) { 
             }
         });
	}
}

