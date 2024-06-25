package gui;

import javax.swing.*;
import java.awt.*;

// Placeholder 기능을 위한 커스텀 JPasswordField 클래스
public class HintPasswordField extends JPasswordField {
    private final String hint;
    private boolean showingHint;

    public HintPasswordField(String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.setEchoChar((char) 0);
        setForeground(Color.GRAY); // 초기 텍스트 색상을 회색으로 설정
        super.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (getPassword().length == 0) {
                    setText("");
                    setEchoChar('*');
                    setForeground(Color.BLACK);
                    showingHint = false;
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (getPassword().length == 0) {
                    setText(hint);
                    setEchoChar((char) 0);
                    setForeground(Color.GRAY);
                    showingHint = true;
                }
            }
        });
    }

    @Override
    public char[] getPassword() {
        return showingHint ? new char[0] : super.getPassword();
    }

    @Override
      protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          if (showingHint) {
              int padding = (getHeight() - getFont().getSize()) / 2;
              g.drawString(hint, getInsets().left, getFont().getSize() + padding - 2);
          }
      }
}
