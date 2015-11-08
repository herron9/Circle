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

		for (int i = 0; i < LogList.size(); i++) {
			if(LogList.get(i).name.equals(name)){	    
				LogList.get(i).TimeLabel.setText(Time);
				LogList.get(i).SetHistory(LastMsg);
			}

			MainLayout.MCPanel.MCInter.add(LogList.get(i));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.add(new ChatLog(null));
			MainLayout.MCPanel.MCInter.revalidate();
			MainLayout.MCPanel.MCInter.repaint();
		}
		
	}
		
}
