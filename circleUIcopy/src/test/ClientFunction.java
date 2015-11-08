package test;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;
import java.awt.CardLayout;
import java.awt.Image;

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
		    for( ActionListener al : CPanel.ImgBtn.getActionListeners() ) {//renew the actionlisetener
		    	CPanel.ImgBtn.removeActionListener( al );
		    }
		CPanel.ImgBtn.addActionListener(new SendTextButtonHandler(Message.LINK,CPanel.Inner,CPanel.MsgField,client,friendname));
		CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(Message.TEXT,CPanel.Inner,CPanel.MsgField,client,friendname));
		MsgReceiver.SrcID=friendname;
		CPanel.Inner.removeAll();
		MainFrame.mainFrame.setTitle("Chat with "+friendname);
		MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
	}
	
	public static void RecallChatting(String friendname){
		for( ActionListener al : CPanel.SendMsgBtn.getActionListeners() ) {
		    CPanel.SendMsgBtn.removeActionListener( al );
		}
		for( ActionListener al : CPanel.ImgBtn.getActionListeners() ) {//renew the actionlisetener
		    CPanel.ImgBtn.removeActionListener( al );
		}
		CPanel.ImgBtn.addActionListener(new SendTextButtonHandler(Message.LINK,CPanel.Inner,CPanel.MsgField,client,friendname));
		CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(Message.TEXT,CPanel.Inner,CPanel.MsgField,client,friendname));
		MsgReceiver.SrcID=friendname;
		LoginFunction.RecallHistory(friendname);
		MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
	}
	public static ImageIcon resizeIcon(ImageIcon old,int x) {
		Image img = old.getImage();
		Image newimg = img.getScaledInstance(x, x, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		return newIcon;
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
		    for( ActionListener al : ClientFunction.CPanel.ImgBtn.getActionListeners() ) {//renew the actionlisetener
		    	ClientFunction.CPanel.ImgBtn.removeActionListener( al );
		    }
		    ClientFunction.CPanel.ImgBtn.addActionListener(new SendTextButtonHandler(message.LINK,ClientFunction.CPanel.Inner,ClientFunction.CPanel.MsgField,ClientFunction.client,message.getMessageSrcID()));
		    ClientFunction.CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(message.TEXT,ClientFunction.CPanel.Inner,ClientFunction.CPanel.MsgField,ClientFunction.client,message.getMessageSrcID()));
		    MsgReceiver.SrcID=message.getMessageSrcID();
		    SrcID = message.getMessageSrcID();
		    ClientFunction.CPanel.Inner.removeAll();
			ChatList.CreateEntry(SrcID);
		}
		if (message.getMessageSrcID().equals(SrcID)) {
			ChattingCellR cell = new ChattingCellR();
			cell.NameLabel.setText(message.getMessageSrcID());
			cell.TimeLabel.setText(message.getMessageTimeStamp());
			cell.msg.setText(message.getMessageContent());
			ClientFunction.CPanel.Inner.add(cell);
//			LoginFunction.History(message.getMessageSrcID(), message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID());
			ChatList.DisplayLog(SrcID,message.getMessageTimeStamp(),message.getMessageContent());
		}
		else{
			ChatList.CreateEntry(message.getMessageSrcID());
//			LoginFunction.History(message.getMessageSrcID(), message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID());
			ChatList.DisplayLog(SrcID,message.getMessageTimeStamp(),message.getMessageContent());
			
		}
		
	}
	
}
