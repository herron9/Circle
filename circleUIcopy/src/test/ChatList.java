package test;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.CircleClient;
import communication.Message;


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
		

	
	public static void DisplayLog() {
		MainLayout.MCPanel.MCInter.removeAll();
		//int length = LogList.size();
		for (int i = 0; i < LogList.size(); i++) {
			//LogList.get(i).TimeLabel=  
			//need integrate with history
			MainLayout.MCPanel.MCInter.add(LogList.get(i));				
		}
		
	}
		
}

 
//public class BoxLayoutDemo {
//    public static void addComponentsToPane(Container pane) {
//        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
// 
//        addAButton("Button 1", pane);
//        addAButton("Button 2", pane);
//        addAButton("Button 3", pane);
//        addAButton("Long-Named Button 4", pane);
//        addAButton("5", pane);
//    }
// 
//    private static void addAButton(String text, Container container) {
//        JButton button = new JButton(text);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT);
//        container.add(button);
//    }
// 
//
//	// add your JPanel object like this way
//	panelList.add(yourPanel);
//
//	// retrieve your JPanel object from list
//	JPanel panel = panelList.get(index);

//}
