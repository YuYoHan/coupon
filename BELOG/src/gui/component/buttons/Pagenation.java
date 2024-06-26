package gui.component.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import gui.CustomStyle;

public class Pagenation extends JPanel{
	private int start = 1;	//페이지네이션 버튼 시작번호
	private int unit = 10;	//한 페이지에 최대 몇개의 버튼까지 보내줄 건지
	private int end = 1;		//페이지네이션 버튼 마지막번호
	
	private List<NumButton> NumButtonList = new ArrayList<>();		//번호리스트
	private List<JPanel> PanelList = new ArrayList<>();				//판넬 리스트
	private int currentNum = 0;		//현재 페이지 번호
	private int panelNum = 0;		//화살표 버튼으로 움직일 판넬 번호
	JButton buttonPrev;
	JButton buttonNext;
	
	//페이지버튼 끝번호를 매개변수로 받는 생성자
	public Pagenation(int end){
		this.end = end;
		
		setBackground(CustomStyle.white);
		
		//이전 페이지 버튼 추가
		buttonPrev = new AllowButton("prev");
		add(buttonPrev);
		
		//버튼판넬 셋팅 + 현재 버튼 스타일 셋팅 + 현재판넬만 보이게
		setNumberButtons();
		setCurrentButton();
		setCurrentPanel();
		
		//다음 페이지 버튼 추가
		buttonNext = new AllowButton("next");
		add(buttonNext);
		
		//현재 페이지에 따른 화살표 버튼 Visible 셋팅
		setVisibleAllow();
	}
	public Pagenation() {
		this(1);
	}
	
	//단일 페이지 버튼
	class NumButton extends TextButton{
		int number;
		boolean isCurrent = false;
		
		NumButton(int number){
			//해당 버튼에 들어갈 숫자 셋팅
			super(number+"");
			this.number = number;
			
			//기본 폰트 셋팅
			roundButtonLabel.setForeground(CustomStyle.black50);
			//버튼마우스이벤트
			this.addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					//현재 판넬 번호에 따른 판넬 보이기(해주지 않으면 버그가 생김)
					setCurrentPanel();
					//해당 페이지 버튼 누르면 현재 페이지로 셋팅하고 setVisible을 셋팅한다.
					currentNum = number-1;
					setCurrentButton();
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseClicked(MouseEvent e) {}
			});
		}
		
		@Override
		public String toString() {
			return "NumButton count : "+ number
					+ "\n isCurrent :" + isCurrent;
			//이 버튼의 번호와 현재 페이지 상태가 on 되어있는지 출력.
		}
	}
	//단일 Pagenation move 버튼
	class AllowButton extends RoundButtons{
		private int movePage = 0;
		
		AllowButton(String allow){
			super("");
			ImageIcon iconAllow = new ImageIcon(getClass().getResource("/icon_"+allow+".png"));
			roundButtonLabel.setIcon(iconAllow);
			movePage = (allow.equals("prev"))? -1 : 1;
			
			//버튼마우스이벤트
			this.addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					panelNum += movePage;
					//판넬 번호에 따른 판넬 visible 셋팅
					setCurrentPanel();
					//버튼에 따른 화살표 버튼 visible 셋팅
					setVisibleAllow();
					//Current 페이지 변경 + CurrentNum 에 따른 스타일 변경
					currentNum = (allow.equals("prev"))?panelNum*unit+(unit - 1):panelNum*unit;
					setCurrentButton();
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseClicked(MouseEvent e) {}
			});
		}
	}
	
	//페이지 네이션 버튼들을 화면에 배치하고 페이지네이션 판넬의 개수를 리턴함
	JPanel p;
	NumButton nb;
	int setNumberButtons() {
		IntStream.rangeClosed(start, end).forEach(n -> {
				if(n % unit == 1){
					p = new JPanel();
					PanelList.add(p);
					add(p);
				}
				nb = new NumButton(n);
				NumButtonList.add(nb);
				p.add(nb);
				p.setBackground(CustomStyle.white);
			});
		return PanelList.size();
	}
	
	//현재의 페이지를 나타내는 번호 셋팅. 현재 페이지 번호 리턴
	public int setCurrentButton() {
		NumButtonList.stream().forEach(N->{
			N.isCurrent = false;
			N.roundButtonLabel.setFont(CustomStyle.setCutomFont(16, 'n'));
			N.roundButtonLabel.setForeground(CustomStyle.black50);
		});
		NumButton current = NumButtonList.get(currentNum);
		current.isCurrent = true;
		current.roundButtonLabel.setFont(CustomStyle.setCutomFont(16, 'b'));
		current.roundButtonLabel.setForeground(CustomStyle.mainColor);
		
		return currentNum;
	}
	//판넬 번호에 해당하는 판넬만 보이게 셋팅. 현재 판넬 번호 리턴
	public int setCurrentPanel() {
		PanelList.stream().forEach(P->P.setVisible(false));
		PanelList.get(panelNum).setVisible(true);
		return panelNum;
	}
	
	//다음페이지가 있으면 다음 화살표 버튼을 이전 페이지가 있으면 이전 화살표 버튼을 보이게 한다.
	public int setVisibleAllow() {
		buttonPrev.setVisible(true);
		buttonNext.setVisible(true);
		if(panelNum == 0) {buttonPrev.setVisible(false);}
		if(panelNum == PanelList.size() - 1) {buttonNext.setVisible(false);}
		return panelNum;
	}
}
