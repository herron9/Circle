package test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import com.sun.security.ntlm.Client;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;

public class ClientFunction {
	
//	public static void CreateClient(String UserName ){
//		try {
//			CircleClient client = new CircleClient(UserName, new TestHandler());
////			CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(CPanel.ChatArea,CPanel.MsgField,client,friendname));
////			//ChattingPanel.ChatArea.append("-"+"Start chatting with"+friendname+"-");
////			//CPanel.ChatArea.addActionListener(new TestHandler());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}	
	
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
	public static void CreateChatting(String friendname){
//		if(friendname == former ){
//			
//		}
//		else{
			MainLayout.CPanel.ChatArea.setText(null);;
			MainLayout.CPanel=new ChattingPanel();
			MainLayout.MainUppage.add(MainLayout.CPanel,"CPanel");
			MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
			MainLayout.CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(MainLayout.CPanel.ChatArea,MainLayout.CPanel.MsgField,client,friendname));
			//TestHandler.getChatArea(CPanel.ChatArea);
//		}

		
	}
	
//	private static JTextArea chatArea;
//    public static void getChatArea(JTextArea ChatArea) {
//    	chatArea = ChatArea;
//    	}
    
}

class TestHandler implements client.ReceiverHandler {

//	private JTextArea chatArea;
//	
//	public void getChatArea(JTextArea chatArea) {
//		this.chatArea = chatArea;
//	}
	
	@Override
	public void reaction(Message message) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,"-"+message.getMessageSrcID()+"-"+message.getMessageContent());
		MainLayout.CPanel.ChatArea.append("-"+message.getMessageSrcID()+"-");
		MainLayout.CPanel.ChatArea.append(" "+message.getMessageTimeStamp()+"\n");
		MainLayout.CPanel.ChatArea.append(message.getMessageContent()+"\n\n");
	}
	
}
