package test;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;


public class CLayout {

	private JFrame Circle = new JFrame("Circle");
	private JLabel title = new JLabel("Circle Login");// login reg page
	private ImageIcon RegLogo = new ImageIcon("src/Circle_LOGOs.png");//cant scale
	private JLabel RegLogo1 = new JLabel(RegLogo);
	public ImageIcon User = new ImageIcon("src/user.png");//cant scale!
	public JLabel UserIcon = new JLabel(User);
	private JTextField Login_username = new JTextField();
	private JLabel lblRegister = new JLabel("Register");
	private JPasswordField Login_password = new JPasswordField();
	private JTextField Register_username;
	private JPasswordField Register_password;
	private JButton LogButton = new JButton("Log in");//switch to main
	//------------LOGIN-----------------------------------------------
	JButton RegButton = new JButton("Register");//switch to main

	
/*	public static ImageIcon getImageIcon(String path, int width, int height) {
		  ImageIcon icon = new ImageIcon();
		  icon.getClass().getResource(path);
		  icon = new ImageIcon(icon.getImage()  
                  .getScaledInstance(width, height, Image.SCALE_DEFAULT));  
		  icon.setImage().getImage().getScaledInstance(width, height,
		    Image.SCALE_DEFAULT);
		  return icon;
		 }*/
	
	JPanel panelCont = new JPanel();
	JPanel panelLog = new JPanel();
	JPanel panelReg = new JPanel();
	JPanel panelMain = new JPanel();
	
	CardLayout cl= new CardLayout();

	public CLayout() {
		panelCont.setLayout(cl);

		panelLog.setLayout(null);
		LogButton.setBounds(240, 300, 120, 40);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panelLog.add(title);
		panelLog.add(RegLogo1);
		panelLog.add(UserIcon);
		panelLog.add(Login_username);
		panelLog.add(Login_password);
		panelLog.add(lblRegister);
		panelLog.add(LogButton);
		
		
		title.setFont(new Font("Arial",Font.PLAIN, 40));
		title.setBounds(230, 75, 220, 50);
		//RegLogo.getImageIcon("src/Circle_LOGOs.png",80,80);
		RegLogo1.setBounds(130, 50, 100, 100);// need PS
		UserIcon.setBounds(130, 156, 100, 100);// need PS
		
		Login_username.setText("username");
		Login_username.setToolTipText("username");
		Login_username.setBounds(240, 160, 220, 35);
		Login_username.setColumns(10);
		
		lblRegister.setForeground(Color.BLUE);
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblRegister.setBounds(470, 160, 80, 35);
		lblRegister.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	cl.show(panelCont, "Reg");
            	AddReg();
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
 
		Login_password.setToolTipText("password");
		Login_password.setEchoChar((char) (0));
		Login_password.setText("password");
		Login_password.setBounds(240, 220,220, 35);

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
        	}
        	
        	public void focusLost(FocusEvent e) {
        		if(e.getSource()==Login_username){
        			if("".equals(Login_username.getText()))
        				Login_username.setText("username");
        		}
        		if(e.getSource()==Login_password){
        			if("".equals(Login_password.getText()))
        				Login_password.setText("password");
        		}
        	}
        }
        
        Login_username.addFocusListener(new FocusHandler());
        Login_password.addFocusListener(new FocusHandler());
        
        //--------Register------------------------------
//        Register_username = new JTextField();
//		Register_username.setText("email");
//		Register_username.setBounds(214, 136, 150, 30);
//		panelReg.add(Register_username);
//		Register_username.setColumns(10);
//		
//		JLabel label = new JLabel("Circle Login");
//		label.setFont(new Font("Arial", Font.PLAIN, 40));
//		label.setBounds(227, 74, 210, 50);
//		panelReg.add(label);
//		
//		Register_password = new JPasswordField();
//		Register_password.setBounds(214, 195, 150, 30);
//		
//		
//		panelReg.add(Register_password);
//		
//		JLabel lblmoreThan = new JLabel("(more than 6 characters)");
//		lblmoreThan.setBounds(214, 225, 160, 20);
//		panelReg.add(lblmoreThan);
        
        panelLog.setBackground(SystemColor.window);
		panelReg.setBackground(SystemColor.window);
		panelMain.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		
		panelCont.add("Log",panelLog);
		panelCont.add("Reg",panelReg);
		panelCont.add("Main",panelMain);

		
		cl.show(panelCont, "Log");
		
		LogButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				cl.show(panelCont, "Main");
			}
		});
		
		RegButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				cl.show(panelCont, "Log");
				panelReg.removeAll();
				panelLog.add(title);
				panelLog.add(RegLogo1);
				panelLog.add(UserIcon);
				panelLog.add(Login_username);
				panelLog.add(Login_password);
				panelLog.add(lblRegister);
				panelLog.add(LogButton);
			}
		});
		
		Circle.getContentPane().add(panelCont);
		Circle.setBounds(100, 100, 600, 450);
		Circle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Circle.pack();
		Circle.setVisible(true);
		Circle.setResizable(false);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CLayout window = new CLayout();
					window.Circle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Initialize the contents of the frame.
	 */
	
	//----删除login组件并添加register组件--------------
	private void AddReg() {
		panelLog.removeAll();	//清除login组件
		
		JLabel Regtitle = new JLabel("Circle Register");
		Regtitle.setFont(new Font("Arial",Font.PLAIN, 40));
		Regtitle.setBounds(230, 75, 280, 50);
		JLabel Tip = new JLabel("(more than 6 characters)");
		Tip.setBounds(245, 252, 160, 20);
		
    	panelReg.add(Regtitle);
		panelReg.add(RegLogo1);
		panelReg.add(Login_username);
		panelReg.add(Login_password);
		panelReg.add(Tip);
		panelReg.setLayout(null);
		RegButton.setBounds(240, 300, 120, 40);
		panelReg.add(RegButton);
		
		Login_username.setText("email address");
		Login_password.setText("password");
		Login_password.setEchoChar((char) (0));
		
	}
	private void initialize() {
		//frame = new JFrame();
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
