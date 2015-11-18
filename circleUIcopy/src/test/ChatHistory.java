package test;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ChatHistory {
	
	public String friendname;
	public ArrayList<chatRecord> history=new ArrayList<>();
	
	public static void AddHistory() {
		
	}
}
class chatRecord{
	public String time=null;
	public String sourceID=null;
	public String message=null;
	public ImageIcon image=null;
	public int type;
}
class MessageRecord extends chatRecord{
	public static String message;
}
class ImageRecord extends chatRecord{
	public static String image;
}