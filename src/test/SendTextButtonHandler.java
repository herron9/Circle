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
	
	public SendTextButtonHandler(JTextArea ChatArea,JTextField MsgField, CircleClient client) {
		// TODO Auto-generated constructor stub
		this.MsgField = MsgField;
		this.ChatArea = ChatArea;
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
    {

		ArrayList<String> des = new ArrayList<>();
	    des.add("duras.yhr@gmail.com");
		//des.add("yanghrong@outlook.com");
//		Message message = new Message(Message.TEXT, des, "huang zhi", "test message", new Date().toString());
		Message message = Message.builder()
				.messageType(Message.TEXT)
				.messageSrcID(LoginPanel.circleAccessToken)
				.messageDesIDList(des)
				.messageContent(MsgField.getText())
				.messageTimeStamp(new Date().toString())
				.build();
		try {
			client.sendTextMessage(message);
			//ChatArea.setForeground(new Color(51, 153, 102));//blue
			ChatArea.append("-"+message.getMessageSrcID()+"-");
			//ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			ChatArea.append(" "+message.getMessageTimeStamp()+"\n");
			//ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			ChatArea.append(message.getMessageContent()+"\n");
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

