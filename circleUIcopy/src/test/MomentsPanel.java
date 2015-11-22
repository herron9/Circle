package test;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MomentsPanel extends JPanel {
	
	JPanel up = new JPanel();
	JPanel down = new JPanel();
	JLabel newmoment = new JLabel("Add New Moment");
	// refresh;
	ImageIcon refreshIcon = new ImageIcon("src/refresh.png");
	JLabel refresh = new JLabel(ClientFunction.resizeIcon(refreshIcon,24));
	VerticalFlowLayout vfl = new VerticalFlowLayout();
	JPanel Subpanel = new JPanel();
	public MomentsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setup();
		setdown();
		add(up);
		add(down);	
	}
	public void setup() {
		up.setPreferredSize(new Dimension(600,30));
		
		GridBagLayout gbl_up = new GridBagLayout();
		gbl_up.columnWidths = new int[]{550, 50};
		gbl_up.rowHeights = new int[]{16, 0, 0};
		gbl_up.columnWeights = new double[]{1.0, 0.0};
		gbl_up.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		up.setLayout(gbl_up);
		//newmoment.setHorizontalAlignment(SwingConstants.CENTERIN_VALUE};
		up.setLayout(gbl_up);
		newmoment.setHorizontalAlignment(SwingConstants.CENTER);
		newmoment.setOpaque(true);
		newmoment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click");
				AddMoments NewM =new AddMoments();
				NewM.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				newmoment.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				newmoment.setBackground(null);
			}
		});


		newmoment.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_newmoment = new GridBagConstraints();
		gbc_newmoment.fill = GridBagConstraints.BOTH;
		refresh.setHorizontalAlignment(SwingConstants.LEFT);
		refresh.setOpaque(true);
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            	LoginFunction.GetMoments(LoginPanel.operation, LoginFunction.AccessToken);
            	MainLayout.MomPanel.DisplayMoments();
			}
			public void  mouseExited(MouseEvent e) {
	           	 refresh.setBackground(null);
			}
	        public void mouseEntered(MouseEvent e) {
	        	refresh.setBackground(Color.LIGHT_GRAY);
	        }
		});
		//wmoment.anchor = GridBagConstraints.NORTH;
		//gbc_newmoment.insgbc_refresh.fill = GridBagConstraints.BOTH;
		//ets = new Insets(0, 0, 5, 5);
		gbc_newmoment.gridx = 0;
		gbc_newmoment.gridy = 0;
		up.add(newmoment, gbc_newmoment);
		
		GridBagConstraints gbc_refresh = new GridBagConstraints();
		gbc_refresh.anchor = GridBagConstraints.WEST;
		//gbc_refresh.insets = new Insets(0, 0, 5, 0nstraints gbc_refresh = new GridBagConstraints();
		gbc_refresh.gridx = 1;
		gbc_refresh.gridy = 0;
		up.add(refresh, gbc_refresh);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
	}
//	public void DisplayMoments(String name, String Time,String test,ImageIcon image) {
	public void setdown() {
		//down.setPreferredSize(new Dimension(600, 370));//why use that can fix the bar bug?
		down.setLayout(vfl);
		DisplayMoments();
	}
    public void DisplayMoments() {
		down.removeAll();
		for (int i = 0; i < LoginFunction.moments.size(); i++) {
			MomentCell cell = new MomentCell();
			cell.lblName.setText(LoginFunction.moments.get(i).name);
			cell.lblIcon.setIcon(new ImageIcon("src/avatar.png"));
			cell.content.MomTextArea.setText(LoginFunction.moments.get(i).text);
			if (LoginFunction.moments.get(i).image != null) {
				cell.content.lblPic.setIcon(LoginFunction.moments.get(i).image);
			}
//			BufferedImage bufferedImage = null;
//			try {
//				URL myURL = new URL("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
//				bufferedImage = ImageIO.read(myURL);
//			} catch (IOException f) {
//			}
//			ImageIcon image=new ImageIcon(bufferedImage);
//			Image img = image.getImage();
//			BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
//			Graphics g = bi.createGraphics();
//			g.drawImage(img, 0, 0, 50, 50, null);
//			ImageIcon image1 = new ImageIcon(bi);
//				cell.content.lblPic.setIcon(image1);
			//}
//			else {
//				cell.content.remove(cell.content.lblPic);
//			}
//			cell.lblTimeStamp.setText(LoginFunction.moments.get(i).time);
			cell.setAlignmentY(5);
			cell.setAlignmentX(0);
			down.add(cell);
		}
    }
    
    public void setSubpanel() {
    	
		
	}
}

