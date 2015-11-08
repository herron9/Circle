package test;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Enumeration;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ProfilePanel extends JPanel {
	
	Font font = new Font("Lucida Grande", Font.PLAIN, 18);
	ImageIcon User = new ImageIcon("src/avatar.png");//cant scale!
	JLabel UserIcon = new JLabel(User);
	static JLabel Userid = new JLabel("New label");
	public JRadioButton RadioBtnM;
	public JRadioButton RadioBtnF;
	public JRadioButton RadioBtnU;
	public JTextField phoneField;
	public JTextField pwField;
	public JButton EditBtn;
	public JButton LogoutBtn;
	public String newgender;
	public String newphone;
	public String newnickname;
	public String newiconurl;
	public String newpw;
	public JLabel valid1;
	public JLabel valid2;
	
	public ProfilePanel() {
		
        UIManager.put("Button.font", font); 
        UIManager.put("Label.font", font);
        UIManager.put("RadioButton.font", font);
        
        System.out.println(LoginPanel.circleAccessToken);
        RadioBtnM = new JRadioButton("Male");
        RadioBtnF = new JRadioButton("Female");
        RadioBtnU = new JRadioButton("Keep Secret");
        phoneField = new JTextField();
        pwField = new JTextField();		
        valid1 = new JLabel(" ");
        valid1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        valid2 = new JLabel(" ");
        valid2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        EditBtn = new JButton("   Edit   ");
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
				if (EditBtn.getText().equals("   Edit   ")) {
					RadioBtnM.setEnabled(true);
					RadioBtnF.setEnabled(true);
					RadioBtnU.setEnabled(true);
					phoneField.setEditable(true);
					pwField.setEditable(true);
					EditBtn.setText("Confirm");
					return;
				}
				if (EditBtn.getText().equals("Confirm")) {
					String x = phoneField.getText();
					String y = pwField.getText();
					boolean isDigit = isNumeric(x);//x.matches("\\d{1}");
					boolean isChar =isLetterDigit(y);// y.matches("[a-zA-z]{1}");
					
					if (isDigit&&isChar&&!y.isEmpty()) {
						
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
						newpw = pwField.getText();   //get password
						RadioBtnM.setEnabled(false);
						RadioBtnF.setEnabled(false);
						RadioBtnU.setEnabled(false);
						phoneField.setEditable(false);
						pwField.setEditable(false);
						EditBtn.setText("   Edit   ");
						valid1.setText(" ");
						valid2.setText(" ");
						LoginPanel.operation="modify-user-profile?";
						LoginFunction.ModifyProfile(LoginPanel.operation, LoginFunction.AccessToken, newgender, newphone,newnickname,newiconurl);
						return;
					}
					else {
						if (isDigit == false) {
							valid1.setText("Must Contain Digits Only");
						}
						if (isChar == false) {
							valid2.setText("Must Contain Letters And Digits Only");
						}
						if (y.isEmpty()) {
							valid2.setText("Cannot Leave this Empty");
							
						}
						return;				
					}
					
				}
				
			}
		});
        
		
	}
	
//	public void setInfo(String id,String gender,String phone,String pw) {
//		//Userid.setText(id);
//		if (condition) {
//			RadioBtnM.setSelected(true);
//		}
//		if (condition) {
//			RadioBtnF.setSelected(true);
//		}
//		if (condition) {
//			RadioBtnU.setSelected(true);
//		}
//		phoneField.setText(phone);
//		pwField.setText(pw);
//		
//	}
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
		RadioBtnU.setSelected(true);
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
		
		GridBagConstraints gbc_logoutBtn = new GridBagConstraints();
		gbc_logoutBtn.fill = GridBagConstraints.VERTICAL;
		gbc_logoutBtn.ipadx = 70;
		gbc_logoutBtn.ipady = 8;
		gbc_logoutBtn.gridwidth = 7;
		gbc_logoutBtn.gridx = 0;
		gbc_logoutBtn.gridy = 10;
		add(LogoutBtn, gbc_logoutBtn);
	
		
	}

	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   System.out.println(str.charAt(i));
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
