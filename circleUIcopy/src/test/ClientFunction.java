package test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.security.ntlm.Client;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;

public class ClientFunction {
	
	public static void CreateClient(String UserName ){
		try {
			CircleClient client = new CircleClient(UserName, new TestHandler());
//			CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(CPanel.ChatArea,CPanel.MsgField,client,friendname));
//			//ChattingPanel.ChatArea.append("-"+"Start chatting with"+friendname+"-");
//			//CPanel.ChatArea.addActionListener(new TestHandler());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
}	
	
//	public static void CreateClient(String UserName,ChattingPanel CPanel,String friendname ){
//			try {
//				CircleClient client = new CircleClient(UserName, new TestHandler());
//				CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(CPanel.ChatArea,CPanel.MsgField,client,friendname));
//				//ChattingPanel.ChatArea.append("-"+"Start chatting with"+friendname+"-");
//				//CPanel.ChatArea.addActionListener(new TestHandler());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}		
//	}	
	public static CircleClient client;
	public static void GetClient(CircleClient Client){
		client = Client;		
	}
	public static ChattingPanel CPanel;
	public static String former = "NoOne";
	public static void CreateChatting(String friendname){
//		if(friendname == former ){
//			
//		}
//		else{
			ChattingPanel CPanel = new ChattingPanel();
			//ClientFunction.CreateClient(UserAT,CPanel,friendname);	
			MainLayout.MainUppage.add(friendname, CPanel);
			MainLayout.MainpageCl.show(MainLayout.MainUppage, friendname);
			CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(CPanel.ChatArea,CPanel.MsgField,client,friendname));
			former = friendname;
			//TestHandler.getChatArea(CPanel.ChatArea);
//		}

		
	}
	
//	private static JTextArea chatArea;
//    public static void getChatArea(JTextArea ChatArea) {
//    	chatArea = ChatArea;
//    	}
    
}

class TestHandler implements ReceiverHandler {

//	private JTextArea chatArea;
//	
//	public void getChatArea(JTextArea chatArea) {
//		this.chatArea = chatArea;
//	}
	
	@Override
	public void reaction(Message message) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,"-"+message.getMessageSrcID()+"-"+message.getMessageContent());
		ClientFunction.CPanel.ChatArea.append("-"+message.getMessageSrcID()+"-");
		ClientFunction.CPanel.ChatArea.append(" "+message.getMessageTimeStamp()+"\n");
		ClientFunction.CPanel.ChatArea.append(message.getMessageContent()+"\n\n");
	}
	
}
