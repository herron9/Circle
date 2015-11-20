package test;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Color;

public class MomentCellContent extends JPanel {
	
	JTextArea MomTextArea = new JTextArea(0,0);
	JLabel lblPic = new JLabel("Pic");
	
	public MomentCellContent() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		MomTextArea = new JTextArea("this is my first moment!");
		MomTextArea.setLineWrap(true);
		//MomTextArea.setBackground(new Color(220, 220, 220));
		MomTextArea.setWrapStyleWord(true);
		MomTextArea.setAlignmentY(0.0f);
		MomTextArea.setEditable(false);
		MomTextArea.setOpaque(false);
		//MomTextArea.setPreferredSize(new Dimension(400, 100));
		MomTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(MomTextArea);
		add(lblPic);
		

	}

}
