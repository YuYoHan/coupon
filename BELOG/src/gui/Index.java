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
        // 메인 패널 생성
        JPanel mainPanel = new JPanel();
        // JLabel을 JPanel에 담기위한 패널
        mainPanel.setLayout(new GridLayout(1, 1));
        
        // 메인 왼쪽 이미지 패널
        JPanel leftImagePanel = new JPanel();
        // 메인 오른쪽 텍스트 패널
        JPanel rightTextPanel = new JPanel();
        
        //헤더 패널 생성
        Header headerPanel = new Header();

        //푸터 패널 생성
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.CYAN);
        
        // 메인 이미지 패널에 담기
        ImageIcon mainImage = new ImageIcon(getClass().getClassLoader().getResource("gui/main.png"));

        Image imgMain = mainImage.getImage();
        Image chageMainImg = imgMain.getScaledInstance(734, 547, Image.SCALE_SMOOTH);
        ImageIcon changeMainIcon = new ImageIcon(chageMainImg);

		JLabel mainLb = new JLabel();
		mainLb.setIcon(changeMainIcon);
		leftImagePanel.add(mainLb);

        //메인 로고 라벨 만들기
        ImageIcon titleImage = new ImageIcon(getClass().getClassLoader().getResource("gui/logo.png"));
        Image img = titleImage.getImage();
        Image changeLogoImg = img.getScaledInstance(550, 110, Image.SCALE_SMOOTH);
        ImageIcon changeLogoIcon = new ImageIcon(changeLogoImg);
        JLabel titleLb = new JLabel();
        titleLb.setIcon(changeLogoIcon);

		// 메인 텍스트 패널에 담기
		JLabel jLabel1 = new JLabel("방명록을 통해");
        jLabel1.setFont(new Font("",Font.BOLD,30));
		JLabel jLabel2 = new JLabel("생각을 공유하세요");
        jLabel2.setFont(new Font("",Font.BOLD,30));

        rightTextPanel.setLayout(new GridLayout(2,1));
		rightTextPanel.add(jLabel1);
		rightTextPanel.add(jLabel2);

        // 메인 패널 위:로고  아래:이미지+텍스트
        JPanel main1Panel = new JPanel();
        main1Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel main2Panel = new JPanel();

        // 패널에 담기
        main1Panel.setLayout(new FlowLayout());
        main1Panel.add(titleLb);
        main1Panel.setBackground(Color.white);


        main2Panel.add(leftImagePanel);
        main2Panel.add(rightTextPanel);
        main2Panel.setBackground(Color.white);
        main2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 메인 패널에 타이틀, 이미지, 텍스트 담기
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.white);
        mainPanel.add(main1Panel, BorderLayout.NORTH);
		mainPanel.add(main2Panel, BorderLayout.CENTER);
        
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);
        add(footerPanel, BorderLayout.SOUTH);

        setSize(1440, 950);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 회원가입
//        signUpButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new SignUp(Index.this);
//                setVisible(false);
//            }
//        });
//        // 로그인 버튼 클릭 시
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new Login(Index.this); // 로그인 창 보여주기
//                setVisible(false); // 현재 창 숨기기
//            }
//        });
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
