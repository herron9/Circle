package test;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.Color;


public class MainChatPanel extends JPanel {

	public JPanel MCInter = new JPanel();
	public JScrollPane MainScroller = new JScrollPane(MCInter);
	
	public MainChatPanel() {

		
		setSize(new Dimension(600, 400));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		MCInter.setPreferredSize(new Dimension(600, 400));
		MainScroller.setPreferredSize(new Dimension(600, 400));
		MainScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		MainScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		MainScroller.setViewportView(MCInter);
		MCInter.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		add(MainScroller);

	}

}
