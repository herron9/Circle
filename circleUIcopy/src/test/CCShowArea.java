package test;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.*;
import java.awt.GridLayout;
import java.awt.RenderingHints;

public class CCShowArea extends JPanel {
	
	//public JPanel Dia = new JPanel();
	public static AbstractBorder Left = new TextBubbleBorder(new Color(240, 255, 255),0,10,12);
	public static AbstractBorder Right = new TextBubbleBorder(new Color(240, 255, 240),0,10,12,false);
	public JLabel msg = new JLabel("hello");

	public CCShowArea(AbstractBorder border) {
		setLayout(new GridLayout(0, 1, 0, 0));
		setOpaque(true);
		setBorder(border);
	}
	

}

	class TextBubbleBorder extends AbstractBorder {

		private Color color;
		private int thickness = 2;
		private int radii = 8;
		private int pointerSize = 7;
		private Insets insets = null;
		private BasicStroke stroke = null;
		private int strokePad;
		private boolean left = true;
		RenderingHints hints;

		TextBubbleBorder(Color color) { new TextBubbleBorder(color, 4, 8, 15);}

		TextBubbleBorder(Color color, int thickness, int radii, int pointerSize) {
			this.thickness = thickness;
			this.radii = radii;
			this.pointerSize = pointerSize;
			this.color = color;
	
			stroke = new BasicStroke(thickness);
			strokePad = thickness / 2;
			
			hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	
			int pad = radii + strokePad;
			int bottomPad = pad + pointerSize + strokePad;
			insets = new Insets(pad, bottomPad, pad, pad);
			}
		
			TextBubbleBorder( Color color, int thickness, int radii, int pointerSize, boolean left) 
			{
				this(color, thickness, radii, pointerSize);
				this.left = left;
			}
	
			@Override
			public Insets getBorderInsets(Component c) {
				return insets;
			}		
			public Insets getBorderInsets(Component c, Insets insets) {
				return getBorderInsets(c);
			}

        @Override
		public void paintBorder(Component c, Graphics g,int x, int y,int width, int height) {
		
				Graphics2D g2 = (Graphics2D) g;
				int bottomLineX = width - thickness -pointerSize;		
				if (left) {
					RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
							0+pointerSize,0,
					        bottomLineX,height - thickness,radii,radii);
					Polygon pointer = new Polygon();	
					pointer.addPoint(pointerSize,10);//up
					pointer.addPoint(0,15);//center
					pointer.addPoint(pointerSize,20);//down
					Area area = new Area(bubble);
					area.add(new Area(pointer));	
					g2.setRenderingHints(hints);
					
					Component parent  = c.getParent();
					if (parent!=null) {
						Color bg = parent.getBackground();
						Rectangle rect = new Rectangle(0,0,width, height);
					    Area borderRegion = new Area(rect);
					    borderRegion.subtract(area);
						g2.setClip(borderRegion);
						g2.setColor(bg);
						g2.fillRect(0, 0, width, height);
						g2.setClip(null);
					}
			
					g2.setColor(color);
					g2.setStroke(stroke);
					g2.draw(area);
					
				} 
				else {
					RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
							0, 0,bottomLineX,height - thickness,radii,radii);
					Polygon pointer = new Polygon();
					pointer.addPoint(width-pointerSize-thickness,10);//up
					pointer.addPoint(width,15);//center
					pointer.addPoint(width-pointerSize-thickness,20);//down
					Area area = new Area(bubble);
					area.add(new Area(pointer));	
					g2.setRenderingHints(hints);
					
					// Paint the BG color of the parent, everywhere outside the clip
					// of the text bubble.
					Component parent  = c.getParent();
					if (parent!=null) {
						Color bg = parent.getBackground();
						Rectangle rect = new Rectangle(0,0,width, height);
					    Area borderRegion = new Area(rect);
					    borderRegion.subtract(area);
						g2.setClip(borderRegion);
						g2.setColor(bg);
						g2.fillRect(0, 0, width, height);
						g2.setClip(null);
					}
			
					g2.setColor(color);
					g2.setStroke(stroke);
					g2.draw(area);
				}
        }
	}
