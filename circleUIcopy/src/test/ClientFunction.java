package test;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class ClientFunction {

	public static CircleClient client;
	public int type;
	public static String friendNickname;
	public static ImageIcon usericon;
	
	public static ChattingPanel CPanel = new ChattingPanel(client);
	public static boolean Init = true;
	//public static boolean ReceiverInit = true;
	public static void GetClient(CircleClient Client){
		client = Client;		
	}
	public ClientFunction(){
		
	}
	public static void setFriendInfo(String friendname) {
		for (int i = 0; i < LoginFunction.userfriend.size(); i++) {
			if (friendname.equals(LoginFunction.userfriend.get(i).username)) {
//				System.out.println("ClientFunction/nickname:   "+LoginFunction.userfriend.get(i).username);
//				System.out.println("ClientFunction/nickname:   "+LoginFunction.userfriend.get(i).nickname);
				if (LoginFunction.userfriend.get(i).nickname.equals("Unknown")) {
					friendNickname = LoginFunction.userfriend.get(i).username;
				}else{
					friendNickname = LoginFunction.userfriend.get(i).nickname;
				}	
				usericon= LoginFunction.userfriend.get(i).image;
			}
		}
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
		CPanel.ImgBtn.addActionListener(new SendTextButtonHandler(Message.LINK,CPanel.Inner,CPanel.MsgField,client,friendname,null));
		CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(Message.TEXT,CPanel.Inner,CPanel.MsgField,client,friendname,null));
		MsgReceiver.SrcID=friendname;
		CPanel.Inner.removeAll();
		setFriendInfo(friendname);
		MainFrame.mainFrame.setTitle("Chat with "+friendNickname);
		MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
	}
	
	public static void RecallChatting(String friendname){
//		if (Init == true) {//the first time a client start a chat window
//			MainLayout.MainUppage.add(CPanel,"CPanel");
//			
//			Init= false;
//		}
		for( ActionListener al : CPanel.SendMsgBtn.getActionListeners() ) {
		    CPanel.SendMsgBtn.removeActionListener( al );
		}
		for( ActionListener al : CPanel.ImgBtn.getActionListeners() ) {//renew the actionlisetener
		    CPanel.ImgBtn.removeActionListener( al );
		}
		CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(Message.TEXT,CPanel.Inner,CPanel.MsgField,client,friendname,null));
		CPanel.ImgBtn.addActionListener(new SendTextButtonHandler(Message.LINK,CPanel.Inner,CPanel.MsgField,client,friendname,null));
		MsgReceiver.SrcID=friendname;	
		System.out.println("clientfunction/MsgReceiver.SrcID:  "+MsgReceiver.SrcID);
		LoginFunction.RecallHistory(friendname);
		setFriendInfo(friendname);
		MainFrame.mainFrame.setTitle("Chat with "+friendNickname);
		MainLayout.MainpageCl.show(MainLayout.MainUppage, "CPanel");
	}
	public static ImageIcon resizeIcon(ImageIcon old,int x,int y) {
		Image img = old.getImage();
		Image newimg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		return newIcon;
	}
	
	public static String ID2Nick(String ID) {
		String nick = null;
		for (int i = 0; i < LoginFunction.userfriend.size(); i++) {
			if (ID.equals(LoginFunction.userfriend.get(i).username)) {
				nick = LoginFunction.userfriend.get(i).nickname;
			}
		}
		return nick;
	}
	
	public static String Nick2ID(String Nick) {
		String ID = null;
		for (int i = 0; i < LoginFunction.userfriend.size(); i++) {
			if (Nick.equals(LoginFunction.userfriend.get(i).nickname)) {
				ID = LoginFunction.userfriend.get(i).username;
			}
		}
		return ID;
	}
	
	public static ImageIcon ID2icon(String ID) {
		ImageIcon icon = null;
		for (int i = 0; i < LoginFunction.userfriend.size(); i++) {

			if (ID.equals(LoginFunction.userfriend.get(i).username)) {
				
				if (LoginFunction.userfriend.get(i).image!=null) {
					icon = LoginFunction.userfriend.get(i).image;
				}
				else{
					icon = new ImageIcon("src/avatar.png");
				}
			}
		}
//		System.out.println(icon);
		return icon;
	}
}

class MsgReceiver implements ReceiverHandler {

	public static String SrcID = null; //="null";
	ImageIcon newIcon = new ImageIcon();
	BufferedImage bi;
	int width,height;
	
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
		    ClientFunction.CPanel.ImgBtn.addActionListener(new SendTextButtonHandler(message.LINK,ClientFunction.CPanel.Inner,ClientFunction.CPanel.MsgField,ClientFunction.client,message.getMessageSrcID(),null));
		    ClientFunction.CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(message.TEXT,ClientFunction.CPanel.Inner,ClientFunction.CPanel.MsgField,ClientFunction.client,message.getMessageSrcID(),null));
		    MsgReceiver.SrcID=message.getMessageSrcID();
		    SrcID = message.getMessageSrcID();
		    ClientFunction.CPanel.Inner.removeAll();
			ChatList.CreateEntry(SrcID);
		}
		if (message.getMessageSrcID().equals(SrcID)) {
			ChattingCellR cell = new ChattingCellR();
			cell.NameLabel.setText(ClientFunction.ID2Nick(message.getMessageSrcID()));
			cell.TimeLabel.setText(message.getMessageTimeStamp());
			cell.UserIcon.setIcon(ClientFunction.usericon);
			if (message.getMessageType() == Message.TEXT) {
//				cell.setPreferredSize(new Dimension(570,55));
				cell.msg.setText(message.getMessageContent());
//				if (cell.msg.getText().length()>50) {		
//					cell.msg.setPreferredSize(new Dimension(400,100));
//			        cell.msg.setLineWrap(true);
//			        cell.msg.setWrapStyleWord(true);
//				}
			}
			else if(message.getMessageType() == Message.LINK){
				BufferedImage bufferedImage = null;
				try {
					URL myURL = new URL(message.getMessageContent());
					bufferedImage = ImageIO.read(myURL);
				} catch (IOException f) {
				}
				ImageIcon image=new ImageIcon(bufferedImage);
				Image img = image.getImage();
				if(image.getIconWidth()>300){
					width=300;
					height = image.getIconHeight()*300/image.getIconWidth();
				}
				else{
					width=image.getIconWidth();
					height=image.getIconHeight();
				}
				bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				g.drawImage(img, 0, 0, width, height, null);
				newIcon = new ImageIcon(bi);
				cell.setPreferredSize(new Dimension(570, newIcon.getIconHeight()+30));
				cell.ShowArea.remove(cell.msg);
				cell.PicMsg(newIcon);
				cell.ShowArea.setPreferredSize(new Dimension(newIcon.getIconWidth(),newIcon.getIconHeight()));
			}
			
			ClientFunction.CPanel.Inner.add(cell);
			ClientFunction.CPanel.Inner.revalidate();
			ClientFunction.CPanel.Inner.repaint();
			LoginFunction.History(message.getMessageType(), message.getMessageSrcID(), 
			message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID(), bi);
			ChatList.DisplayLog(message.getMessageType(),SrcID,message.getMessageTimeStamp(),message.getMessageContent());
		}
		else{
			ChatList.CreateEntry(message.getMessageSrcID());
			LoginFunction.History(message.getMessageType(), message.getMessageSrcID(), 
	        message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID(), bi);
			ChatList.DisplayLog(message.getMessageType(),SrcID,message.getMessageTimeStamp(),message.getMessageContent());
			
		}
		
	}
	
}
