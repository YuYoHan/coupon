package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.InputStream;

import javax.swing.border.Border;

public class CustomStyle{
	//Common display maring 
	public static int DISPLAY_MARGIN = 96;
	
	//Font
	InputStream fontStreamR = getClass().getResourceAsStream("/SpoqaHanSansNeo-Regular.ttf");
	InputStream fontStreamB = getClass().getResourceAsStream("/SpoqaHanSansNeo-Bold.ttf");
	
	public static Font setCutomFont(int size, char bolder) {
		CustomStyle style = new CustomStyle();
		Font customFont;
		Font fontStyle = null;
		try {
			switch(bolder) {
			case 'n' -> {
				customFont = Font.createFont(Font.TRUETYPE_FONT, style.fontStreamR);
				fontStyle = customFont.deriveFont(Font.PLAIN, size);
			}
			case 'b' -> {
				customFont = Font.createFont(Font.TRUETYPE_FONT, style.fontStreamB);
				fontStyle = customFont.deriveFont(Font.BOLD, size);
			}
			}
		} catch (Exception e) {
			System.err.println("폰트 설정 안됨");
			e.printStackTrace();
		}
		return fontStyle;
	}
	
	//Color.
	public static Color white = new Color(255,255,255);
	public static Color black = new Color(0,0,0);
	public static Color black50= new Color(0,0,0, 125);
	public static Color mainColor = new Color(92, 31, 107);
	public static Color hoverColor = new Color(249, 226, 255);
	public static Color primaryhoverColor = new Color(109,42,126);
	public static Color primaryactiveColor = new Color(65,0,81);
	public static Color activeColor = new Color(227, 199, 234);
	public static Color transparency = new Color(255, 255, 255, 0);
	public static Color buttonBorderColor = new Color(163, 163, 163);
	public static Color borderColor = new Color(217, 217, 217);

	//둥근 사각형 그리는 Boarder
	public static class DrawRoundSquare implements Border{
		private int radius = 8;
		private int radius1 = radius;
		private int radius2 = radius;
		private int radius3 = radius;
		private int radius4 = radius;
        private Color borderColor = CustomStyle.buttonBorderColor;
        private Color background = CustomStyle.white;

        public DrawRoundSquare(int radius, Color borderColor, Color background) {
            this.radius = radius;
            this.borderColor = borderColor;
            this.background = background;
        }
        public DrawRoundSquare(int radius1, int radius2, int radius3, int radius4, Color borderColor, Color background) {
            this.radius1 = radius;
            this.radius2 = radius;
            this.radius3 = radius;
            this.radius4 = radius;
            this.borderColor = borderColor;
            this.background = background;
        }
        
        public DrawRoundSquare() {}
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius1, radius2, radius3, radius4);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D
            g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(background);
            g2.fillRoundRect(x, y, width-1, height-1, radius*2, radius*2);
            g2.setColor(borderColor);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius*2, radius*2);
            
            
            g2.dispose();
        }
	}
	
	//각진 사각형 그리는 Boarder
	public static class DrowSquare implements Border{
		private Color borderColor = CustomStyle.borderColor;
        private Color background = CustomStyle.transparency;
        
        
		public DrowSquare() {}
		public DrowSquare(Color borderColor, Color background) {
			this.borderColor = borderColor;
			this.background = background;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D
            g2 = (Graphics2D) g.create();
			g2.setColor(background);
			g2.fillRect(x, y, width-1, height-1);
			g2.setColor(borderColor);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRect(0, 0, width - 1, height - 1);
		}
		
		@Override
		public boolean isBorderOpaque() {
			// TODO Auto-generated method stub
			 return true;
		}
		
		@Override
		public Insets getBorderInsets(Component c) {
			// TODO Auto-generated method stub
			return new Insets(1, 1, 1, 1);
		}
	}
}