package test;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegisterPanel extends JPanel {

	private ImageIcon RegLogo = new ImageIcon("bin/Circle_LOGOss.png");//cant scale
	private JLabel RegLogo1 = new JLabel(RegLogo);
	public ImageIcon User = new ImageIcon("bin/avatar.png");//cant scale!
	public JLabel UserIcon = new JLabel(User);
	private JTextField Register_username = new JTextField("email address");
	private JPasswordField Register_password = new JPasswordField("password");;
	public JButton RegButton = new JButton("Register");//switch to main
	JLabel Regtitle = new JLabel("Circle Register");
	JLabel Tip = new JLabel("(more than 6 characters)");

	public RegisterPanel() {
		setBackground(SystemColor.window);
		setLayout(null);
		
		
		add(Regtitle);
		add(RegLogo1);
		add(UserIcon);	
		add(Register_username);
		add(Register_password);
		add(Tip);
		add(RegButton);
		Regtitle.setFont(new Font("Arial",Font.PLAIN, 40));
		Regtitle.setBounds(230, 75, 280, 50);
		RegLogo1.setBounds(140, 60, 80, 80);
		UserIcon.setBounds(130, 176, 100, 100);// need PS
		Register_username.setBounds(240, 180, 220, 35);
		Register_username.setColumns(10);
		Register_password.setEchoChar((char) (0));
		Register_password.setBounds(240, 240,220, 35);
		Tip.setBounds(245, 252, 160, 20);
		RegButton.setBounds(240, 300, 120, 40);
		
		class FocusHandler extends FocusAdapter{
        	public void focusGained(FocusEvent e) {
        		if(e.getSource()==Register_username){
        			if("email address".equals(Register_username.getText()))
        				Register_username.setText("");
        		}
        		if(e.getSource()==Register_password){
        			if("password".equals(Register_password.getText()))
        				Register_password.setText("");
        			    Register_password.setEchoChar('*');
        		}
        		if(("".equals(Register_password.getText()))|("password".equals(Register_password.getText()))){
            	}
            	else{
            		Register_password.setEchoChar('*');
            	}
        	}
        	
        	public void focusLost(FocusEvent e) {
        		if(e.getSource()==Register_username){
        			if("".equals(Register_username.getText()) )
        				Register_username.setText("email address");
        		}
        		if(e.getSource()==Register_password){
        			if("".equals(Register_password.getText()))
        				Register_password.setText("password");
        		}
        		if(("".equals(Register_password.getText()))|("password".equals(Register_password.getText()))){
            	}
            	else{
            		Register_password.setEchoChar('*');
            	}
        	}
        }
        
		Register_username.addFocusListener(new FocusHandler());
        Register_password.addFocusListener(new FocusHandler());
		
		RegButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
            	LoginPanel.operation="create-new-account?";
            	LoginPanel.truefalse=LoginFunction.Test(LoginPanel.operation,Register_username.getText(),Register_password.getText());
            	if(LoginPanel.truefalse==true){
            		JOptionPane.showMessageDialog(null,"registration successful");
            		CLayout.cl.show(CLayout.panelCont, "Log");
            	}
            	else{
            		JOptionPane.showMessageDialog(null,"registration failed");
            	}
            	
            	Register_username.setText("email address");
            	Register_password.setText("password");
            	Register_password.setEchoChar((char) (0));
			}				
		});
		
	}
	//may use later in chatting get userID
	//LoginPanel.Login_username.setText("xxx");
	//= Register_username;
	

}
