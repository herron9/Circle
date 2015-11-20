package test;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Enumeration;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ProfilePanel extends JPanel {
	
	Font font = new Font("Lucida Grande", Font.PLAIN, 18);
	public static ImageIcon User;//cant scale!
	public static JLabel UserIcon=new JLabel(User);
	static JLabel Userid = new JLabel("New label");
	public static JRadioButton RadioBtnM;
	public static JRadioButton RadioBtnF;
	public static JRadioButton RadioBtnU;
	public static JTextField phoneField;
	public static JPasswordField pwField;
	public JButton EditBtn;
	public JButton EditPWBtn;
	public JButton LogoutBtn;
	public static String newgender;
	public static String newphone;
	public static String newnickname=null;
	public static String newiconurl=null;
	public static String newpw;
	public JLabel valid1;
	public JLabel valid2;
	
	public ProfilePanel() {
		RadioBtnM = new JRadioButton("Male");
	    RadioBtnF = new JRadioButton("Female");
	    RadioBtnU = new JRadioButton("Keep Secret");
	    phoneField = new JTextField();
	    pwField = new JPasswordField();
	    
//		if (gender.equals("Male")) {
//			RadioBtnM.setSelected(true);
//		}
//		else if (gender.equals("Female")) {
//			RadioBtnF.setSelected(true);
//		}
//		else {
//			RadioBtnU.setSelected(true);
//		}
//		newiconurl=Iconurl;
//		newgender=gender;
//		newphone=phone;
//		User=new ImageIcon(Iconurl);
//		UserIcon= new JLabel(User);
//		phoneField.setText(phone);
//		pwField.setText(null);
		
        UIManager.put("Button.font", font); 
        UIManager.put("Label.font", font);
        UIManager.put("RadioButton.font", font);
        
//        System.out.println(LoginPanel.circleAccessToken);
       	
        valid1 = new JLabel(" ");
        valid1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        valid2 = new JLabel(" ");
        valid2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        //EditBtn.setPreferredSize(new Dimension(200, 50));
        EditBtn = new JButton("   Edit Profile  ");
        //EditPWBtn.setPreferredSize(new Dimension(200, 50));
        EditPWBtn = new JButton("Edit Password");
        LogoutBtn = new JButton("Log out");
        LogoutBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//MainFrame mainFrame = new MainFrame();
        	    //mainFrame.cl.show(mainFrame.panelCont, "Log");
        	    //MainLayout.MCPanel.MCInter.removeAll();
        		//MainLayout.getContentPane().removeAll();
        	}
        });
        setPreferredSize(new Dimension(600, 400));
        setProfile();
        
        EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (EditBtn.getText().equals("   Edit Profile  ")) {
					RadioBtnM.setEnabled(true);
					RadioBtnF.setEnabled(true);
					RadioBtnU.setEnabled(true);
					phoneField.setEditable(true);
					EditPWBtn.setEnabled(false);
					EditBtn.setText("Confirm");
					return;
				}
				if (EditBtn.getText().equals("Confirm")) {
					EditPWBtn.setEnabled(true);
					String x = phoneField.getText();
					String y = pwField.getText();
					boolean isDigit = isNumeric(x);//x.matches("\\d{1}");
					boolean isChar =isLetterDigit(y);// y.matches("[a-zA-z]{1}");
					
//					if (isDigit&&isChar&&!y.isEmpty()) {
						
					if (RadioBtnM.isSelected()) {
						newgender = "Male";
					}
					if (RadioBtnF.isSelected()) {
						newgender = "Female";
					}
					if (RadioBtnU.isSelected()) {   //get gender
						newgender = "keep secret";
					}
						newphone = phoneField.getText(); //get phone
						RadioBtnM.setEnabled(false);
						RadioBtnF.setEnabled(false);
						RadioBtnU.setEnabled(false);
						phoneField.setEditable(false);
						pwField.setEditable(false);
						EditBtn.setText("   Edit Profile  ");
						valid1.setText(" ");
						valid2.setText(" ");
						LoginPanel.operation="modify-user-profile?";
						LoginFunction.ModifyProfile(LoginPanel.operation, LoginFunction.AccessToken, newgender, newphone,newnickname,newiconurl);
						LoginPanel.operation="get-user-profile?";
						LoginFunction.GetProfile(LoginPanel.operation, LoginFunction.AccessToken);
			    		setInfo(LoginFunction.Gender,LoginFunction.Phonenumber,LoginFunction.Iconurl);
			        	MainLayout.MainpageCl.show(MainLayout.MainUppage, "ProPanel");
						return;
					}
					else {
						return;				
					}
					
