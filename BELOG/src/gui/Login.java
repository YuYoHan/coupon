package gui;


import config.PasswordEncode;
import config.SessionManager;
import dao.UserDAO;
import dto.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;

    public Login(Index mainPage) {

        // 로그인 폼을 구현할 패널 생성
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel panel2 = new JPanel();

        // 로그인 폼 필드 추가
        panel.add(new JLabel("아이디:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // 로그인 버튼 추가
        JButton loginButton = new JButton("로그인");
        JButton loginCancel = new JButton("취소");
        panel2.add(loginButton);
        panel2.add(loginCancel);

        // 패널을 프레임에 추가
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);

        // JFrame 설정
        setSize(300, 150);
        setLocationRelativeTo(null); // 화면 가운데 정렬
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginId = idField.getText();
                System.out.println("아이디 체크 : " + loginId);
                UserDTO select = UserDAO.select(loginId);
                System.out.println("유저 정보 확인 : " + select);
                String password = new String(passwordField.getPassword());

                String encodePw = PasswordEncode.encode(password);
                // SHA-256과 같은 해시 알고리즘은 일반적으로 동일한 입력값에 대해 항상 동일한 해시값을 생성
                if (encodePw.equals(select.getUserPw())) {
                    SessionManager.loginUser(loginId);
                    JOptionPane.showMessageDialog(null, "로그인 성공!");
                    dispose();
                    mainPage.afterLogin();
                } else {
                    SessionManager.loginUser(null);
                    JOptionPane.showMessageDialog(null,"로그인 실패!");
                    dispose();
                    mainPage.setVisible(true);
                }
            }
        });

        loginCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 현재 창 닫기
                mainPage.setVisible(true); // 메인 창 다시 보이기
            }
        });
    }
}
