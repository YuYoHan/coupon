package gui.component.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.CustomStyle;

public class TextButton extends RoundButtons{
	public TextButton(String buttonLabel) {
		super(buttonLabel);
		
		//기본 폰트 셋팅
		roundButtonLabel.setFont(CustomStyle.setCutomFont(16, 'n'));
		
		//기본 배경색 셋팅
		roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.transparency, CustomStyle.transparency));
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.hoverColor, CustomStyle.hoverColor));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.activeColor, CustomStyle.activeColor));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.white, CustomStyle.white));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.hoverColor, CustomStyle.hoverColor));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
}
