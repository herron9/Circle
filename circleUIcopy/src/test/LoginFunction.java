package test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.CircleClient;
import communication.Message;
import scala.collection.generic.BitOperations.Int;



public class LoginFunction {

	public static String AccessToken;
	public static ArrayList<ChatHistory> receiver=new ArrayList<>();
	public static String Gender;
	public static String Phonenumber;
	public static String Nickname;
	public static String Iconurl=null;
	public static boolean friendrequest=false;
	public static ArrayList<Moments> moments=new ArrayList<>();
	public static ArrayList<Userid> userfriend=new ArrayList<>();

	
	public static void History(int type,String friendname,String content,String time,String sourceID,BufferedImage bImage) {
		boolean find=false;
		int index=0;
		for(int i=0;i<receiver.size();i++){
			if(friendname.equals(receiver.get(i).friendname)){
				find=true;
				index=i;
			}
		}
		if(find==true){
			chatRecord record = new chatRecord();
			if(type==Message.TEXT){
				record.message=content;
			}
			else if(type==Message.LINK){
				record.message="[picture]";
				record.image=new ImageIcon(bImage);
			}
			record.type=type;
			record.time=time;
			record.sourceID=sourceID;
			receiver.get(index).history.add(record);
		}
		else{
			ChatHistory chat = new ChatHistory();
			chat.friendname=friendname;
			chatRecord record = new chatRecord();
			if(type==Message.TEXT){
				record.message=content;
			}
			else if(type==Message.LINK){
				record.message="[picture]";
				record.image=new ImageIcon(bImage);
			}
			record.type=type;
			record.time=time;
			record.sourceID=sourceID;
			chat.history.add(record);
			receiver.add(chat);
		}
	}
		
	public static void RecallHistory(String friendname) {
		ClientFunction.CPanel.Inner.removeAll();
		for(int i=0;i<receiver.size();i++){
			if(friendname.equals(receiver.get(i).friendname)){
				for(int j=0;j<receiver.get(i).history.size();j++){
					if (friendname.equals(receiver.get(i).history.get(j).sourceID)) {
						ChattingCellS cell = new ChattingCellS();
						cell.NameLabel.setText(receiver.get(i).history.get(j).sourceID);
						cell.TimeLabel.setText(receiver.get(i).history.get(j).time);
						if(receiver.get(i).history.get(j).type==Message.TEXT){
							cell.msg.setText(receiver.get(i).history.get(j).message);
						}
						else if(receiver.get(i).history.get(j).type==Message.LINK){
							cell.setPreferredSize(new Dimension(520,receiver.get(i).history.get(j).image.getIconHeight()+20));
							cell.ShowArea.remove(cell.msg);
							cell.PicMsg(receiver.get(i).history.get(j).image);
							cell.ShowArea.setPreferredSize(new Dimension(receiver.get(i).history.get(j).image.getIconWidth(),receiver.get(i).history.get(j).image.getIconHeight()));
						}
						ClientFunction.CPanel.Inner.add(cell);
						ClientFunction.CPanel.Inner.revalidate();
						ClientFunction.CPanel.Inner.repaint();
					}
					else if (LoginPanel.circleAccessToken.equals(receiver.get(i).history.get(j).sourceID)) {
						ChattingCellS cell = new ChattingCellS();
						cell.NameLabel.setText(receiver.get(i).history.get(j).sourceID);
						cell.TimeLabel.setText(receiver.get(i).history.get(j).time);
						if(receiver.get(i).history.get(j).type==Message.TEXT){
							cell.msg.setText(receiver.get(i).history.get(j).message);
						}
						else if(receiver.get(i).history.get(j).type==Message.LINK){
							cell.setPreferredSize(new Dimension(520,receiver.get(i).history.get(j).image.getIconHeight()+20));
							cell.ShowArea.remove(cell.msg);
							cell.PicMsg(receiver.get(i).history.get(j).image);
							cell.ShowArea.setPreferredSize(new Dimension(receiver.get(i).history.get(j).image.getIconWidth(),receiver.get(i).history.get(j).image.getIconHeight()));
						}						ClientFunction.CPanel.Inner.add(cell);
						ClientFunction.CPanel.Inner.revalidate();
						ClientFunction.CPanel.Inner.repaint();
					}
					
				}
			}
		}
	}
	
