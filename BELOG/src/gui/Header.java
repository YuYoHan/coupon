package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.component.buttons.DefaultButton;
import gui.component.buttons.TextButton;


class NavButton extends TextButton{
	NavButton(String navMenuText){
		super(navMenuText);
		roundButtonLabel.setFont(CustomStyle.setCutomFont(18, 'n'));
		roundButtonLabel.setForeground(CustomStyle.black);
	}	
}

class CurrentNavButton extends NavButton{
	CurrentNavButton(String navMenuText) {
		super(navMenuText);
		roundButtonLabel.setFont(CustomStyle.setCutomFont(18, 'b'));
		roundButtonLabel.setForeground(CustomStyle.mainColor);
	}
}
class DefualtNavButton extends NavButton{
	DefualtNavButton(String navMenuText) {
		super(navMenuText);
		roundButtonLabel.setFont(CustomStyle.setCutomFont(18, 'n'));
		roundButtonLabel.setForeground(CustomStyle.black);
	}
}
public class Header extends JPanel{
	Header(){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(24, CustomStyle.DISPLAY_MARGIN, 24, CustomStyle.DISPLAY_MARGIN));
		setBackground(CustomStyle.white);
		
		//S: logo
		ImageIcon logoImage = new ImageIcon(getClass().getResource("/logo.png"));
		JButton logo = new JButton(logoImage);
		logo.setBorderPainted(false);
		logo.setFocusPainted(false);
		logo.setContentAreaFilled(false);
		add(logo, BorderLayout.WEST);
		
		logo.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				logo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		//E:logo
		//S:nav
		JPanel nav = new JPanel();
		add(nav, BorderLayout.EAST);
		nav.setLayout(new FlowLayout());
		nav.setBackground(CustomStyle.white);
		
		NavButton navPostList = new CurrentNavButton("방명록");
		nav.add(navPostList);
		NavButton navUserInfo = new DefualtNavButton("마이페이지");
		nav.add(navUserInfo);
		
		DefaultButton loginButton = new DefaultButton("로그인");
		nav.add(loginButton);
		//E:nav
	}
}
