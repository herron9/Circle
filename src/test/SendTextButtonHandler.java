package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.CircleClient;
import communication.Message;

public class SendTextButtonHandler implements ActionListener{

	JTextField MsgField;
	JTextArea ChatArea;
	CircleClient client;
	String FriendName;
	
	public SendTextButtonHandler(JTextArea ChatArea,JTextField MsgField, CircleClient client, String friendname) {
		// TODO Auto-generated constructor stub
		this.MsgField = MsgField;
		this.ChatArea = ChatArea;
		this.client = client;
		this.FriendName = friendname;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
    {

		ArrayList<String> des = new ArrayList<>();
	    des.add(FriendPanel.friendname);
	    Message message = new Message();
	    message.setMessageType(Message.TEXT);
	    message.setMessageSrcID(LoginPanel.circleAccessToken);
	    message.setMessageDesIDList(des);
	    message.setMessageContent(MsgField.getText());
	   // message.setMessageTimeStamp(new);
	    
	
		try {
			client.sendTextMessage(message);
			//ChatArea.setForeground(new Color(51, 153, 102));//blue
			ChatArea.append("- ME -");
			//ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			ChatArea.append(" "+message.getMessageTimeStamp()+"\n");
			//ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			ChatArea.append(message.getMessageContent()+"\n\n");
			//JOptionPane.showMessageDialog(null,"-"+message.getMessageSrcID()+"-"+message.getMessageContent());
			//ChatArea.setForeground(new Color(0, 153, 255));
			//ChatArea.append(MsgField.getText());
			MsgField.setText("");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

