package test;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;


import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import client.CircleClient;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.text.DefaultCaret;

public class ChattingPanel extends JPanel {

	public JTextArea ChatArea = new JTextArea(16,40);
	public JScrollPane Scroller = new JScrollPane(ChatArea);//add chatarea to scrollarea
	public JTextField MsgField = new JTextField(40);
	public JButton SendMsgBtn = new JButton("Send");
	JPanel South = new JPanel();
	String DesID = "null";
	
	
	public ChattingPanel(CircleClient client) {
		
		
		
		setBackground(UIManager.getColor("CheckBox.background"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel AddFriend = new JLabel("Picture");
		GridBagConstraints gbc_AddFriend = new GridBagConstraints();
		gbc_AddFriend.insets = new Insets(0, 0, 5, 0);
		gbc_AddFriend.weightx = 0.1;
		gbc_AddFriend.gridy = 0;
		gbc_AddFriend.gridx = 5;
		add(AddFriend, gbc_AddFriend);
		
		AddFriend.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	s3Repository s3= new s3Repository();
        		String key = ""+UUID.randomUUID()+".jpg";
        		String filePath =SwingFileChooserDemo.chooseAFileFromCurrentMachine();
        		s3.uploadFile(key, filePath);
             }
             public void  mouseExited(MouseEvent e) {
             }
             public void  mouseEntered(MouseEvent e) {            	
             }
             public void  mouseReleased(MouseEvent e) {            	 
             }
             public void  mousePressed(MouseEvent e) { 
             }
         });

		Scroller.setPreferredSize(new Dimension(600, 370));
		ChatArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		ChatArea.setBackground(UIManager.getColor("CheckBox.background"));
		ChatArea.setLineWrap(true);//automatic line feed
		ChatArea.setEditable(false);
		Scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DefaultCaret caret = (DefaultCaret)ChatArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		Scroller.setViewportView(ChatArea);//set sroller to the component u want to scroll
		add(Scroller);//add scroller to panel, not the textarea
		MsgField.setHorizontalAlignment(SwingConstants.LEFT);
		MsgField.setBackground(UIManager.getColor("Button.highlight"));
		
		FlowLayout flowLayout = (FlowLayout) South.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		South.add(MsgField);
		South.add(SendMsgBtn);
		SendMsgBtn.addActionListener(new SendTextButtonHandler(ChatArea,MsgField,client,null));
		SendMsgBtn.setPreferredSize(new Dimension(100, 32));
		add(South);
		South.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{MsgField, SendMsgBtn}));

	}

}

