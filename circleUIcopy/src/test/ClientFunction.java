package test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.org.apache.xml.internal.security.Init;
import com.sun.security.ntlm.Client;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;
import test.SendTextButtonHandler.Setname;
import javax.swing.JFrame;

public class ClientFunction {

	public static CircleClient client;
	
	public static ChattingPanel CPanel = new ChattingPanel(client);
	public static boolean Init = true;
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
		LoginFunction.RecallHistory(CPanel, friendname);
		MainFrame.mainFrame.setTitle("Chat with "+friendname);
		MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
	}
    
}

class MsgReceiver implements ReceiverHandler {

	public static String SrcID;
	
	@Override
	public void reaction(Message message) {
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
