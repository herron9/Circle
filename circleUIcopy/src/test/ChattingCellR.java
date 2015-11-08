package test;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;


import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.BevelBorder;


public class ChattingCellR extends JPanel {

	public ImageIcon User = new ImageIcon("src/avatar.png");//cant scale!
	public JLabel UserIcon;
	public JLabel NameLabel = new JLabel("New label");
	public JLabel TimeLabel = new JLabel("New label");
	public JPanel ShowArea;
	public JTextArea msg = new JTextArea("hello");
	JPanel Right = new JPanel();
	
	String name = "null";
	
	public ChattingCellR() {
		//setBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, null, null, null));
		setPreferredSize(new Dimension(570, 55));
		UserIcon = new JLabel(ClientFunction.resizeIcon(User,40));	
		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
//		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{40, 340, 150, 40};
		gridBagLayout.rowHeights = new int[]{20, 50};
		setLayout(gridBagLayout);
			NameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		
			NameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_NameLabel = new GridBagConstraints();
			gbc_NameLabel.anchor = GridBagConstraints.SOUTHEAST;
			gbc_NameLabel.gridwidth = 2;
			gbc_NameLabel.gridx = 0;
			gbc_NameLabel.gridy = 0;
			add(NameLabel, gbc_NameLabel);
			TimeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			
			TimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_TimeLabel = new GridBagConstraints();
			gbc_TimeLabel.insets = new Insets(0, 5, 0, 0);
			gbc_TimeLabel.gridwidth = 2;
			gbc_TimeLabel.anchor = GridBagConstraints.SOUTHEAST;
			gbc_TimeLabel.gridx = 2;
			gbc_TimeLabel.gridy = 0;
			add(TimeLabel, gbc_TimeLabel);
			
			GridBagConstraints gbc_UserIcon = new GridBagConstraints();
			gbc_UserIcon.fill = GridBagConstraints.HORIZONTAL;
			gbc_UserIcon.anchor = GridBagConstraints.NORTH;
			gbc_UserIcon.gridx = 3;
			gbc_UserIcon.gridy = 1;
			add(UserIcon, gbc_UserIcon);
			
			ShowArea = new CCShowArea(CCShowArea.Right);
			//ShowArea.seth
			//msg.setBounds(0,0,200,40);
			GridBagConstraints gbc_ShowArea = new GridBagConstraints();
			gbc_ShowArea.ipadx = 20;
			gbc_ShowArea.anchor = GridBagConstraints.EAST;
			gbc_ShowArea.gridwidth = 2;
			gbc_ShowArea.gridx = 1;
			gbc_ShowArea.gridy = 1;
			add(ShowArea, gbc_ShowArea);
	        msg.setForeground(Color.BLACK);
	        msg.setEditable(false);
	        msg.setOpaque(false);
			
	        msg.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	        
	        ShowArea.add(msg);
			
			ShowArea.setBackground(new Color(240, 255, 240));
		}
	

}
