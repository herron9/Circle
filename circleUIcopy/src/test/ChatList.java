package test;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.CircleClient;

public class ChatList{
	ArrayList<ChatLog> LogList = new ArrayList<ChatLog>();
	public ChatList() {
//		create
//		add
		// TODO Auto-generated constructor stub
	} 
	
	public void CreateEntry(String name) {
		ChatLog log = new ChatLog();
		log.name = name;
		LogList.add(log);
	}
	
	public void DisplayLog() {
		MainLayout.MCPanel.removeAll();
		//int length = LogList.size();
		for (int i = 0; i < LogList.size(); i++) {
			//LogList.get(i)	
			MainLayout.MCPanel.add(LogList.get(i));				
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
