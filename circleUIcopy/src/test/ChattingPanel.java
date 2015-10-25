package test;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;


import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.UIManager;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import client.CircleClient;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.text.DefaultCaret;

public class ChattingPanel extends JPanel {

	public JTextArea ChatArea = new JTextArea(16,40);
	public JScrollPane Scroller = new JScrollPane(ChatArea);//add chatarea to scrollarea
	public JTextField MsgField = new JTextField(40);
	public JButton SendMsgBtn = new JButton("Send");
	JPanel South = new JPanel();
	String DesID = "null";
	
	
	public ChattingPanel(CircleClient client) {
		
		
		
		setBackground(UIManager.getColor("CheckBox.background"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Scroller.setPreferredSize(new Dimension(600, 370));
		ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		ChatArea.setBackground(UIManager.getColor("CheckBox.background"));
		ChatArea.setLineWrap(true);//automatic line feed
		ChatArea.setEditable(false);
		Scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DefaultCaret caret = (DefaultCaret)ChatArea.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		 Scroller.setViewportView(ChatArea);//set sroller to the component u want to scroll
		add(Scroller);//add scroller to panel, not the textarea
		MsgField.setHorizontalAlignment(SwingConstants.LEFT);
		MsgField.setBackground(UIManager.getColor("Button.highlight"));
		
		FlowLayout flowLayout = (FlowLayout) South.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		South.add(MsgField);
		South.add(SendMsgBtn);
		SendMsgBtn.addActionListener(new SendTextButtonHandler(ChatArea,MsgField,client,null));
		SendMsgBtn.setPreferredSize(new Dimension(100, 32));
		add(South);
		South.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{MsgField, SendMsgBtn}));

	}

}

