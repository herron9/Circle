package test;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterPanel extends JPanel {

	private ImageIcon RegLogo = new ImageIcon("bin/Circle_LOGOss.png");//cant scale
	private JLabel RegLogo1 = new JLabel(RegLogo);
	public ImageIcon User = new ImageIcon("bin/avatar.png");//cant scale!
	public JLabel UserIcon = new JLabel(User);
	public JLabel lblBack = new JLabel("Back");
	private JTextField Register_username = new JTextField("email address");
	private JPasswordField Register_password = new JPasswordField("password");
	public JButton RegButton = new JButton("Register");//switch to main
	JLabel Regtitle = new JLabel("Circle Register");
	JLabel Tip = new JLabel("(more than 6 characters)");
	boolean validate;
	
	public RegisterPanel() {
		setBackground(SystemColor.window);
		setLayout(null);	
		add(Regtitle);
		add(RegLogo1);
		add(UserIcon);	
		add(lblBack);
		add(Register_username);
		add(Register_password);
		add(Tip);
		add(RegButton);
		Regtitle.setFont(new Font("Arial",Font.PLAIN, 40));
		Regtitle.setBounds(230, 75, 280, 50);
		RegLogo1.setBounds(140, 60, 80, 80);
		UserIcon.setBounds(130, 176, 100, 100);// need PS
		lblBack.setForeground(Color.BLUE);
		lblBack.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblBack.setBounds(470, 180, 80, 35);
		Register_username.setToolTipText("email address");
		Register_username.setBounds(240, 180, 220, 35);
		Register_username.setColumns(10);
		Register_password.setToolTipText("password");
		Register_password.setEchoChar((char) (0));
		Register_password.setBounds(240, 240,220, 35);
		Tip.setBounds(245, 252, 160, 20);
		RegButton.setBounds(240, 300, 120, 40);
		
		lblBack.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	MainFrame.cl.show(MainFrame.panelCont, "Log");
            	Register_username.setText("email address");
            	Register_password.setText("password");
            	Register_password.setEchoChar((char) (0));
             }
             public void  mouseExited(MouseEvent e) {
            	 lblBack.setForeground(Color.BLUE);
             }
             public void  mouseEntered(MouseEvent e) {
            	 lblBack.setForeground(Color.MAGENTA);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
		
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
    					Register_password.setEchoChar((char) (0));
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
				validate=Email(Register_username.getText());
				if(validate==true){
					LoginPanel.operation="create-new-account?";
	            	LoginFunction.Test(LoginPanel.operation,Register_username.getText(),Register_password.getText());
				}
				else{
            		JOptionPane.showMessageDialog(null,"Not a valid email address");
				}
				Register_username.setText("email address");
            	Register_password.setText("password");
            	Register_password.setEchoChar((char) (0));
			}				
		});
		
	}
	public static boolean Email(String email) {

	    boolean validate;
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
	    Matcher mat = pattern.matcher(email);

	    if(mat.matches()){
	    	validate=true;
	    }else{
	    	validate=false;
	    }
	    return validate;
	}
}