//				}
				
			}
		});
        EditPWBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (EditPWBtn.getText().equals("Edit Password")) {
					pwField.setEditable(true);
					EditBtn.setEnabled(false);
					EditPWBtn.setText("Confirm");
					
					return;
				}
				if (EditPWBtn.getText().equals("Confirm")) {
					EditBtn.setEnabled(true); 
					String x = phoneField.getText();
					String y = pwField.getText();
					boolean isDigit = isNumeric(x);//x.matches("\\d{1}");
					boolean isChar =isLetterDigit(y);// y.matches("[a-zA-z]{1}");
					
					if (isDigit&&isChar&&!y.isEmpty()) {    
						pwField.setEditable(false);
						EditPWBtn.setText("Edit Password");
						valid1.setText(" ");
						valid2.setText(" ");
						LoginPanel.operation="modify-password?";
						LoginFunction.ModifyPassword(LoginPanel.operation, LoginFunction.AccessToken, newpw);
			    		setInfo(LoginFunction.Gender,LoginFunction.Phonenumber,LoginFunction.Iconurl);
			        	MainLayout.MainpageCl.show(MainLayout.MainUppage, "ProPanel");
						return;
					}
					else {
//						if (isDigit == false) {
//							valid1.setText("Must Contain Digits Only");
//						}
//						if (isChar == false) {
//							valid2.setText("Must Contain Letters And Digits Only");
//						}
						if (y.isEmpty()) {
							valid2.setText("Cannot Leave this Empty");
							
						}
						return;				
					}
					
				}
				
			}
		});
		
	}
	
	public static void setInfo(String gender,String phone,String Iconurl) {
//		MainLayout.panelPro.removeAll();
//		MainLayout.panelPro=new ProfilePanel();
		if (gender.equals("Male")) {
			RadioBtnM.setSelected(true);
		}
		else if (gender.equals("Female")) {
			RadioBtnF.setSelected(true);
		}
		else {
			RadioBtnU.setSelected(true);
		}
		newiconurl=Iconurl;
		newgender=gender;
		newphone=phone;
		ImageIcon image=new ImageIcon(Iconurl);
		Image img = image.getImage();
		BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 100, 100, null);
		User=new ImageIcon(bi);
		UserIcon.setIcon(User);
		phoneField.setText(phone);
		pwField.setText(null);
		
