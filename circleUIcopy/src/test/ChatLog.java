package test;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ChatLog extends JPanel {

	public ImageIcon User = new ImageIcon("src/avatar.png");//cant scale!
	public JLabel UserIcon;
	JPanel Right = new JPanel();
	JLabel NameLabel =new JLabel();
	JLabel TimeLabel = new JLabel();                                                             
	JLabel HistoryLabel = new JLabel();
	String name = "null";
	String user =  LoginPanel.circleAccessToken;
	
	static boolean flag =true;

	
	public ChatLog(String name) {
		this.name = name;
//		System.out.println("chatlog.this.name:  "+this.name);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean find=false;
        		for(int i=0;i<LoginFunction.receiver.size();i++){
        			
            		if(ChatLog.this.name.equals(LoginFunction.receiver.get(i).friendname)){
            			find=true;
            		}
        		}
            		if(find==true){
                		ClientFunction.RecallChatting(ChatLog.this.name);
                	}
                	else{
                    	ClientFunction.CreateChatting(ChatLog.this.name);
                	}
            	
//			ClientFunction.RecallChatting(ChatLog.this.name);
			}
		});

		setPreferredSize(new Dimension(590, 60));
		//setSize(new Dimension(600, 40));
		setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setup();
		UserIcon.setIcon(ClientFunction.resizeIcon(ClientFunction.ID2icon(name),60,60));
//		UserIcon.setIcon(ClientFunction.ID2icon(name));
		NameLabel.setText(ClientFunction.ID2Nick(name));
		HistoryLabel.setPreferredSize(new Dimension(600, 60));


	}
	
	public void setup() {
		UserIcon = new JLabel();	
		add(UserIcon);
		GridBagLayout gbl_Right = new GridBagLayout();
		gbl_Right.columnWidths = new int[]{200, 0};
		gbl_Right.rowHeights = new int[]{16, 16, 1, 0};
		gbl_Right.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_Right.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		Right.setLayout(gbl_Right);
		GridBagConstraints gbc_NameLabel = new GridBagConstraints();
		gbc_NameLabel.anchor = GridBagConstraints.WEST;
		gbc_NameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_NameLabel.gridx = 0;
		gbc_NameLabel.gridy = 0;
		Right.add(NameLabel, gbc_NameLabel);
		GridBagConstraints gbc_TimeLabel = new GridBagConstraints();
		gbc_TimeLabel.gridy = 0;
		gbc_TimeLabel.gridx = 1;
		gbc_NameLabel.anchor = GridBagConstraints.WEST;
		gbc_NameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_NameLabel.gridx = 1;
		gbc_NameLabel.gridy = 0;
		Right.add(TimeLabel, gbc_TimeLabel);
		GridBagConstraints gbc_HistoryLabel = new GridBagConstraints();
		gbc_HistoryLabel.anchor = GridBagConstraints.WEST;
		gbc_HistoryLabel.gridx = 0;
		gbc_HistoryLabel.gridy = 1;
		gbc_HistoryLabel.gridwidth=2;
		gbc_HistoryLabel.ipady = 10;
		Right.add(HistoryLabel, gbc_HistoryLabel);
		add(Right);
	
	}
	
//	public void Renew(String Time,String LastMsg) {
//		TimeLabel.setText(Time);
//		SetHistory(LastMsg);
//		
//		
//	}

	public void SetHistory(String LastMsg){
		if (LastMsg.length() > 70) {
			LastMsg = LastMsg.substring(0, 70) ;
			HistoryLabel.setText(LastMsg + "...");
		}
		else {
			HistoryLabel.setText(LastMsg);
		}
		
	}

}
