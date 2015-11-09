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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import com.amazonaws.services.elasticsearch.model.CreateElasticsearchDomainRequest;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;

import client.CircleClient;
import communication.Message;

import scala.collection.generic.BitOperations.Int;
import scala.util.Left;

public class SendTextButtonHandler implements ActionListener{

	JTextField MsgField;
	JTextArea ChatArea;
	CircleClient client;
	JPanel Inner;
	int type;
	static String FriendName;
	ImageIcon newIcon = new ImageIcon();

	public SendTextButtonHandler(int type, JPanel Inner,JTextField MsgField, CircleClient client, String friendname) {
	//public SendTextButtonHandler(JTextArea ChatArea,JTextField MsgField, CircleClient client) {
		// TODO Auto-generated constructor stub
		this.type=type;
		this.MsgField = MsgField;
		//this.ChatArea = ChatArea;
		this.client = client;
		this.Inner =Inner;
		SendTextButtonHandler.FriendName = friendname;
	}
//	public static void setname(String name) {
//		FriendName = name;
//	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
    {
	    Message message = new Message();
		if(type==Message.LINK){
			s3Repository s3= new s3Repository();
			String key = ""+UUID.randomUUID()+".jpg";
			String filePath =SwingFileChooserDemo.chooseAFileFromCurrentMachine();
			s3.uploadFile(key,filePath);
			String fileurl="https://s3.amazonaws.com/circleuserfiles/"+key;
			System.out.println(fileurl);
			BufferedImage bufferedImage = null;
			try {
				URL myURL = new URL(fileurl);
				bufferedImage = ImageIO.read(myURL);
			} catch (IOException f) {
			}
			//bufferedImage.getHeight()
			ImageIcon image=new ImageIcon(bufferedImage);
			Image img = image.getImage();
			BufferedImage bi = new BufferedImage(130, 120, BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img, 15, 15, 100, 100, null);
			newIcon = new ImageIcon(bi);
			
			ArrayList<String> des = new ArrayList<>();
		    des.add(FriendPanel.friendname);
		    message.setMessageType(Message.LINK);
		    message.setMessageSrcID(LoginPanel.circleAccessToken);
		    message.setMessageDesIDList(des);
		    message.setMessageContent(fileurl);
		}
		else if(type==Message.TEXT){
			ArrayList<String> des = new ArrayList<>();
		    des.add(FriendPanel.friendname);
		    message.setMessageType(Message.TEXT);
		    message.setMessageSrcID(LoginPanel.circleAccessToken);
		    message.setMessageDesIDList(des);
		    message.setMessageContent(MsgField.getText());
		}
		
		
	    try {
			client.sendTextMessage(message);
			ChattingCellS cell = new ChattingCellS();
			cell.NameLabel.setText(message.getMessageSrcID());
			cell.TimeLabel.setText(message.getMessageTimeStamp());

			if(type==Message.TEXT){
				cell.setPreferredSize(new Dimension(520,70));
				//cell.msg.setPreferredSize(new Dimension());
				cell.msg.setText(message.getMessageContent());
				if (cell.msg.getText().length()>30) {
					
					cell.msg.setPreferredSize(new Dimension(400,50));
			        cell.msg.setLineWrap(true);
			        cell.msg.setWrapStyleWord(true);
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
			
			LoginFunction.History(FriendPanel.friendname, message.getMessageContent(), message.getMessageTimeStamp(), message.getMessageSrcID());
			ChatList.DisplayLog(FriendName,message.getMessageTimeStamp(),message.getMessageContent());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


    }
	
	public class Setname{  
		public void setname(String name) {
			FriendName = name;
		}
	}
}

