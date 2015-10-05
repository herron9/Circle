package test;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class MainmenuPanel extends JPanel {
	
	JLabel MenuLabChat = new JLabel("Chats");
	JLabel MenuLabFriend = new JLabel("Friends");
	JLabel MenuLabMon = new JLabel("Moments");
	JLabel MenuLabMe = new JLabel("Me");

	public MainmenuPanel() {
		setBackground(new Color(51, 204, 255));
		setBounds(100,100,600,50);
		setLayout(new GridLayout(0, 4, 0, 0));
		MenuLabChat.setHorizontalAlignment(SwingConstants.CENTER);
		
		MenuLabChat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabChat);
		MenuLabFriend.setHorizontalAlignment(SwingConstants.CENTER);

		MenuLabFriend.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabFriend);
		MenuLabMon.setHorizontalAlignment(SwingConstants.CENTER);

		MenuLabMon.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabMon);
		MenuLabMe.setHorizontalAlignment(SwingConstants.CENTER);

		MenuLabMe.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		add(MenuLabMe);
	}

}
