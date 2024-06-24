package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame {

    public Index() {
        JLabel jLabel = new JLabel("메인페이지", SwingConstants.CENTER);
        setBackground(Color.CYAN);

        // 버튼을 담을 패널 생성
        JPanel jPanel = new JPanel();
        // JLabel을 JPanel에 담기위한 패널
        jPanel.setLayout(new GridLayout(1, 1));
        // 패널에 라벨을 추가
        jPanel.add(jLabel);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));

        // 버튼 생성
        JButton signUpButton = new JButton("회원가입");
        jPanel2.add(signUpButton);
        JButton loginButton = new JButton("로그인");
        jPanel2.add(loginButton);

        setLayout(new BorderLayout());
        add(jPanel, BorderLayout.CENTER);
        add(jPanel2, BorderLayout.NORTH);

        setSize(1440, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 회원가입
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SingUp(Index.this);
                setVisible(false);
            }
        });
        // 로그인 버튼 클릭 시
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(Index.this); // 로그인 창 보여주기
                setVisible(false); // 현재 창 숨기기
            }
        });
    }


    public static void main(String[] args) {
        new Index();
    }
}
