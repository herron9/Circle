package test;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.omg.CORBA.portable.InputStream;


public class CLayout {

	public JFrame Circle = new JFrame("Circle");
	public static String operation;
	boolean truefalse;

	JPanel panelCont = new JPanel();
	LoginPanel panelLog = new LoginPanel();
	RegisterPanel panelReg = new RegisterPanel();
	MainLayout panelMain = new MainLayout();
	
	
	CardLayout cl= new CardLayout();

	public CLayout() {
		panelCont.setLayout(cl);
		panelCont.add("Log",panelLog);
		panelCont.add("Reg",panelReg);
		panelCont.add("Main",panelMain);

		panelLog.lblRegister.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
            	cl.show(panelCont, "Reg");
            	panelLog.Login_username.setText("username");
            	panelLog.Login_password.setText("password");
             }
             public void  mouseExited(MouseEvent e) {
         		panelLog.lblRegister.setForeground(Color.BLUE);
             }
             public void  mouseEntered(MouseEvent e) {
            	 panelLog.lblRegister.setForeground(Color.MAGENTA);
            	
             }
             public void  mouseReleased(MouseEvent e) { }
             public void  mousePressed(MouseEvent e) { 
             }
         });
 
		cl.show(panelCont, "Log");
		
		panelLog.LogButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				cl.show(panelCont, "Main");
			}
		});
		
		panelReg.RegButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
            	operation="create-new-account?";
            	truefalse=Test(operation,panelReg.Textfield2.Login_username.getText(),panelReg.Textfield2.Login_password.getText());
            	if(truefalse==true){
            		JOptionPane.showMessageDialog(null,"注册成功");
            		cl.show(panelCont, "Log");
            	}
            	else{
            		JOptionPane.showMessageDialog(null,"注册失败");
            	}
				panelReg.Textfield2.Login_username.setText("email address");
            	panelReg.Textfield2.Login_password.setText("password");
            	panelReg.Textfield2.Login_password.setEchoChar((char) (0));
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
	public static boolean Test(String operation,String username,String password) {
		boolean register;
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"username="+username+"&password="+password,"");
		System.out.println(response);
		  if(response.indexOf("true")!=-1){
			  register=true;
		  }
		  else{
			  register=false;
		  }
		return register;
	}

	public static String excutePost(String targetURL, String urlParameters) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.close();

			// Get Response
			java.io.InputStream is =  connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}