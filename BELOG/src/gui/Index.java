package gui;

import config.SessionManager;
import gui.component.user.Login;
import gui.component.user.SignUp;

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
                new SignUp(Index.this);
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
    // 로그인 후 보이는 창
    public void afterLogin() {
        // 기존 컴포넌트들을 제거
        getContentPane().removeAll();
        String currentUser = SessionManager.getCurrentUser();
        System.out.println("현재 접속중인 닉네임 : " + currentUser);

        JButton logout_button = new JButton("로그아웃");
        JButton myInfo_button = new JButton("내정보보기");
        JButton board_button = new JButton("게시물");

        JPanel jPanel = new JPanel();
        jPanel.add(logout_button);
        jPanel.add(myInfo_button);
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        setLayout(new BorderLayout());
        add(jPanel, BorderLayout.NORTH);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel2.add(board_button);
        add(jPanel2, BorderLayout.CENTER);

        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("로그아웃 메뉴를 선택하였습니다.");
                SessionManager.logoutUser();
                dispose(); // 현재 윈도우를 닫음
                setVisible(true);
            }
        });

        board_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("게시글 메뉴를 선택하였습니다.");
                boolean check = SessionManager.isLoggedIn();
                if(check) {
                    return;
                }
            }
        });

        revalidate(); // 변경된 GUI를 다시 그리도록 갱신

        // JFrame 설정
        setSize(1440, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new Index();
    }
}
