package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;


public class AddMoments extends JFrame {
	//JFrame MomentFrame = new JFrame("New Moment");
	JPanel NewMoment = new JPanel();
	JTextArea Moment = new JTextArea("say something :-D");
	JScrollPane scrollPane = new JScrollPane(Moment);
	private final JLabel lblCancel = new JLabel("Cancel");
	private final JLabel lblPicture = new JLabel("Picture");
	private final JLabel lblSend = new JLabel("Send");
	
	String fileurlx = null;
	public AddMoments() {
		setResizable(false); 
		
		Moment.setLineWrap(true);
		lblCancel.setVerticalAlignment(SwingConstants.TOP);
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblCancel.setOpaque(true);
		lblPicture.setOpaque(true);
		lblSend.setOpaque(true);
		setTitle("New Moment");
		setBounds(200, 200, 300, 200);
		getContentPane().add(NewMoment);
		GridBagLayout gbl_bottom = new GridBagLayout();
		gbl_bottom.columnWidths = new int[]{80,140,80};
		gbl_bottom.rowHeights = new int[]{165, 35};
		gbl_bottom.columnWeights = new double[]{0.0, 0.0};
		gbl_bottom.rowWeights = new double[]{0.0, 0.0};
		NewMoment.setLayout(gbl_bottom);
		

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		NewMoment.add(scrollPane, gbc_scrollPane);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GridBagConstraints gbc_lblCancel = new GridBagConstraints();
		gbc_lblCancel.fill = GridBagConstraints.BOTH;
		gbc_lblCancel.insets = new Insets(0, 0, 0, 5);
		gbc_lblCancel.gridx = 0;
		gbc_lblCancel.gridy = 1;
		//lblCancel.setBackground(new Color(220, 220, 220));
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancel.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancel.setBackground(null);
				 
			}
		});
		NewMoment.add(lblCancel, gbc_lblCancel);
		
		GridBagConstraints gbc_lblPicture = new GridBagConstraints();
		gbc_lblPicture.fill = GridBagConstraints.BOTH;
		gbc_lblPicture.insets = new Insets(0, 0, 0, 5);
		gbc_lblPicture.gridx = 1;
		gbc_lblPicture.gridy = 1;
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setVerticalAlignment(SwingConstants.TOP);
		NewMoment.add(lblPicture, gbc_lblPicture);
		lblPicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fileurl =null;
				String filePath=null;
				filePath = SwingFileChooserDemo.chooseAFileFromCurrentMachine();;
				
				if(filePath!=null){
					s3Repository s3= new s3Repository();
					String key = ""+UUID.randomUUID()+".jpg";
					s3.uploadFile(key,filePath);
					fileurl="https://s3.amazonaws.com/circleuserfiles/"+key;
					lblPicture.setText("Picture added");
					fileurlx=fileurl;
					fileurl=null;
					filePath=null;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPicture.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPicture.setBackground(null);
			}
		});
		
		GridBagConstraints gbc_lblSend = new GridBagConstraints();
		gbc_lblSend.fill = GridBagConstraints.BOTH;
		gbc_lblSend.gridx = 2;
		gbc_lblSend.gridy = 1;
		lblSend.setHorizontalAlignment(SwingConstants.CENTER);
		lblSend.setVerticalAlignment(SwingConstants.TOP);
//		lblSend.setBackground(new Color(220, 220, 220));
		NewMoment.add(lblSend, gbc_lblSend);
		lblSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPanel.operation="add-moments-owners-personal-record?";
				LoginFunction.AddMoments(LoginPanel.operation, LoginFunction.AccessToken, Moment.getText(), fileurlx, null);
				MainLayout.MomPanel.DisplayMoments();
				dispose();	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSend.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSend.setBackground(null);
			}
		});

	}
	

}
