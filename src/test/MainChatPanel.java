// herron
package test;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

public class MainChatPanel extends JPanel {

	public MainChatPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE, 0.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel CircleChat = new JLabel("Circle");
		CircleChat.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		GridBagConstraints gbc_CircleChat = new GridBagConstraints();
		gbc_CircleChat.weighty = 0.1;
		gbc_CircleChat.weightx = 0.8;
		gbc_CircleChat.gridy = 0;
		gbc_CircleChat.gridx = 1;
		add(CircleChat, gbc_CircleChat);
		
		JLabel AddChatCon = new JLabel("plus");
		GridBagConstraints gbc_AddChatCon = new GridBagConstraints();
		gbc_AddChatCon.weightx = 0.1;
		gbc_AddChatCon.gridy = 0;
		gbc_AddChatCon.gridx = 2;
		add(AddChatCon, gbc_AddChatCon);
		
		JLabel ChatAvatar1= new JLabel("chatAvatar1");
		ChatAvatar1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_ChatAvatar1 = new GridBagConstraints();
		gbc_ChatAvatar1.weighty = 0.2;
		gbc_ChatAvatar1.weightx = 0.05;
		gbc_ChatAvatar1.gridy = 1;
		gbc_ChatAvatar1.gridx = 0;
		add(ChatAvatar1, gbc_ChatAvatar1);
		JLabel ChatLog1= new JLabel("chatlog1");//flag to control listener?
		GridBagConstraints gbc_ChatLog1 = new GridBagConstraints();
		gbc_ChatLog1.weighty = 0.2;
		gbc_ChatLog1.weightx = 0.8;
		gbc_ChatLog1.gridwidth = 2;
		gbc_ChatLog1.gridy = 1;
		gbc_ChatLog1.gridx = 1;
		add(ChatLog1, gbc_ChatLog1);

		JLabel ChatAvatar2= new JLabel("chatAvatar2");
		ChatAvatar2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_ChatAvatar2 = new GridBagConstraints();
		gbc_ChatAvatar2.insets = new Insets(0, 0, 180, 0);
		gbc_ChatAvatar2.weighty = 0.2;
		gbc_ChatAvatar2.weightx = 0.05;
		gbc_ChatAvatar2.gridy = 2;
		gbc_ChatAvatar2.gridx = 0;
		add(ChatAvatar2, gbc_ChatAvatar2);
		JLabel ChatLog2= new JLabel("chatlog2");//flag to control listener?
		GridBagConstraints gbc_ChatLog2 = new GridBagConstraints();
		gbc_ChatLog2.insets = new Insets(0, 0, 180, 0);
		gbc_ChatLog2.weighty = 0.2;
		gbc_ChatLog2.weightx = 0.8;
		gbc_ChatLog2.gridy = 2;
		gbc_ChatLog2.gridwidth = 2;
		gbc_ChatLog2.gridx = 1;
		add(ChatLog2, gbc_ChatLog2);


		
		

	}

}
