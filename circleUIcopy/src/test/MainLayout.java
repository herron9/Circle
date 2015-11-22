package test;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;



public class MainLayout extends JPanel {
	
	public static JPanel MainUppage = new JPanel ();// will be cardlayout
	MainmenuPanel Mainmenu = new MainmenuPanel();
	
	public static CardLayout MainpageCl = new CardLayout();
	public static MainChatPanel MCPanel = new MainChatPanel();
	public static FriendPanel FriendList = new FriendPanel(LoginPanel.names);
	
	public static MomentsPanel MomPanel = new MomentsPanel();
	public static JScrollPane  MomPane = new JScrollPane(MomPanel); 
	public static ProfilePanel panelPro = new ProfilePanel();
	
	
	public MainLayout() {
		setBackground(new Color(240, 255, 240));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//the overall look will be box
		MainUppage.setAlignmentY(0.0f);
		MainUppage.setLayout(MainpageCl);
		MainUppage.setPreferredSize(new Dimension(600,400));
		Mainmenu.setPreferredSize(new Dimension(600, 50));
		
		panelPro.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		MainUppage.add(MCPanel,"MCPanel");
		MainUppage.add(FriendList,"FriendList");
		MainUppage.add(panelPro, "ProPanel");
		MomPane.setPreferredSize(new Dimension(600,370));
		MomPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		MomPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    MomPane.getVerticalScrollBar().setValue(0);
	    revalidate();
		repaint();
		//MomPanel.setPreferredSize(new Dimension(MomPane.getWidth(), MomPane.getHeight()*2));
		//JScrollBar Bar = MomPane.getVerticalScrollBar();
        //MomPanel.updateUI();//利用当前外观的值重置 UI 属性。 也可以保证滚动条随时的更新
//                  //终于搞好了，将垂直滚动条自动的移动到最低端
//　　　　　　　　//setViewPosition：设置显示在视口左上角的视图坐标
        //MomPane.getVerticalScrollBar().getMaximum(); //返回滚动条的最大跨度
        //MomPane.getViewport().setViewPosition(new Point(0, MomPane.bar.getMaximum()));
		MainUppage.add(MomPane, "MPane");
		add(MainUppage);
		Mainmenu.setAlignmentY(0.0f);
		add(Mainmenu);
		
		
		
		
	}

}
