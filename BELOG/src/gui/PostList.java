package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.CustomStyle.DrowSquare;
import gui.component.buttons.DefaultButton;
import gui.component.buttons.Pagenation;
import gui.component.buttons.PrimaryButton;
import gui.component.input.RoundInput;

public class PostList extends JFrame{
	PostList(){
		//S:header
		Header header = new Header();
		add(header, BorderLayout.NORTH);
		//E:header
		
		//S:body
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		body.setBorder(new EmptyBorder(0, 96, 60, 96));
		body.setBackground(CustomStyle.white);
		add(body);
		//E:body
		
		//S: Total label
		JPanel tableTop = new JPanel();
		tableTop.setLayout(new BorderLayout());
		tableTop.setBackground(CustomStyle.white);
		body.add(tableTop, BorderLayout.NORTH);
		
		
		JPanel labelWrap = new JPanel();
		labelWrap.setLayout(new FlowLayout());
		labelWrap.setBackground(CustomStyle.white);
		tableTop.add(labelWrap, BorderLayout.WEST);
		
		JLabel defaultLabel1 = new JLabel("Total ");
		labelWrap.add(defaultLabel1);
		defaultLabel1.setFont(CustomStyle.setCutomFont(16, 'n'));
		
		JLabel totalNum = new JLabel("0");
		labelWrap.add(totalNum);
		totalNum.setFont(CustomStyle.setCutomFont(16, 'b'));
		totalNum.setForeground(CustomStyle.mainColor);
		//E: Total label
		
		//S: tableContentBox 
		JScrollPane tableContentBox = new JScrollPane();
		tableContentBox.setBackground(CustomStyle.white);
		DrowSquare tableBoxBorder = new DrowSquare();
		tableContentBox.setBorder(tableBoxBorder);
		tableContentBox.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableContentBox.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		body.add(tableContentBox, BorderLayout.CENTER);
		
		JTable table;
		Vector<String> columnName = new Vector<String>();
		columnName.add("No");
		columnName.add("제목");
		columnName.add("작성자");
		columnName.add("열1");
		Vector<Vector<String>> rowData = new Vector<>();
		int testNum = 0;
		while(testNum++ < 100) {
			Vector<String> row = new Vector<String>();  
			row.add(testNum+"");
			row.add("테스트하는중..");
			row.add("테스트");
			row.add("흠");
			rowData.add(row);
		}
		table = new JTable(rowData, columnName);
		table.setBorder(new LineBorder(CustomStyle.borderColor, 1));
		
		tableContentBox.setViewportView(table);
		table.setMinimumSize(super.getPreferredSize());
		//E: tableContentBox
		//S: tableBottomArea
		JPanel tableBottomArea = new JPanel();
		body.add(tableBottomArea, BorderLayout.SOUTH);
		tableBottomArea.setLayout(new GridLayout(2,1));
		
		
		JPanel tableNavArea = new JPanel();
		tableNavArea.setLayout(new BorderLayout());
		
		tableBottomArea.add(tableNavArea);
		
		
		Pagenation tablePagenation = new Pagenation();
		tableNavArea.add(tablePagenation, BorderLayout.CENTER);
		
		
		JPanel buttonsWrap = new JPanel();
		buttonsWrap.setLayout(new FlowLayout());
		tableNavArea.add(buttonsWrap, BorderLayout.EAST);
		DefaultButton ButtonWrite = new DefaultButton("글쓰기");
		buttonsWrap.add(ButtonWrite);
		
		
		JPanel tableSearchArea = new JPanel();
		tableBottomArea.add(tableSearchArea);
		tableSearchArea.setLayout(new FlowLayout());
		tableSearchArea.setBackground(CustomStyle.white);
		RoundInput searchInput = new RoundInput(20);
		tableSearchArea.add(searchInput);
		PrimaryButton buttonSearch = new PrimaryButton("검색");
		tableSearchArea.add(buttonSearch);
		//E: tableBottomArea
		
		//S: JFrame Setting
		setSize(1440, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//E: JFrame Setting
	}
	public static void main(String[] args) {
		new PostList();
	}
}