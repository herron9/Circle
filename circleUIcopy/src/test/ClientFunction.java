package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class ClientFunction {

	public static CircleClient client;
	public int type;
	
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
		CPanel.VideoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VideoFrame video = new VideoFrame();
			}
		});
		CPanel.SendMsgBtn.addActionListener(new SendTextButtonHandler(Message.TEXT,CPanel.Inner,CPanel.MsgField,client,friendname));
		MsgReceiver.SrcID=friendname;		
		LoginFunction.RecallHistory(friendname);
		MainFrame.mainFrame.setTitle("Chat with "+friendname);
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
	ImageIcon newIcon = new ImageIcon();
	
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
		    ClientFunction.CPanel.ImgBtn.addActionListener(new SendTextButtonHandler(message.TEXT,ClientFunction.CPanel.Inner,ClientFunction.CPanel.MsgField,ClientFunction.client,message.getMessageSrcID()));
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
			if (message.getMessageType() == Message.TEXT) {
				cell.setPreferredSize(new Dimension(570,55));
				//cell.msg.setPreferredSize(new Dimension());
				cell.msg.setText(message.getMessageContent());
				if (cell.msg.getText().length()>30) {		
					cell.msg.setPreferredSize(new Dimension(400,50));
			        cell.msg.setLineWrap(true);
			        cell.msg.setWrapStyleWord(true);
				}
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
				BufferedImage bi = new BufferedImage(130, 120, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				g.drawImage(img, 15, 15, 100, 100, null);
				newIcon = new ImageIcon(bi);
				cell.setPreferredSize(new Dimension(570, newIcon.getIconHeight()+30));
				cell.ShowArea.remove(cell.msg);
				cell.PicMsg(newIcon);
				cell.ShowArea.setPreferredSize(new Dimension(newIcon.getIconWidth(),newIcon.getIconHeight()));
			}
			
			ClientFunction.CPanel.Inner.add(cell);
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
