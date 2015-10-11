package test;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.UIManager;

import client.CircleClient;
import client.ReceiverHandler;
import communication.Message;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.sun.org.apache.xml.internal.security.utils.ElementCheckerImpl.FullChecker;

import java.awt.Component;
import javax.swing.BoxLayout;

public class ChattingPanel extends JPanel {

	public static JTextArea ChatArea = new JTextArea(16,40);
	public static JScrollPane Scroller = new JScrollPane(ChatArea);
	public static JTextField MsgField = new JTextField(40);
	public static JButton SendMsgBtn = new JButton("Send");
	JPanel South = new JPanel();
	
	
	public ChattingPanel() {
		setBackground(UIManager.getColor("CheckBox.background"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Scroller.setPreferredSize(new Dimension(600, 370));
		ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		ChatArea.setBackground(UIManager.getColor("CheckBox.background"));
		ChatArea.setLineWrap(true);//automatic line feed
		Scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Scroller.setViewportView(ChatArea);
		add(Scroller);
		MsgField.setHorizontalAlignment(SwingConstants.LEFT);
		MsgField.setBackground(UIManager.getColor("Button.highlight"));
		
		FlowLayout flowLayout = (FlowLayout) South.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		South.add(MsgField);
		South.add(SendMsgBtn);
		SendMsgBtn.setPreferredSize(new Dimension(100, 32));
		add(South);
		South.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{MsgField, SendMsgBtn}));
		
		class FocusHandler extends FocusAdapter{
        	public void focusGained(FocusEvent e) {
//        		if(e.getSource()==MsgField){
//        			if("say something".equals(MsgField.getText()))
//        				MsgField.setText("");
//        		}
        	}    	
        	public void focusLost(FocusEvent e) {
//        		if(e.getSource()==MsgField){
//        			if("".equals(MsgField.getText()) )
//        				MsgField.setText("say something");
//        		}
        	}
        }
        
		MsgField.addFocusListener(new FocusHandler());

	}

}

