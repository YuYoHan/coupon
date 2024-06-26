package gui;

import javax.swing.*;
import java.awt.*;

// Placeholder 기능을 위한 커스텀 JTextField 클래스
public class HintTextField extends JTextField {
    private final String hint;
    private boolean showingHint;

    public HintTextField(String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        setForeground(Color.GRAY); // 초기 텍스트 색상을 회색으로 설정

        super.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (getText().isEmpty()) {
                    setText("");
                    // 포커스를 받으면 글씨 색상을 검정으로 변경
                    setForeground(Color.BLACK);
                    showingHint = false;
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(hint);
                    // 입력이 없는 경우 다시 회색으로 변경
                    setForeground(Color.GRAY);
                    showingHint = true;
                } else {
                    showingHint = false;
                }
            }
        });
    }

    @Override
    public String getText() {
        System.out.println("getText 체크 : " + super.getText());
        String result = showingHint ? "" : super.getText();
        System.out.println("result : " + result);
        return result;
    }

    @Override
    // 화면에 컴포넌트가 그려질 때 호출되는 메서드
    protected void paintComponent(Graphics g) {
        // 이 부분은 상위 클래스인 JTextField의 paintComponent 메서드를
        // 호출하여 기본적인 텍스트 필드의 그리기 작업을 수행
        super.paintComponent(g);
        // showingHint 변수가 true일 때에만 추가적인 그리기 작업을 수행
        if (showingHint) {
            // 텍스트 필드의 높이에서 폰트의 크기를 뺀 후 2로 나눈 값을 padding 변수에 저장합니다.
            // 이는 힌트 텍스트가 텍스트 필드의 세로 중앙에 위치하도록 하기 위한 계산
            int padding = (getHeight() - getFont().getSize()) / 2;
            // g.drawString() 메서드를 사용하여 힌트 텍스트를 그립니다.
            // hint는 힌트 텍스트의 내용을 나타내며, getInsets().left는
            // 텍스트 필드의 좌측 여백 위치를 가져오고, getFont().getSize() + padding - 2는
            // 텍스트가 세로 중앙에 위치하도록 계산한 값을 나타냅니다.
            g.drawString(
                    hint,
                    getInsets().left,
                    getFont().getSize() + padding - 2);
        }
    }

}
