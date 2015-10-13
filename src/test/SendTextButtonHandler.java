package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendTextButtonHandler implements ActionListener{

	JTextField MsgField;
	JTextArea ChatArea;
	
	public SendTextButtonHandler(JTextField MsgField, JTextArea ChatArea) {
		// TODO Auto-generated constructor stub
		this.MsgField = MsgField;
		this.ChatArea = ChatArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
    {
   	 if(MsgField.getText().equals("")){}
   	 else{
   		 System.out.println(MsgField.getText());
   		 ChatArea.append("-"+LoginPanel.circleAccessToken+"-" +"\n");
   		 ChatArea.append(MsgField.getText()+"\n");
   		 //MsgField.setText("");
   		 }
    }

}
