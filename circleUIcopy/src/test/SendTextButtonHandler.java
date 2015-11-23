package test;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import client.CircleClient;
import communication.Message;
import test.ChattingPanel.Emoji;

public class SendTextButtonHandler implements ActionListener{

	JTextField MsgField;
	JTextArea ChatArea;
	CircleClient client;
	JPanel Inner;
	String emoji;
	int type;
	
	static String FriendName;
	ImageIcon newIcon = new ImageIcon();
	BufferedImage bi;

	public SendTextButtonHandler(int type, JPanel Inner,JTextField MsgField, CircleClient client, String friendname,String emoji) {
	//public SendTextButtonHandler(JTextArea ChatArea,JTextField MsgField, CircleClient client) {
		// TODO Auto-generated constructor stub
		this.type=type;
		this.MsgField = MsgField;
		//this.ChatArea = ChatArea;
		this.client = client;
		this.Inner =Inner;
		this.emoji=emoji;
		SendTextButtonHandler.FriendName = friendname;
	}
//	public static void setname(String name) {
//		FriendName = name;
//	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
    {
	    Message message = new Message();
	    String filePath=null;
		String fileurl=null;
	    boolean send=false;

		if(type==Message.LINK){
			
	 		s3Repository s3= new s3Repository();
			String key = ""+UUID.randomUUID()+".jpg";
			if(emoji==null){
				filePath = SwingFileChooserDemo.chooseAFileFromCurrentMachine();
			}
			else{
				filePath = emoji;
			}
			
			if(filePath!=null){
				send=true;
				s3.uploadFile(key,filePath);
				fileurl="https://s3.amazonaws.com/circleuserfiles/"+key;
				ImageIcon image=new ImageIcon(filePath);
				Image img = image.getImage();
				int height = image.getIconHeight()*300/image.getIconWidth();
				if(emoji==null){
					bi = new BufferedImage(300, height, BufferedImage.TYPE_INT_ARGB);
					Graphics g = bi.createGraphics();
					g.drawImage(img, 0, 0, 300, height, null);
					newIcon = new ImageIcon(bi);
				}
				else{
					bi = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
					Graphics g = bi.createGraphics();
					g.drawImage(img, 0, 0, 30, 30, null);
					newIcon = new ImageIcon(bi);
				}
			}		
			ArrayList<String> des = new ArrayList<>();
		    des.add(FriendPanel.friendname);
		    message.setMessageType(Message.LINK);
		    message.setMessageSrcID(LoginPanel.circleAccessToken);
		    message.setMessageDesIDList(des);
		    message.setMessageContent(fileurl);
			

		}
		else if(type==Message.TEXT){
			send=true;
			ArrayList<String> des = new ArrayList<>();
		    des.add(FriendPanel.friendname);
		    message.setMessageType(Message.TEXT);
		    message.setMessageSrcID(LoginPanel.circleAccessToken);
		    message.setMessageDesIDList(des);
		    message.setMessageContent(MsgField.getText());
		}
		
	    if(send==true){
	    	try {
				client.sendTextMessage(message);
				ChattingCellS cell = new ChattingCellS();
				cell.NameLabel.setText(message.getMessageSrcID());
				cell.TimeLabel.setText(message.getMessageTimeStamp());
				cell.UserIcon.setIcon(LoginFunction.userIcon);

				if(type==Message.TEXT){
					if (message.getMessageContent()=="") {
						
					}
					else{
					cell.setPreferredSize(new Dimension(520,55));
					cell.msg.setText(message.getMessageContent());
					if (cell.msg.getText().length()>30) {
						
						cell.msg.setPreferredSize(new Dimension(400,50));
				        cell.msg.setLineWrap(true);
				        cell.msg.setWrapStyleWord(true);
					}
					}
				}
				else if(type==Message.LINK){

					cell.setPreferredSize(new Dimension(520, newIcon.getIconHeight()+20));
					cell.ShowArea.remove(cell.msg);
					cell.PicMsg(newIcon);
					cell.ShowArea.setPreferredSize(new Dimension(newIcon.getIconWidth(),newIcon.getIconHeight()));
					//cell.ShowArea.setPreferredSize(new Dimension(300,300));
					//cell.ShowArea.add(cell.Image);
				}
				MsgField.setText(null);
				ClientFunction.CPanel.vertical.setValue(ClientFunction.CPanel.vertical.getMaximum());
				Inner.add(cell);
				//cell.setAlignmentX(0);
				Inner.revalidate();
				Inner.repaint();
				LoginFunction.History(type,FriendName, message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID(),bi);
				ChatList.DisplayLog(type,FriendName,message.getMessageTimeStamp(),message.getMessageContent());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }


    }
	
	public class Setname{  
		public void setname(String name) {
			FriendName = name;
		}
	}
}

