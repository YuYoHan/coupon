package gui.component.input;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.CustomStyle;

public class RoundTextArea extends JPanel{
	protected JTextArea textarea;
	
	public RoundTextArea(){
		this(2, 10);
	}
	//public Roun
	public RoundTextArea(int row, int cols){
		setBorder(new CustomStyle.DrawRoundSquare(8, CustomStyle.borderColor, CustomStyle.white));
		textarea = new JTextArea(row, cols);
		add(textarea);
		textarea.setBorder(null);
		textarea.setFont(CustomStyle.setCutomFont(16, 'n'));
	}
}
