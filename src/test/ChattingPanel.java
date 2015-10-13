package test;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.UIManager;

//import client.CircleClient;
//import client.ReceiverHandler;
//import communication.Message;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class ChattingPanel extends JPanel {
	
	JTextArea ChatArea = new JTextArea(20,20);
	JScrollPane ScrollChatArea = new JScrollPane(ChatArea);
	public static JTextField MsgField = new JTextField(20);
	JButton SendMsgBtn = new JButton("Send");
	
	
	public ChattingPanel() {
		setBackground(UIManager.getColor("CheckBox.select"));
		setLayout(null);

		ScrollChatArea.setBounds(0, 0, 600, 370);
		ChatArea.setForeground(new Color(0, 0, 0));
		ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ChatArea.setBounds(0, 0, 600, 370);
		ChatArea.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		ChatArea.setLineWrap(true);//automatic line feed
		ChatArea.setEditable(false); //forbid input from chatarea
		ScrollChatArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollChatArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollChatArea.add(ChatArea);
		add(ScrollChatArea);
		MsgField.setBackground(UIManager.getColor("Button.highlight"));
		MsgField.setBounds(0,370,480,30);
		MsgField.setText("Hello!");
		add(MsgField);
		
		class FocusHandler extends FocusAdapter{
        	public void focusGained(FocusEvent e) {
        		if(e.getSource()==MsgField){
        			if("Hello!".equals(MsgField.getText()))
        				MsgField.setText("");
        		}
        	}    	
        	public void focusLost(FocusEvent e) {
        		if(e.getSource()==MsgField){
        			if("".equals(MsgField.getText()) )
        				MsgField.setText("Hello!");
        		}
        	}
        }
        
		MsgField.addFocusListener(new FocusHandler());


		SendMsgBtn.setBounds(480,368,120,34);
		add(SendMsgBtn);
		
//		try {
//			CircleClient client = new CircleClient("huang zhi", new TestHandler(ChatArea));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		SendMsgBtn.addActionListener(new SendTextButtonHandler(MsgField, ChatArea));
//
//
	}

}

//class TestHandler implements ReceiverHandler {
//
//	private JTextArea chatArea;
//	
//	public TestHandler(JTextArea chatArea) {
//		// TODO Auto-generated constructor stub
//		this.chatArea = chatArea;
//	}
//	
//	@Override
//	public void reaction(Message message) {
//		// TODO Auto-generated method stub
//		chatArea.append("Sender:"+message.getMessageSrcID());
//		chatArea.append(" "+message.getMessageTimeStamp()+"\n");
//		chatArea.append(message.getMessageContent()+"\n");
//	}
//	
//}
