package test;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.UIManager;


public class ChattingCellS extends JPanel {
	

	public ImageIcon User = new ImageIcon("src/avatar.png");
	public JLabel UserIcon;
	public JLabel NameLabel = new JLabel("New label");
	public JLabel TimeLabel = new JLabel("New label");
	public JLabel Image= new JLabel();
	public JPanel ShowArea;
	public JTextArea msg = new JTextArea();

	public JLabel PicLab;
	
	JPanel Right = new JPanel();
	
	String name = "null";
	
	public ChattingCellS() {
		setBackground(UIManager.getColor("CheckBox.background"));
		setPreferredSize(new Dimension(570,60));
		
			
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{40, 20,320,40, 60, 60, 0, 0, 0};//{40, 320, 120, 60}
		gridBagLayout.rowHeights = new int[]{20, 50};
		setLayout(gridBagLayout);

		
			ShowArea = new CCShowArea(CCShowArea.Left);
			//ShowArea.setMaximumSize(new Dimension(400, 0));
			GridBagConstraints gbc_ShowArea = new GridBagConstraints();
			gbc_ShowArea.insets = new Insets(0, 5, 0, 5);
			gbc_ShowArea.gridwidth = 2;
			gbc_ShowArea.anchor = GridBagConstraints.NORTHWEST;
			gbc_ShowArea.gridx = 1;
			gbc_ShowArea.gridy = 1;
			add(ShowArea, gbc_ShowArea);
	        msg.setRows(1);
	        msg.setWrapStyleWord(true);
	        msg.setEditable(false);
	        msg.setOpaque(false);
	        //msg.setHorizontalAlignment(SwingConstants.LEFT);
	        msg.setForeground(Color.BLACK);
			//msg.setSize(new Dimension(100, 15));
	        msg.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	        ShowArea.add(msg);
			ShowArea.setBackground(new Color(240, 255, 255));
			
			NameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			NameLabel.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_NameLabel = new GridBagConstraints();
			gbc_NameLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_NameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_NameLabel.anchor = GridBagConstraints.SOUTH;
			gbc_NameLabel.gridwidth = 2;
			gbc_NameLabel.gridx = 0;
			gbc_NameLabel.gridy = 0;
			add(NameLabel, gbc_NameLabel);
			TimeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			
			TimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_TimeLabel = new GridBagConstraints();
			gbc_TimeLabel.insets = new Insets(0, 0, 5, 5);
			//gbc_TimeLabel.ipady = 15;
			gbc_TimeLabel.anchor = GridBagConstraints.SOUTHWEST;
			gbc_TimeLabel.gridx = 2;
			gbc_TimeLabel.gridy = 0;
			add(TimeLabel, gbc_TimeLabel);
			
			
			UserIcon = new JLabel(ClientFunction.resizeIcon(User,40,40));
			UserIcon.setVerticalAlignment(SwingConstants.TOP);
			GridBagConstraints gbc_UserIcon = new GridBagConstraints();
			gbc_UserIcon.fill = GridBagConstraints.HORIZONTAL;
			gbc_UserIcon.insets = new Insets(0, 0, 0, 5);
			gbc_UserIcon.anchor = GridBagConstraints.NORTH;
			gbc_UserIcon.gridy = 1;
			gbc_UserIcon.gridx = 0;
			add(UserIcon, gbc_UserIcon);

	}
	
	public void loadpic(ImageIcon picMsg) {
		JLabel picLabel = new JLabel(picMsg);
		ShowArea.add(picLabel);
	}

	
	public void PicMsg(ImageIcon picMsg) { 
		PicLab= new JLabel(picMsg);
		ShowArea.add(PicLab);
		ShowArea.setVisible(true);
	}
	

}
