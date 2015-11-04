package test;

import java.awt.event.ActionListener;
import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;
import java.awt.CardLayout;

public class ClientFunction {

	public static CircleClient client;
	
	public static ChattingPanel CPanel = new ChattingPanel(client);
	public static boolean Init = true;
	//public static boolean ReceiverInit = true;
	public static void GetClient(CircleClient Client){
		client = Client;		
	}
	public ClientFunction(){
		
	}
	
	public static void CreateChatting(String friendname){
		if (Init == true) {//the first time a client start a chat window
			MainLayout.MainUppage.add(CPanel,"CPanel");
			
			Init= false;
		}
			//MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
		    for( ActionListener al : CPanel.SendMsgBtn.getActionListeners() ) {//renew the actionlisetener
		    	CPanel.SendMsgBtn.removeActionListener( al );
		    }
		CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(CPanel.ChatArea,CPanel.MsgField,client,friendname));
		MsgReceiver.SrcID=friendname;
		CPanel.ChatArea.setText(null);
		MainFrame.mainFrame.setTitle("Chat with "+friendname);
		MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
	}
	
	public static void RecallChatting(String friendname){
		    for( ActionListener al : CPanel.SendMsgBtn.getActionListeners() ) {
		    	CPanel.SendMsgBtn.removeActionListener( al );
		    }
		CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(CPanel.ChatArea,CPanel.MsgField,client,friendname));
		MsgReceiver.SrcID=friendname;
		LoginFunction.RecallHistory(friendname);
		MainFrame.mainFrame.setTitle("Chat with "+friendname);
		MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
	}
    
}

class MsgReceiver implements ReceiverHandler {

	public static String SrcID = null; //="null";
	
	@Override
	public void reaction(Message message) {
		if (SrcID == null) {
			MainLayout.MainUppage.add(ClientFunction.CPanel,"CPanel");
		    for( ActionListener al : ClientFunction.CPanel.SendMsgBtn.getActionListeners() ) {//renew the actionlisetener
		    	ClientFunction.CPanel.SendMsgBtn.removeActionListener( al );
		    }
		    ClientFunction.CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(ClientFunction.CPanel.ChatArea,ClientFunction.CPanel.MsgField,ClientFunction.client,message.getMessageSrcID()));
		MsgReceiver.SrcID=message.getMessageSrcID();
		SrcID = message.getMessageSrcID();
		ClientFunction.CPanel.ChatArea.setText(null);
		ChatList.CreateEntry(SrcID);
		//MainFrame.mainFrame.setTitle("Chat with "+SrcID);
		//MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
		}
		if (message.getMessageSrcID().equals(SrcID)) {
			ClientFunction.CPanel.ChatArea.append("-"+message.getMessageSrcID()+"-");
			ClientFunction.CPanel.ChatArea.append(" "+message.getMessageTimeStamp()+"\n");
			ClientFunction.CPanel.ChatArea.append(message.getMessageContent()+"\n\n");
			LoginFunction.History(message.getMessageSrcID(), message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID());
			ChatList.DisplayLog(SrcID,message.getMessageTimeStamp(),message.getMessageContent());
		}
		else{
			ChatList.CreateEntry(message.getMessageSrcID());
			LoginFunction.History(message.getMessageSrcID(), message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID());
			ChatList.DisplayLog(SrcID,message.getMessageTimeStamp(),message.getMessageContent());
			
		}
		
	}
	
}
