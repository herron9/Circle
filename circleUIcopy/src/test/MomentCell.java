package test;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import scala.collection.parallel.ParIterableLike.Max;

import java.awt.Insets;
import java.awt.Color;

public class MomentCell extends JPanel {
	public ImageIcon User = new ImageIcon("src/avatar.png");
	public JLabel lblIcon;// = new JLabel(ClientFunction.resizeIcon(User,40));
	public JLabel lblName = new JLabel("name");
	public MomentCellContent content = new MomentCellContent();
	public JLabel lblTimeStamp = new JLabel("TimeStamp");

	public MomentCell() {
		int height =this.getHeight();
		setBackground(new Color(220, 220, 220));
		//setPreferredSize(new Dimension(600,height));
		//UserIcon = new JLabel(ClientFunction.resizeIcon(User,40));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 510, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//JLabel lblIcon = new JLabel("icon");
		lblIcon = new JLabel(ClientFunction.resizeIcon(User,60));
		lblIcon.setOpaque(false);
		lblIcon.setBackground(new Color(220, 220, 220));
		lblIcon.setPreferredSize(new Dimension(60, 60));
		lblIcon.setVerticalAlignment(SwingConstants.TOP);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.anchor = GridBagConstraints.NORTH;
		gbc_lblIcon.insets = new Insets(0, 0, 0, 5);
		gbc_lblIcon.gridheight = 2;
		gbc_lblIcon.gridx = 0;
		gbc_lblIcon.gridy = 0;
		add(lblIcon, gbc_lblIcon);
		lblName.setBackground(new Color(220, 220, 220));

		lblName.setOpaque(false);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridwidth = 3;
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		add(lblName, gbc_lblName);
		
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		content.setBackground(new Color(220, 220, 220));
		add(content, gbc_panel);
		lblTimeStamp.setBackground(new Color(220, 220, 220));
		lblTimeStamp.setOpaque(false);
		lblTimeStamp.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblTimeStamp = new GridBagConstraints();
		gbc_lblTimeStamp.anchor = GridBagConstraints.WEST;
		gbc_lblTimeStamp.insets = new Insets(0, 0, 0, 5);
		gbc_lblTimeStamp.gridx = 1;
		gbc_lblTimeStamp.gridy = 2;
		add(lblTimeStamp, gbc_lblTimeStamp);

	}

}
