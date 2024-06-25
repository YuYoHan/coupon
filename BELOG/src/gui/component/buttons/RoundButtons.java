package gui.component.buttons;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.CustomStyle;

//부모역할만
public class RoundButtons extends JButton{
	RoundButtons(){
		
	}
	protected RoundButtonContent roundButtonContent;
	protected RoundButtonLabel roundButtonLabel;
	protected class RoundButtonContent extends JPanel{
		RoundButtonContent(){
			setMargin(new Insets(0, 0, 0, 0));
		}
	}
	protected class RoundButtonLabel extends JLabel{
		RoundButtonLabel(String buttonLabel){
			super(buttonLabel);
		}
	}
	
	RoundButtons(String buttonLabel){
		//버튼 기본 스타일 초기화
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		
		//버튼 안에 컨텐츠 판넬 추가
		roundButtonContent = new RoundButtonContent();
		add(roundButtonContent);
		roundButtonContent.setBackground(CustomStyle.transparency);
		
		//버튼 컨텐츠 안에 라벨 추가
		roundButtonLabel = new RoundButtonLabel(buttonLabel);
		roundButtonContent.add(roundButtonLabel);
		roundButtonLabel.setFont(CustomStyle.setCutomFont(16, 'n'));
		//기본 마우스 이벤트
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
}