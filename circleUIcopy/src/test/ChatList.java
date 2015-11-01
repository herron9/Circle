package test;


import java.util.ArrayList;

public class ChatList{
	
	static ArrayList<ChatLog> LogList = new ArrayList<ChatLog>();
	public ChatList() {

	} 
	
	public static void CreateEntry(String fname) {
		boolean isIn = false;
		if(LogList.isEmpty()){
			ChatLog log = new ChatLog(fname);
			LogList.add(log);	
			isIn = true;
		}
		else {
			for (int i = 0; i < LogList.size(); i++) {
				if (fname.equals(LogList.get(i).name)) {
					isIn = true;
				} 
			}	
		}
		if (isIn == true) {
		}
		else {
			ChatLog log = new ChatLog(fname);
			//log.name = name;
			LogList.add(log);	
		}
		
	}
		

	
	public static void DisplayLog(String name, String Time,String LastMsg) {
		MainLayout.MCPanel.MCInter.removeAll();
//		if(LogList.get(0).name.equals(name)){
//			LogList.get(0).Renew(Time,LastMsg);
//			System.out.println(LogList.get(0).TimeLabel);
//			System.out.println(LogList.get(0).HistoryLabel);
//		}	
		for (int i = 0; i < LogList.size(); i++) {
//			System.out.println("T "+Time);
//			System.out.println("M "+LastMsg);
			if(LogList.get(i).name.equals(name)){
				LogList.get(i).Renew(Time,LastMsg);
			}
//			System.out.println(LogList.get(0).TimeLabel.getText());
//			System.out.println(LogList.get(0).HistoryLabel.getText());
			MainLayout.MCPanel.MCInter.add(LogList.get(i));				
		}
		
	}
		
}
