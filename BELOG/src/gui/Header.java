package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.component.buttons.DefaultButton;
import gui.component.buttons.TextButton;

public class Header extends JPanel{
	private NavButton buttonPostList = new NavButton("방명록");
	private NavButton buttonMyPage = new NavButton("마이페이지");
	private DefaultButton buttonLogin = new DefaultButton("로그인");
	private DefaultButton buttonLogout = new DefaultButton("로그아웃");
	private DefaultButton buttonSignUp = new DefaultButton("회원가입");
	//첫번째 매개변수 : 메뉴 이동 버튼, 두번째 매개변수 : 로그인 로그아웃 회원가입 등의 버튼
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

		nav.add(buttonPostList);
		nav.add(buttonMyPage);
		nav.add(buttonLogin);
		nav.add(buttonLogout);
		nav.add(buttonSignUp);
	}

	public NavButton getButtonPostList() {
		return buttonPostList;
	}
	public NavButton getButtonMyPage() {
		return buttonMyPage;
	}

	public DefaultButton getButtonLogin() {
		return buttonLogin;
	}

	public DefaultButton getButtonLogout() {
		return buttonLogout;
	}

	public DefaultButton getButtonSignUp() {
		return buttonSignUp;
	}

	class NavButton extends TextButton{
		boolean isCurrent = false;
		boolean isVisible = true;
		NavButton(String navMenuText){
			super(navMenuText);
			setDecobutton();
		}
		public void setIsCurrent(boolean isCurrent){
			this.isCurrent = isCurrent;
			setDecobutton();
		}
		public void setIsVisible(boolean isVisible){
			this.isVisible = isVisible;
		}
		private void setDecobutton(){
			if(isCurrent){
				roundButtonLabel.setFont(CustomStyle.setCutomFont(18, 'b'));
				roundButtonLabel.setForeground(CustomStyle.mainColor);
			}else{
				roundButtonLabel.setFont(CustomStyle.setCutomFont(18, 'n'));
				roundButtonLabel.setForeground(CustomStyle.black);
			}
		}
	}
}
