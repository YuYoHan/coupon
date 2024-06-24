package gui;


import config.PasswordEncode;
import dao.UserDAO;
import dto.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfo extends JFrame {
    private JTextField userLoginId;
    private JTextField userPassword;
    private JTextField userEmail;
    private JTextField userNickName;
    private Vector<String> colNames;
    private Vector<Vector<String>> rowData;
    private JTable table;
    private String selectedId;

    public UserInfo(Index mainPage) {
        userLoginId = new JTextField(5);
        // userLoginId 필드를 수정 불가능하게 설정
        userLoginId.setEditable(false);
        userPassword = new JTextField(7);
        userEmail = new JTextField(7);
        userNickName = new JTextField(10);

        JPanel p = new JPanel();
        p.add(new JLabel("아이디"));
        p.add(userLoginId);
        p.add(new JLabel("비밀번호"));
        p.add(userPassword);
        p.add(new JLabel("이메일"));
        p.add(userEmail);
        p.add(new JLabel("닉네임"));
        p.add(userNickName);

        JButton btnUpdate = new JButton("수정");
        JButton btnRemove = new JButton("삭제");
        JButton btnCancel = new JButton("취소");
        p.add(btnUpdate);
        p.add(btnRemove);
        p.add(btnCancel);
        colNames = new Vector<>();
        colNames.add("아이디");
        colNames.add("비밀번호");
        colNames.add("이메일");
        colNames.add("닉네임");
        rowData = new Vector<>();
        table = new JTable(rowData, colNames);
        JScrollPane jsp = new JScrollPane(table);
        add(p, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);

        loadUser();

        setSize(650, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 수정
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowData.clear();

                String userId = userLoginId.getText();
                System.out.println("유저 아이디 : " + userId);
                UserDTO select = UserDAO.select(userId);

                if (!checkPw(userPassword.getText())) {
                    System.out.println("비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8글자 이상 20글자 이하여야 합니다.");
                    JOptionPane.showMessageDialog(null, "비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8글자 이상 20글자 이하여야 합니다.");
                    return;
                }

                String encodePw = PasswordEncode.encode(userPassword.getText());

                if (encodePw.equals(select.getUserPw())) {
                    System.out.println("동일한 비밀번호이기 때문에 바꿀 수 없습니다.");
                    JOptionPane.showMessageDialog(null, "기존에 동일한 비밀번호로 바꿀 수 없습니댜.");
                    return;
                }

                String email = userEmail.getText();
                if (!checkEmail(email)) {
                    JOptionPane.showMessageDialog(null, "이메일 중복을 확인해주세요.");
                    return; // 중복되는 이메일일 경우 회원가입 중지
                }

                UserDTO user = new UserDTO();
                user.setUserPw(encodePw);
                user.setUserEmail(userEmail.getText());
                user.setNickName(userNickName.getText());
                System.out.println(user);

                Vector<String> row = rowData.get(0);
                int selectedId = Integer.parseInt(row.get(0));

                int update = UserDAO.update(user, selectedId);
                if (update > 0) {
                    JOptionPane.showMessageDialog(null, "유저 정보를 수정했습니다.");
                    dispose(); // 현재 창 닫기
                    mainPage.setVisible(true); // 메인 창 다시 보이기
                } else {
                    JOptionPane.showMessageDialog(null, "유저 정보를 수정 실패했습니다.");
                }
            }
        });



        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainPage.afterLogin();
            }
        });
    }

    public void loadUser() {
        //JTable에 출력하는 메소드
        rowData.clear();
        String selected = selectedId;
        UserDTO select = UserDAO.select(selected);
        Vector<String> v = new Vector<>();
        v.add(select.getUserId() + "");
        v.add(select.getUserLoginID());
        v.add(select.getUserPw());
        v.add(select.getUserEmail());
        v.add(select.getNickName());
        rowData.add(v);
        table.updateUI();
    }

    // 소문자, 대문자, 0~9 숫자, 특수문자 8자리 이상!(소문자, 대문자 같이 안써도 됨!)
    // 위에서 비밀번호를 생성하기위한 조건을 주기위해서 메소드 만들었다!
    public boolean checkPw(String userpw) {
        String passwordPolicy = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";

        Pattern pattern_pwd = Pattern.compile(passwordPolicy);
        Matcher matcher_pwd = pattern_pwd.matcher(userpw);

        return matcher_pwd.matches();
    }

    // 이메일 검사
    public boolean checkEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern patternEmail = Pattern.compile(emailPattern);
        Matcher matcherEmail = patternEmail.matcher(email);
        UserDTO userDTO = UserDAO.selectByEmail(email);

        if (matcherEmail.matches() && userDTO == null) {
            return true;
        }
        return false;
    }
}