	public static void Login(String operation,String username,String password) {
		int p1,p2;
		String str1="circleAccessToken";
		String str2="code";
		
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"username="+username+"&password="+password,"");
		System.out.println("Login: "+response);
		  if(response.indexOf("true")!=-1){
      		  //JOptionPane.showMessageDialog(null,"login successful");
      		  operation="accessToken-Verification?";
			  p1=response.indexOf(str1);
			  p2=response.indexOf(str2);
			  AccessToken=response.substring(p1+20, p2-3);
      		  VerifyAccessToken(operation,AccessToken);
      		try {
				CircleClient client0 = new CircleClient(LoginPanel.circleAccessToken, new MsgReceiver());
				ProfilePanel.getUserID(LoginPanel.circleAccessToken);
				ClientFunction.GetClient(client0);
				ClientFunction.CPanel = new ChattingPanel(client0);
				
      		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
      		  }
      		  operation="friendRequestList-request?";
      		  CheckFriendRequest(operation,AccessToken);
      		  operation="friendList-request?";
      		  GetFriendList(operation, AccessToken);
      		  operation="get-friend-result-list?";
    		  GetFriendResultList(operation, AccessToken);
//    		  operation="create-user-profile?";
//    		  CreateProfile(operation, AccessToken);
    		  operation="get-user-profile?";
    		  GetProfile(operation, AccessToken);
    		  MainLayout.panelPro.setInfo(Gender,Phonenumber,Iconurl);

   		 
          	  MainFrame.cl.show(MainFrame.panelCont, "Main");
		  }
		  else{
      		  JOptionPane.showMessageDialog(null,"login failed");
		  }
	}
	
	public static void VerifyAccessToken(String operation,String AccessToken) {
		int p1,p2;
		String str1="username";
		String str2="code";
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken,"");
		System.out.println("VerifyAccessToken: "+response);
		  if(response.indexOf("true")!=-1){
			  p1=response.indexOf(str1);
			  p2=response.indexOf(str2);
			  LoginPanel.circleAccessToken=response.substring(p1+11, p2-3);
		  }
		  else{
      		  JOptionPane.showMessageDialog(null,"failed");
		  }
	}
	
	public static void GetFriendList(String operation,String AccessToken) {
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken,"");
		System.out.println("GetFriendList: "+response);
		LoginPanel.names=parseFriendList(response);
		MainLayout.FriendList.removeAll();
		MainLayout.FriendList=new FriendPanel(LoginPanel.names);
		MainLayout.MainUppage.add(MainLayout.FriendList,"FriendList");    
	}
	
	public static void GetFriendResultList(String operation,String AccessToken) {
		int p1,p2,p3,p4;
		String str1="username";
		String str2="nickname";
		String str3="iconurl";
		String str4="}";
		String username;
		String nickname;
		String iconurl;
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken,"");
		System.out.println("GetFriendList: "+response);
		p1 = response.indexOf(str1);
		p2 = response.indexOf(str2);
		p3 = response.indexOf(str3);
		p4 = response.indexOf(str4);
		

		while (p1 >= 0) {
			username=response.substring(p1+11, p2-3);
			nickname=response.substring(p2+11, p3-3);
			iconurl=response.substring(p3+10, p4-1);
			Userid user = new Userid();
			
			if(!iconurl.equals("Unknown")){
				BufferedImage bufferedImage = null;
				try {
					URL myURL = new URL(iconurl);
					bufferedImage = ImageIO.read(myURL);
				} catch (IOException f) {
				}
				ImageIcon image=new ImageIcon(bufferedImage);
				Image img = image.getImage();
				BufferedImage bi = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				g.drawImage(img, 0, 0, 30, 30, null);
				user.image = new ImageIcon(bi);
			}
			user.username=username;
			user.nickname=nickname;
			
			userfriend.add(user);
		
			p1 = response.indexOf(str1,p1+1);
			p2 = response.indexOf(str2,p2+1);
			p3 = response.indexOf(str3,p3+1);
			p4 = response.indexOf(str4,p4+1);
		}
		
	}
	
	public static void GetProfile(String operation,String AccessToken) {
		int p1,p2,p3,p4,p5;
		String str1="gender";
		String str2="phoneNumber";
		String str3="nickName";
		String str4="iconUrl";
		String str5="information";
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken,"");
		System.out.println("GetProfile: "+response);
		if(response.indexOf("true")!=-1){
			p1=response.indexOf(str1);
			p2=response.indexOf(str2);
			p3=response.indexOf(str3);
			p4=response.indexOf(str4);
			p5=response.indexOf(str5);
			Gender=response.substring(p1+9, p2-3);
			Phonenumber=response.substring(p2+14,p3-3);
			Nickname=response.substring(p3+11,p4-3);
			Iconurl=response.substring(p4+10,p5-3);
			System.out.println(Iconurl);
		}
	}
	
	public static void AddAFriend(String operation,String AccessToken,String friendname) {
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken+"&friend="+friendname,"");
		System.out.println("AddAFriend: "+response);
		if(response.indexOf("true")!=-1){
		}
		else if (response.indexOf("Your friend has not registered yet.")!=-1) {
			JOptionPane.showMessageDialog(null,"Your friend has not registered yet.");	
		}
		else if(response.indexOf("You has already added that friend")!=-1){
    		  JOptionPane.showMessageDialog(null,"You has already added that friend");
		}
		else{
			JOptionPane.showMessageDialog(null,"friend name is wrong.");
		}
	}
	
	public static void CheckFriendRequest(String operation,String AccessToken){
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken,"");
		System.out.println("CheckFriendRequest: "+response);
		if(response.indexOf("requester")!=-1){
			friendrequest=true;
		}
		else{
			friendrequest=false;
		}
	}
	
	public static void GetFriendRequest(String operation,String AccessToken){
		String s1="requester";
		String s2="acceptUrl";
		String s3="denyUrl";
		String s4="}";
		String requester;
		String acceptUrl;
		String denyUrl;
		int index1,index2,index3,index4;
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken,"");
		System.out.println("GetFriendRequest: "+response);
		index1 = response.indexOf(s1);
		index2 = response.indexOf(s2);
		index3 = response.indexOf(s3);
		index4 = response.indexOf(s4);

		while (index1 >= 0) {
			requester=response.substring(index1+12, index2-3);
			acceptUrl=response.substring(index2+12, index3-3);
			denyUrl=response.substring(index3+10, index4-1);
		    Object[] options = {"Cancel","Reject","Accept"};
		    int n = JOptionPane.showOptionDialog(null,requester+" sent you a friend request", "New friend request", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,null);
		    switch(n){
            case 0:                 
                   break;
            case 1:
        		   response = excuteGET(denyUrl,"");
        		   operation="friendRequestList-request?";
          		   CheckFriendRequest(operation,AccessToken);
        		   operation="friendList-request?";
               	   GetFriendList(operation, AccessToken);
               	   MainLayout.MainpageCl.show(MainLayout.MainUppage, "FriendList");

                   break;
            case 2:
        		   response = excuteGET(acceptUrl,"");
        		   operation="friendRequestList-request?";
          		   CheckFriendRequest(operation,AccessToken);
        		   operation="friendList-request?";
               	   GetFriendList(operation, AccessToken);
               	   MainLayout.MainpageCl.show(MainLayout.MainUppage, "FriendList");
                   break;
            }
		    index1 = response.indexOf(s1, index1 + 20);
		    index2 = response.indexOf(s2, index2 + 20);
		    index3 = response.indexOf(s3, index3 + 20);
			index4 = response.indexOf(s4, index4 + 20);
		}
	}
	
	public static void Register(String operation,String username,String password) {
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"username="+username+"&password="+password,"");
		System.out.println("Register: "+response);
		  if(response.indexOf("true")!=-1){
      		  JOptionPane.showMessageDialog(null,"registration successful");
          	  MainFrame.cl.show(MainFrame.panelCont, "Log");
          	  
		  }
		  else{
      		  JOptionPane.showMessageDialog(null,"registration failed");

		  }
	}
	

	public static String excutePost(String targetURL, String urlParameters) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.close();

			// Get Response
			java.io.InputStream is =  connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	public static String excuteGET(String targetURL, String urlParameters) {
		HttpURLConnection connection = null;
		try {
			URL obj = new URL(targetURL);
			connection = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			connection.setRequestMethod("GET");

			//add request header

			int responseCode = connection.getResponseCode();
//			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
	}
	
	public static String[] parseFriendList(String s) {
		String[] friendList;
		Pattern p = Pattern.compile("\"friendList\":\\[(.+)\\]");
		Matcher m = p.matcher(s);
		String friendNameList ="";
		if(m.find()) {
			friendNameList = m.group(1);
		}
		friendList = friendNameList.split(",");
		for (int i = 0; i < friendList.length; i++) {
			String name = friendList[i];
			if (name.length() > 0) {
				friendList[i] = (name.substring(1,  name.length() - 1));
			}
		}
		return friendList;
	}
	

	//--------------------------------ProfileFunction------------------------------------	
	public static void ModifyPassword(String operation,String AccessToken,String newpassword){
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken+"&password="+newpassword,"");
		System.out.println("ModifyPassword: "+response);
		if(response.indexOf("true")!=-1){
//			JOptionPane.showMessageDialog(null,"modification successful");
		}
		else if(response.indexOf("New password is the same as old password.")!=-1){
			JOptionPane.showMessageDialog(null,"New password is the same as old password.");
		}
		else{
			JOptionPane.showMessageDialog(null,"modification failed");
		}

	}
	
	public static void ModifyProfile(String operation,String AccessToken,String gender,String phonenumber,String nickname,String iconurl) {
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken+"&gender="+gender+"&phonenumber="+phonenumber+"&nickname="+nickname+"&iconurl="+iconurl,"");
		System.out.println("ModifyProfile: "+response);
	}
	
	//---------------------------------MomentsFunction--------------------------------------
	public static void AddMoments(String operation,String AccessToken,String textURL,String photoURL,String videoURL){
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken+"&textURL="+textURL+"&photoURL="+photoURL+"&videoURL="+videoURL,"");
		System.out.println("AddMoments: "+response);
	}

	public static void GetMoments(String operation,String AccessToken){
		String s1="username";
		String s2="timeStamp";
		String s3="nickName";
		String s4="textUrl";
		String s5="photoUrl";
		String s6="iconUrl";
		String s7="videoUrl";
		String s8="}";
		String username;
		String timeStamp;
		String nickName;
		String textUrl;
		String photoUrl;
		String videoUrl;
		String iconurl;
		int width,height;
		int index1,index2,index3,index4,index5,index6,index7,index8;
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"accessToken="+AccessToken,"");
		System.out.println("GetMoments: "+response);
		index1 = response.indexOf(s1);
		index2 = response.indexOf(s2);
		index3 = response.indexOf(s3);
		index4 = response.indexOf(s4);
		index5 = response.indexOf(s5);
		index6 = response.indexOf(s6);
		index7 = response.indexOf(s7);
		index8 = response.indexOf(s8);

		while (index1 >= 0) {
			username=response.substring(index1+11, index2-3);
			timeStamp=response.substring(index2+12, index3-3);
			nickName=response.substring(index3+11, index4-3);
			textUrl=response.substring(index4+10, index5-3);
			photoUrl=response.substring(index5+11, index6-3);
			iconurl=response.substring(index6+10, index7-3);
			videoUrl=response.substring(index7+11, index8-1);

		    Moments newmoments =new Moments();
		    newmoments.name=username;
		    newmoments.time=timeStamp;
		    newmoments.nickname=nickName;
		    if(textUrl.equals(null)){
		    	newmoments.text=textUrl;
		    }
		    if(photoUrl.equals(null)){
		    	ImageIcon image=new ImageIcon(photoUrl);
				Image img = image.getImage();
				if(image.getIconWidth()>300){
					width=300;
					height = image.getIconHeight()*300/image.getIconWidth();
				}
				else{
					width=image.getIconWidth();
					height=image.getIconHeight();
				}
				BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				g.drawImage(img, 0, 0, width, height, null);
				newmoments.image = new ImageIcon(bi);
		    }
		    if(iconurl.equals(null)){
		    	ImageIcon image=new ImageIcon(iconurl);
				Image img = image.getImage();
//				if(image.getIconWidth()>300){
//					width=300;
//					height = image.getIconHeight()*300/image.getIconWidth();
//				}
//				else{
//					width=image.getIconWidth();
//					height=image.getIconHeight();
//				}
				BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				g.drawImage(img, 0, 0, 50, 50, null);
				newmoments.icon = new ImageIcon(bi);
		    }
		    if(videoUrl.equals(null)){
		    	
		    }
		    moments.add(newmoments);
//			System.out.println(newmoments.name);
//			System.out.println(newmoments.time);
//			System.out.println(newmoments.nickname);
//			System.out.println(newmoments.text);
//			System.out.println(textUrl);
//			System.out.println(photoUrl);
//			System.out.println(iconurl);
//			System.out.println(videoUrl);

		    index1 = response.indexOf(s1, index1 + 1);
		    index2 = response.indexOf(s2, index2 + 1);
		    index3 = response.indexOf(s3, index3 + 1);
			index4 = response.indexOf(s4, index4 + 1);
			index5 = response.indexOf(s5, index5 + 1);
			index6 = response.indexOf(s6, index6 + 1);
			index7 = response.indexOf(s7, index7 + 1);
			index8 = response.indexOf(s8, index8 + 1);
		}
	}
	
}

