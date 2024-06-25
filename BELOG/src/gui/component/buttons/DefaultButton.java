package gui.component.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.CustomStyle;

public class DefaultButton extends RoundButtons{
	public DefaultButton(String buttonLabel) {
		super(buttonLabel);
	
		//기본 색 셋팅
		roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.buttonBorderColor, CustomStyle.white));
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.buttonBorderColor, CustomStyle.hoverColor));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.buttonBorderColor, CustomStyle.activeColor));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.buttonBorderColor, CustomStyle.white));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.buttonBorderColor, CustomStyle.hoverColor));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
}
