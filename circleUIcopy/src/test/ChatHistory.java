package test;

import java.util.ArrayList;

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
}
class MessageRecord extends chatRecord{
	public static String message;
}
class ImageRecord extends chatRecord{
	public static String image;
}