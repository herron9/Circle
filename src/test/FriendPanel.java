package test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import scala.collection.script.Start;

import javax.swing.ListSelectionModel;

import java.awt.Dimension;

public class FriendPanel extends JPanel {
	
	public static String names[]={"duras.yhr@gmail.com","yanghrong@outlook.com","Jack","Lee","Wang"};
	public static JList list = new JList(names);	
	public static JScrollPane Scroller=new JScrollPane(list);
	JPanel South=new JPanel();
	public static String friendname;
	
	public FriendPanel() {
		
		setBackground(UIManager.getColor("CheckBox.background"));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		Scroller.setPreferredSize(new Dimension(600, 400));
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		list.setBorder(BorderFactory.createTitledBorder("Friend Lists"));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Scroller.setViewportView(list);
		add(Scroller);
		
		list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
            	friendname=(String) list.getSelectedValue();
            	//JOptionPane.showMessageDialog(null, "chat with" +friendname, null, JOptionPane.INFORMATION_MESSAGE);
            	//String UserAT = LoginPanel.circleAccessToken;
				//JOptionPane.showMessageDialog(null,LoginPanel.circleAccessToken);
//				ChattingPanel CPanel = new ChattingPanel();
//				ClientFunction.CreateClient(UserAT,CPanel,friendname);	
//				MainLayout.MainUppage.add(CPanel,"CPanel");
//				MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
            	ClientFunction.CreateChatting(friendname);
            	//MainLayout.MainpageCl.show(MainLayout.MainUppage, "Chatting");
            	}
		});
		
		

//		JButton btnNewButton = new JButton("Start");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String UserAT = LoginPanel.circleAccessToken;
//				//JOptionPane.showMessageDialog(null,LoginPanel.circleAccessToken);
//				ChattingPanel CPanel = new ChattingPanel();
//				ClientFunction.CreateClient(UserAT,CPanel);	
//				MainLayout.MainUppage.add(CPanel,"CPanel");
//				
//				//CLayout.cl.show(CLayout.panelCont, "Reg");
//				
//			}
//		});
		
//		ScrollFriendList.setBounds(0, 0, 600, 400);
//		ScrollFriendList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		ScrollFriendList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		ScrollFriendList.add(list);
//		add(ScrollFriendList);
		
		
	}
}


