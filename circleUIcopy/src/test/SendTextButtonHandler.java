package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.CircleClient;
import communication.Message;

public class SendTextButtonHandler implements ActionListener{

	JTextField MsgField;
	JTextArea ChatArea;
	CircleClient client;
	static String FriendName;
	
	public SendTextButtonHandler(JTextArea ChatArea,JTextField MsgField, CircleClient client, String friendname) {
	//public SendTextButtonHandler(JTextArea ChatArea,JTextField MsgField, CircleClient client) {
		// TODO Auto-generated constructor stub
		this.MsgField = MsgField;
		this.ChatArea = ChatArea;
		this.client = client;
		SendTextButtonHandler.FriendName = friendname;
	}
//	public static void setname(String name) {
//		FriendName = name;
//	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
    {

		ArrayList<String> des = new ArrayList<>();
	    des.add(FriendName);
	    Message message = new Message();
	    message.setMessageType(Message.TEXT);
	    message.setMessageSrcID(LoginPanel.circleAccessToken);
	    message.setMessageDesIDList(des);
	    message.setMessageContent(MsgField.getText());
	   // message.setMessageTimeStamp(new);
	    
	
		try {
			client.sendTextMessage(message);
			ChatArea.append("- ME -");
			ChatArea.append(" "+message.getMessageTimeStamp()+"\n");
			ChatArea.append(message.getMessageContent()+"\n\n");
			MsgField.setText("");
			LoginFunction.History(FriendPanel.friendname, message.getMessageContent(), message.getMessageTimeStamp(), "- ME -");
			//next step : consider the group chatting
			ChatList.DisplayLog(FriendName,message.getMessageTimeStamp(),message.getMessageContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public class Setname{  
		public void setname(String name) {
			FriendName = name;
		}
	}
}

