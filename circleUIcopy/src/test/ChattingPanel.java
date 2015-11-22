package test;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Dimension;


import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import client.CircleClient;
import communication.Message;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;

public class ChattingPanel extends JPanel {

	//public JTextArea ChatArea = new JTextArea(16,40);
	public JPanel Inner = new JPanel();
	public JScrollPane Scroller = new JScrollPane(Inner);//add chatarea to scrollarea
	public JTextField MsgField = new JTextField(38);
	public JButton SendMsgBtn = new JButton("Send");
	public JButton EmoBtn = new JButton();
	public JButton ImgBtn = new JButton("Image");
	public JButton VideoBtn = new JButton("Video Call");
	//public static JList list;

	public JLabel Plus=new JLabel("Plus");
	public ImageIcon PlusIcon = new ImageIcon("src/plus.png");
	public JScrollBar vertical = Scroller.getVerticalScrollBar();
	
	JPanel South = new JPanel();
	JPanel SubBar = new JPanel();
	String DesID = "null";
	boolean subbar=false;
	
	
	public ChattingPanel( CircleClient client) {
		//add(VideoBtn);
		setBackground(new Color(238, 238, 238));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Scroller.setPreferredSize(new Dimension(600, 370));
		Scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		DefaultCaret caret = (DefaultCaret)ChatArea.getCaret();
//		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		Scroller.setViewportView(Inner);

		VerticalFlowLayout vfl_Inner = new VerticalFlowLayout();
		vfl_Inner.setVgap(0);
		vfl_Inner.setHgap(0);
		vfl_Inner.setAlignment(0);
		Inner.setLayout(vfl_Inner);
		//Inner.setPreferredSize(new Dimension(600, 0));
		add(Scroller);//add scroller to panel, not the textarea
		MsgField.setHorizontalAlignment(SwingConstants.LEFT);
		MsgField.setBackground(UIManager.getColor("Button.highlight"));
		
		Plus = new JLabel(ClientFunction.resizeIcon(PlusIcon,24,24));
		Plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (subbar==false) {
					add(SubBar);
					revalidate();
					repaint();
					subbar=true;
					return;	
				}
				if (subbar==true) {
					remove(SubBar);
					revalidate();
					repaint();
					subbar=false;
					return;
				}
			}
		});
				
		FlowLayout flowLayout = (FlowLayout) South.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		South.add(MsgField);
		South.add(Plus);
		South.add(SendMsgBtn);
		//Inner.add(Box.createVerticalStrut(370)); 
		
		SendMsgBtn.setPreferredSize(new Dimension(100, 32));

		add(South);
		SubBar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		//ImgBtn.setPreferredSize(new Dimension(50, 32));
		//SubBar.add(EmoBtn);
		SubBar.add(ImgBtn);
		SubBar.add(VideoBtn);
		importEmoji();
		South.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{MsgField, SendMsgBtn}));

		
	}


	public void importEmoji() {
		ArrayList<Emoji> emoList = new ArrayList<Emoji>();
		Emoji emoji;
		JButton emojiBtn;
		
		for (int i = 701; i < 710; i++) {
			emoji = new Emoji("src/"+i+".png");
			emoji.num=i;
			emoji.url="src/"+i+".png";
			emoList.add(emoji);
			emojiBtn = new JButton(ClientFunction.resizeIcon(emoji.image,24,24));
			emojiBtn.addActionListener( new SendTextButtonHandler(Message.LINK,Inner,MsgField,ClientFunction.client,FriendPanel.friendname,emoji.url));
			SubBar.add(emojiBtn);

		}
		
	}
	
	class Emoji extends ImageIcon{
		int num;
		String url;
		ImageIcon image;
		public Emoji(String string) {
			 image = new ImageIcon(string);
		}
	}

}

