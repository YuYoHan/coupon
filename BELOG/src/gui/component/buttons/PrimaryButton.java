package gui.component.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.CustomStyle;

public class PrimaryButton extends RoundButtons{
	public PrimaryButton(String buttonLabel) {
		super(buttonLabel);
	
		//폰트 색 변경
		roundButtonLabel.setForeground(CustomStyle.white);
		roundButtonLabel.setFont(CustomStyle.setCutomFont(16, 'b'));
		//기본 색 셋팅
		roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.mainColor, CustomStyle.mainColor));
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.primaryhoverColor, CustomStyle.primaryhoverColor));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.primaryactiveColor, CustomStyle.primaryactiveColor));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.mainColor, CustomStyle.mainColor));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				roundButtonContent.setBorder(new CustomStyle.DrawRoundSquare(8,CustomStyle.primaryhoverColor, CustomStyle.primaryhoverColor));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
}
