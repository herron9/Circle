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

import javax.swing.JFrame;
import javax.swing.JPanel;


public class CLayout {

	public JFrame Circle = new JFrame("Circle");

	JPanel panelCont = new JPanel();
	LoginUI panelLog = new LoginUI();
	RegisterUI panelReg = new RegisterUI();
	MainmenuUI panelMain = new MainmenuUI();
	
	
	CardLayout cl= new CardLayout();

	public CLayout() {
		panelCont.setLayout(cl);
		panelCont.add("Log",panelLog);
		panelCont.add("Reg",panelReg);
		panelCont.add("Main",panelMain);

		panelLog.lblRegister.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	cl.show(panelCont, "Reg");
             }
             public void  mouseExited(MouseEvent e) {
         		panelLog.lblRegister.setForeground(Color.BLUE);
             }
             public void  mouseEntered(MouseEvent e) {
            	 panelLog.setForeground(Color.MAGENTA);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
 

        class FocusHandler extends FocusAdapter{
        	public void focusGained(FocusEvent e) {
        		if(e.getSource()==panelLog.Login_username){
        			if("username".equals(panelLog.Login_username.getText()))
        				panelLog.Login_username.setText("");
        		}
        		if(e.getSource()==panelLog.Login_password){
        			if("password".equals(panelLog.Login_password.getText()))
        				panelLog.Login_password.setText("");
        			    panelLog.Login_password.setEchoChar('*');
        		}
        	}
        	
        	public void focusLost(FocusEvent e) {
        		if(e.getSource()==panelLog.Login_username){
        			if("".equals(panelLog.Login_username.getText()))
        				panelLog.Login_username.setText("username");
        		}
        		if(e.getSource()==panelLog.Login_password){
        			if("".equals(panelLog.Login_password.getText()))
        				panelLog.Login_password.setText("password");
        		}
        	}
        }
        
        panelLog.Login_username.addFocusListener(new FocusHandler());
        panelLog.Login_password.addFocusListener(new FocusHandler());
	
		cl.show(panelCont, "Log");
		
		panelLog.LogButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				cl.show(panelCont, "Main");
			}
		});
		
		panelReg.RegButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				cl.show(panelCont, "Log");
			}
		});
		
		Circle.getContentPane().add(panelCont);
		Circle.setBounds(100, 100, 600, 450);
		Circle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

	//----删除login组件并添加register组件--------------
/*	private void AddReg() {*/
/*		panelLog.removeAll();	//清除login组件
		
		

		panelReg.add(Login_username);
		panelReg.add(Login_password);
		panelReg.add(Tip);
		panelReg.setLayout(null);
		RegButton.setBounds(240, 300, 120, 40);
		panelReg.add(RegButton);
		
		Login_username.setText("email address");
		Login_password.setText("password");
		Login_password.setEchoChar((char) (0));
		
	}*/

}
