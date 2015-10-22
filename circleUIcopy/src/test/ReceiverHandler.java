package test;

import javax.swing.JTextArea;

import communication.Message;

public class ReceiverHandler implements client.ReceiverHandler{

	private JTextArea chatArea;
	
	public ReceiverHandler(JTextArea chatArea) {
		// TODO Auto-generated constructor stub
		this.chatArea = chatArea;
	}
	
	@Override
	public void reaction(Message message) {
		
		// TODO Auto-generated method stub
		System.out.println(message.getMessageTimeStamp());
		System.out.println(message.getMessageSrcID());
		System.out.println(message.getMessageDesIDList());
		System.out.println(message.getMessageContent());
		
		chatArea.append(message.getMessageTimeStamp());chatArea.append("\n");
		chatArea.append(message.getMessageSrcID());chatArea.append("\n");
		chatArea.append(message.getMessageDesIDList().toString());chatArea.append("\n");
		chatArea.append(message.getMessageContent());chatArea.append("\n");
	}

}
