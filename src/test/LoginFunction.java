package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import org.omg.CORBA.portable.InputStream;

public class LoginFunction {
	public static void main(String args[]) {
		
	}
	
	public static void Login(String operation,String username,String password) {
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"username="+username+"&password="+password,"");
		System.out.println(response);
		  if(response.indexOf("true")!=-1){
      		  JOptionPane.showMessageDialog(null,"login successful");
          	  CLayout.cl.show(CLayout.panelCont, "Main");
		  }
		  else{
      		  JOptionPane.showMessageDialog(null,"login failed");
		  }
	}
	
	public static void Test(String operation,String username,String password) {
		String response = excutePost("http://ec2-54-86-38-175.compute-1.amazonaws.com:8080/CircleAuthenticationService/"+operation+"username="+username+"&password="+password,"");
		System.out.println(response);
		  if(response.indexOf("true")!=-1){
      		  JOptionPane.showMessageDialog(null,"registration successful");
          	  CLayout.cl.show(CLayout.panelCont, "Log");
		  }
		  else{
      		  JOptionPane.showMessageDialog(null,"registration failed");

		  }
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

