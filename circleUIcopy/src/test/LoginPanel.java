package test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import client.CircleClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class LoginPanel extends JPanel {
	private JLabel title = new JLabel("Circle Login");// login reg page
	private ImageIcon RegLogo = new ImageIcon("src/Circle_LOGOss.png");//cant scale
	private JLabel RegLogo1 = new JLabel(RegLogo);
	public ImageIcon User = new ImageIcon("src/avatar.png");//cant scale!
	public JLabel UserIcon = new JLabel(User);
	
	public static JTextField Login_username = new JTextField();
	public static JPasswordField Login_password = new JPasswordField();
	public JLabel lblRegister = new JLabel("Register");
	public JButton LogButton = new JButton("Log in");//switch to main
	
	public static String operation;
	public static String circleAccessToken;
	public static String AccessToken;	//accesstoken
	public static String[] names={""};
		
	public LoginPanel() {
		setBackground(SystemColor.window);
		setLayout(null);
		
		add(title);
		add(RegLogo1);
		add(UserIcon);		
		add(Login_username);
		add(Login_password);
		add(lblRegister);
		add(LogButton);
				
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Arial",Font.PLAIN, 40));
		title.setBounds(230, 75, 220, 50);
		RegLogo1.setBounds(140, 60, 80, 80);
		UserIcon.setBounds(130, 176, 100, 100);// need PS
		LogButton.setBounds(240, 300, 120, 40);
		
		Login_username.setText("username");
		Login_username.setToolTipText("username");
		Login_username.setBounds(240, 180, 220, 35);
		Login_username.setColumns(10);
		
		lblRegister.setForeground(Color.BLUE);
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblRegister.setBounds(470, 180, 80, 35);

		Login_password.setToolTipText("password");
		Login_password.setEchoChar((char) (0));
		Login_password.setText("password");
		Login_password.setBounds(240, 240,220, 35);
		
		
		
		class FocusHandler extends FocusAdapter{
        	public void focusGained(FocusEvent e) {
        		if(e.getSource()==Login_username){
        			if("username".equals(Login_username.getText()))
        				Login_username.setText("");
        		}
        		if(e.getSource()==Login_password){
        			if("password".equals(Login_password.getText()))
        				Login_password.setText("");
        			    Login_password.setEchoChar('*');
        		}
        		if(("".equals(Login_password.getText()))|("password".equals(Login_password.getText()))){
            	}
            	else{
            		Login_password.setEchoChar('*');
            	}

        	}
        	
        	public void focusLost(FocusEvent e) {
        		if(e.getSource()==Login_username){
        			if("".equals(Login_username.getText()))
        				Login_username.setText("username");
        		}
        		if(e.getSource()==Login_password){
        			if("".equals(Login_password.getText()))
        				Login_password.setText("password");
        				Login_password.setEchoChar((char) (0));

        		}
        		if(("".equals(Login_password.getText()))|("password".equals(Login_password.getText()))){
            	}
            	else{
            		Login_password.setEchoChar('*');
            	}

        	}
        }
        
        Login_username.addFocusListener(new FocusHandler());
        Login_password.addFocusListener(new FocusHandler());
        
        lblRegister.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	MainFrame.cl.show(MainFrame.panelCont, "Reg");
            	Login_username.setText("username");
            	Login_password.setText("password");
            	Login_password.setEchoChar((char) (0));
             }
             public void  mouseExited(MouseEvent e) {
         		lblRegister.setForeground(Color.BLUE);
             }
             public void  mouseEntered(MouseEvent e) {
            	lblRegister.setForeground(Color.MAGENTA);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
        
        LogButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				operation="sign-in?";
				char[] pass = LoginPanel.Login_password.getPassword();
				String password = new String(pass);
            	LoginFunction.Login(operation,Login_username.getText(),password);

				Login_username.setText("username");
            	Login_password.setText("password");
            	Login_password.setEchoChar((char) (0));
			}
		});
        
       
	}	
	
	

}
