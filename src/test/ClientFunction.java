package test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;

public class ClientFunction {
	
	public static void CreateClient(String UserName,ChattingPanel CPanel){
		//if(LoginFunction.ClientVerifyFlag == true){
		//this.CPanel = CPanel;
			try {
				CircleClient client = new CircleClient(UserName, new TestHandler(CPanel.ChatArea));
				CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(CPanel.ChatArea,CPanel.MsgField,client));
				//CPanel.ChatArea.addActionListener(new TestHandler());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}	

}

class TestHandler implements ReceiverHandler {

	private JTextArea chatArea;
	
	public TestHandler(JTextArea chatArea) {
		// TODO Auto-generated constructor stub
		this.chatArea = chatArea;
	}
	
	@Override
	public void reaction(Message message) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,"-"+message.getMessageSrcID()+"-"+message.getMessageContent());
		chatArea.append("-"+message.getMessageSrcID()+"-");
		chatArea.append(" "+message.getMessageTimeStamp()+"\n");
		chatArea.append(message.getMessageContent()+"\n");
	}
	
	
	
}