//		MainLayout.MainUppage.add(MainLayout.panelPro,"ProPanel");
	}
	public static void getUserID(String id) {
		Userid.setText(id);
	}
	
	public void setProfile() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{80, 120, 70, 70, 70, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 60, 60, 0, 40,35, 0,35, 0, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
			
		GridBagConstraints gbc_UserIcon = new GridBagConstraints();
		gbc_UserIcon.anchor = GridBagConstraints.WEST;
		gbc_UserIcon.insets = new Insets(5, 5, 5, 5);
		gbc_UserIcon.gridheight = 2;
		gbc_UserIcon.gridx = 1;
		gbc_UserIcon.gridy = 1;
		add(UserIcon, gbc_UserIcon);
		UserIcon.addMouseListener(new MouseListener(){
            public void  mouseClicked(MouseEvent e) {
    			String filePath =SwingFileChooserDemo.chooseAFileFromCurrentMachine();
    			newiconurl=filePath;
    			if(filePath==null){
    				
    			}
    			else{
    				LoginPanel.operation="modify-user-profile?";
    				LoginFunction.ModifyProfile(LoginPanel.operation, LoginFunction.AccessToken, newgender, newphone,newnickname,newiconurl);
    				LoginPanel.operation="get-user-profile?";
    				LoginFunction.GetProfile(LoginPanel.operation, LoginFunction.AccessToken);
    	    		setInfo(LoginFunction.Gender,LoginFunction.Phonenumber,LoginFunction.Iconurl);
                	MainLayout.MainpageCl.show(MainLayout.MainUppage, "ProPanel");
//                	ProfilePanel.setInfo(LoginFunction.Gender, LoginFunction.Phonenumber,LoginFunction.Iconurl);
//                	MainLayout.MainpageCl.show(MainLayout.MainUppage, "ProPanel");
    			}
    			
            }
            public void  mouseExited(MouseEvent e) {
         		
            }
            public void  mouseEntered(MouseEvent e) {
            	
            }
            public void  mouseReleased(MouseEvent e) { }
            public void  mousePressed(MouseEvent e) { 
            }
        });
		
		JLabel UserID = new JLabel("UserID:");
		GridBagConstraints gbc_UserID = new GridBagConstraints();
		gbc_UserID.anchor = GridBagConstraints.WEST;
		gbc_UserID.gridwidth = 2;
		gbc_UserID.insets = new Insets(0, 0, 5, 5);
		gbc_UserID.gridx = 2;
		gbc_UserID.gridy = 1;
		add(UserID, gbc_UserID);
		
		
		GridBagConstraints gbc_Userid = new GridBagConstraints();
		gbc_Userid.anchor = GridBagConstraints.NORTHWEST;
		gbc_Userid.gridwidth = 2;
		gbc_Userid.insets = new Insets(0, 0, 5, 5);
		gbc_Userid.gridx = 2;
		gbc_Userid.gridy = 2;
		Userid.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		add(Userid, gbc_Userid);
		
		JLabel GenderLabel = new JLabel("Gender");
		GridBagConstraints gbc_GenderLabel = new GridBagConstraints();
		gbc_GenderLabel.insets = new Insets(0, 0, 5, 5);
		gbc_GenderLabel.gridx = 1;
		gbc_GenderLabel.gridy = 4;
		add(GenderLabel, gbc_GenderLabel);
			
		GridBagConstraints gbc_RadioBtnM = new GridBagConstraints();
		gbc_RadioBtnM.anchor = GridBagConstraints.WEST;
		gbc_RadioBtnM.insets = new Insets(0, 0, 5, 5);
		gbc_RadioBtnM.gridx = 2;
		gbc_RadioBtnM.gridy = 4;
		add(RadioBtnM, gbc_RadioBtnM);
		
		GridBagConstraints gbc_RadioBtnF = new GridBagConstraints();
		gbc_RadioBtnF.anchor = GridBagConstraints.WEST;
		gbc_RadioBtnF.insets = new Insets(0, 0, 5, 5);
		gbc_RadioBtnF.gridx = 3;
		gbc_RadioBtnF.gridy = 4;
		add(RadioBtnF, gbc_RadioBtnF);		

		GridBagConstraints gbc_RadioBtnU = new GridBagConstraints();
		gbc_RadioBtnU.anchor = GridBagConstraints.WEST;
		gbc_RadioBtnU.insets = new Insets(0, 0, 5, 5);
		gbc_RadioBtnU.gridx = 4;
		gbc_RadioBtnU.gridy = 4;
		add(RadioBtnU, gbc_RadioBtnU);
		
		ButtonGroup gender = new ButtonGroup();
		gender.add(RadioBtnM);
		gender.add(RadioBtnF);
		gender.add(RadioBtnU);
//		RadioBtnU.setSelected(true);
		RadioBtnM.setEnabled(false);
		RadioBtnF.setEnabled(false);
		RadioBtnU.setEnabled(false);
		
		
		
		JLabel TelLabel = new JLabel("Phone");
		GridBagConstraints gbc_TelLabel = new GridBagConstraints();
		gbc_TelLabel.ipadx = 6;
		gbc_TelLabel.insets = new Insets(0, 0, 5, 5);
		gbc_TelLabel.gridx = 1;
		gbc_TelLabel.gridy = 5;
		add(TelLabel, gbc_TelLabel);
		
		phoneField.setEditable(false);
		GridBagConstraints gbc_TelEdit = new GridBagConstraints();
		gbc_TelEdit.gridwidth = 3;
		gbc_TelEdit.insets = new Insets(0, 0, 0, 5);
		gbc_TelEdit.fill = GridBagConstraints.BOTH;
		gbc_TelEdit.gridx = 2;
		gbc_TelEdit.gridy = 5;
		add(phoneField, gbc_TelEdit);
		phoneField.setColumns(10);
		
		valid1.setForeground(Color.RED);
		GridBagConstraints gbc_valid1 = new GridBagConstraints();
		gbc_valid1.insets = new Insets(0, 5, 0, 0);
		gbc_valid1.anchor = GridBagConstraints.NORTHWEST;
		gbc_valid1.gridwidth = 3;
		gbc_valid1.gridx = 2;
		gbc_valid1.gridy = 6;
		add(valid1, gbc_valid1);
		
		valid2.setForeground(Color.RED);
		GridBagConstraints gbc_valid2 = new GridBagConstraints();
		gbc_valid2.insets = new Insets(0, 5, 0, 0);
		gbc_valid2.anchor = GridBagConstraints.NORTHWEST;
		gbc_valid2.gridwidth = 3;
		gbc_valid2.gridx = 2;
		gbc_valid2.gridy = 8;
		add(valid2, gbc_valid2);
		
		JLabel PWLabel = new JLabel("Password");
		GridBagConstraints gbc_PWLabel = new GridBagConstraints();
		gbc_PWLabel.ipadx = 22;
		gbc_PWLabel.anchor = GridBagConstraints.EAST;
		gbc_PWLabel.insets = new Insets(0, 0, 5, 5);
		gbc_PWLabel.gridx = 1;
		gbc_PWLabel.gridy = 7;
		add(PWLabel, gbc_PWLabel);
		
		pwField.setEditable(false);
		GridBagConstraints gbc_PWField = new GridBagConstraints();
		gbc_PWField.gridwidth = 3;
		gbc_PWField.insets = new Insets(0, 0, 0, 5);
		gbc_PWField.fill = GridBagConstraints.BOTH;
		gbc_PWField.gridx = 2;
		gbc_PWField.gridy = 7;
		add(pwField, gbc_PWField);
		pwField.setColumns(10);
			
		GridBagConstraints gbc_EditBtn = new GridBagConstraints();
		gbc_EditBtn.fill = GridBagConstraints.VERTICAL;
		gbc_EditBtn.ipadx = 70;
		gbc_EditBtn.ipady = 8;
		gbc_EditBtn.gridwidth = 7;
		gbc_EditBtn.insets = new Insets(0, 0, 5, 0);
		gbc_EditBtn.gridx = 0;
		gbc_EditBtn.gridy = 9;
		add(EditBtn, gbc_EditBtn);
		
		GridBagConstraints gbc_EditPWBtn = new GridBagConstraints();
		gbc_EditPWBtn.fill = GridBagConstraints.VERTICAL;
		gbc_EditPWBtn.ipadx = 70;
		gbc_EditPWBtn.ipady = 8;
		gbc_EditPWBtn.gridwidth = 7;
		gbc_EditPWBtn.gridx = 0;
		gbc_EditPWBtn.gridy = 10;
		add(EditPWBtn, gbc_EditPWBtn);
		
//		GridBagConstraints gbc_logoutBtn = new GridBagConstraints();
//		gbc_logoutBtn.fill = GridBagConstraints.VERTICAL;
//		gbc_logoutBtn.ipadx = 70;
//		gbc_logoutBtn.ipady = 8;
//		gbc_logoutBtn.gridwidth = 7;
//		gbc_logoutBtn.gridx = 0;
//		gbc_logoutBtn.gridy = 10;
//		add(LogoutBtn, gbc_logoutBtn);
	
		
	}

	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
//		   System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	public static boolean isLetterDigit(String str) {
		  String regex = "^[a-z0-9A-Z]+$";
		  return str.matches(regex);
		 }

}
