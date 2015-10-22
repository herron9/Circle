package test;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class ChatLog extends JPanel {

	public ImageIcon User = new ImageIcon("bin/avatar.png");//cant scale!
	public JLabel UserIcon = new JLabel(User);
	JPanel Right = new JPanel();
	JLabel NameLabel =new JLabel("null");
	JLabel empty = new JLabel("                                                                          ");
	JLabel historyLabel = new JLabel("no history");
	String name = "null";

	
	public ChatLog() {
		setSize(new Dimension(600, 20));
		setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//		UserIcon.setBounds(0, 0, 20, 20);
//		add(UserIcon);
//		
//		Right.setLayout(new BoxLayout(Right, BoxLayout.Y_AXIS));
//		Right.add(NameLabel);
//		Right.add(empty);
//		Right.add(historyLabel);
//
//		add(Right);
		
		//BoxLayout BoxLayout = (BoxLayout) right.getLayout();

	}
	
	public void setup() {
		UserIcon.setBounds(0, 0, 20, 20);
		add(UserIcon);
		
		Right.setLayout(new BoxLayout(Right, BoxLayout.Y_AXIS));
		Right.add(NameLabel);
		Right.add(empty);
		Right.add(historyLabel);

		add(Right);
		
	}
	
	 private static void addAButton(String text, Container container) {
	        JButton button = new JButton(text);
	        button.setAlignmentX(Component.CENTER_ALIGNMENT);
	        container.add(button);
	    }

}
