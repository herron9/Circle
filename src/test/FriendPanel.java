package test;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Dimension;

public class FriendPanel extends JPanel {
	
	public static String names[]={"Alex","Bob","Jack","Lee","Wang"};
	public static JList list = new JList(names);	
	public static JScrollPane Scroller=new JScrollPane(list);
	JPanel South=new JPanel();
	public static String friendname;
	
	public FriendPanel() {
		
		setBackground(UIManager.getColor("CheckBox.background"));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		Scroller.setPreferredSize(new Dimension(600, 400));
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		list.setBorder(BorderFactory.createTitledBorder("Friend Lists"));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Scroller.setViewportView(list);
		add(Scroller);
		
		list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
            	friendname=(String) list.getSelectedValue();
            	JOptionPane.showMessageDialog(null, "选择与" +friendname+"聊天", null, JOptionPane.INFORMATION_MESSAGE);
            	MainLayout.MainpageCl.show(MainLayout.MainUppage, "Chatting");
            	}
		});
		
//		ScrollFriendList.setBounds(0, 0, 600, 400);
//		ScrollFriendList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		ScrollFriendList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		ScrollFriendList.add(list);
//		add(ScrollFriendList);
		
		
	}
}


