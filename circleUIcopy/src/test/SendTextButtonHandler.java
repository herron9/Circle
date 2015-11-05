package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

import client.CircleClient;
import communication.Message;
import scala.util.Left;

public class SendTextButtonHandler implements ActionListener{

	JTextField MsgField;
	JTextArea ChatArea;
	CircleClient client;
	JPanel Inner;
	static String FriendName;

	public SendTextButtonHandler(JPanel Inner,JTextField MsgField, CircleClient client, String friendname) {
	//public SendTextButtonHandler(JTextArea ChatArea,JTextField MsgField, CircleClient client) {
		// TODO Auto-generated constructor stub
		this.MsgField = MsgField;
		//this.ChatArea = ChatArea;
		this.client = client;
		this.Inner =Inner;
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
			ChattingCellS cell = new ChattingCellS();
			cell.NameLabel.setText(message.getMessageSrcID());
			cell.TimeLabel.setText(message.getMessageTimeStamp());
			cell.msg.setText(message.getMessageContent());
			if (cell.msg.getText().length()>20) {
				cell.msg.setSize(400,200);
		        cell.msg.setLineWrap(true);
		        cell.msg.setWrapStyleWord(true);
			}
			MsgField.setText(null);
			Inner.add(cell);
			Inner.revalidate();
			Inner.repaint();
			LoginFunction.History(FriendPanel.friendname, message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID());
			ChatList.DisplayLog(FriendName,message.getMessageTimeStamp(),message.getMessageContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public class Setname{  
		public void setname(String name) {
			FriendName = name;
		}
	}
}

