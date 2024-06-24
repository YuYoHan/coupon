package gui;

import config.PasswordEncode;
import dao.UserDAO;
import dto.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends JFrame {
    private JTextField userLoginId;
    private JPasswordField userPassword;
    private JPasswordField confirmPasswordField;
    private JTextField userEmail;
    private JTextField userNickName;
    private boolean idAvailable = false;
    private boolean emailAvailable = false;

    public SignUp(Index mainPage) {
        // 회원가입 구현할 패널 생성
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel panel1 = new JPanel();

        panel.add(new JLabel("아이디"));
        panel.add(new Label("(조건 : 영어, 숫자조합으로 5~30글자"));
        userLoginId = new JTextField();
        panel.add(userLoginId);

        panel.add(new JLabel("비밀번호 :"));
        panel.add(new JLabel("(조건 : 대문자,소문자,숫자,특문 포함하여 8글자 ~ 20글자)"));
        userPassword = new JPasswordField();
        panel.add(userPassword);

        panel.add(new JLabel("비밀번호 확인 : "));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        panel.add(new JLabel("이메일"));
        userEmail = new JTextField();
        panel.add(userEmail);

        panel.add(new JLabel("닉네임"));
        panel.add(new Label("(조건 : 한글로 5~30글자"));
        userNickName = new JTextField();
        panel.add(userNickName);

        // 아이디 중복 검사 버튼 추가
        JButton checkIdButton = new JButton("중복 검사");
        checkIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userLoginId.getText();
                if (check(id)) {
                    JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
                    idAvailable = true; // 아이디가 사용 가능한 상태로 변경
                } else {
                    JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
                    idAvailable = false; // 아이디가 이미 존재하는 상태로 변경
                }
            }
        });
        panel.add(checkIdButton);

        // 이메일 중복 검사 버튼 추가
        JButton checkEmailButton = new JButton("중복 검사");
        checkIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userEmail.getText();
                if (checkEmail(email)) {
                    JOptionPane.showMessageDialog(null, "사용 가능한 이메일입니다.");
                    emailAvailable = true; // 이메일이 사용 가능한 상태로 변경
                } else {
                    JOptionPane.showMessageDialog(null, "이미 존재하는 이메일입니다.");
                    emailAvailable = false; // 이메일이 이미 존재하는 상태로 변경
                }
            }
        });
        panel.add(checkEmailButton);


        // 확인 버튼 추가
        JButton signUpButton = new JButton("가입하기");
        panel1.add(signUpButton);
        JButton cancelButton = new JButton("취소");
        panel1.add(cancelButton);

        // 패널을 프레임에 추가
        add(panel, BorderLayout.CENTER);
        add(panel1, BorderLayout.SOUTH);

        // JFrame 설정
        setSize(400, 350);
        setLocationRelativeTo(null); // 화면 가운데 정렬
        setVisible(true);

        // 회원가입
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 각 필드에서 데이터 가져오기
                    String id = userLoginId.getText();
                    // char[]로 가져온 비밀번호를 String으로 변환
                    String password = new String(userPassword.getPassword());
                    String confirmPassword = new String(confirmPasswordField.getPassword());
                    String email = userEmail.getText();
                    String nickName = userNickName.getText();
                    System.out.println(id + " : " + password + ", " + confirmPassword + ", " + email + ", " + nickName);

                    if (!checkId(id)) {
                        JOptionPane.showMessageDialog(null, "영어, 숫자 조합이 5~30글자 이내로 해주세요");
                        return;
                    }

                    if (!checkNickName(id)) {
                        JOptionPane.showMessageDialog(null, "한글 로 5~30글자 이내로 해주세요");
                        return;
                    }

                    // 아이디 중복 체크
                    if (!idAvailable) {
                        JOptionPane.showMessageDialog(null, "아이디 중복을 확인해주세요.");
                        return; // 중복되는 아이디일 경우 회원가입 중지
                    }

                    // 이메일 중복 체크
                    if (!emailAvailable) {
                        JOptionPane.showMessageDialog(null, "이메일 중복을 확인해주세요.");
                        return; // 중복되는 이메일일 경우 회원가입 중지
                    }

                    // 비밀번호 조건 검사
                    if (!checkPw(password) || !checkPw(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8글자 이상 20글자 이하여야 합니다.");
                        return; // 비밀번호 조건 충족하지 않으면 메서드 종료
                    }

                    // 비밀번호 일치 여부 확인
                    if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                        return; // 비밀번호 불일치 시 메서드 종료
                    }

                    // 외부 클래스에 인코드 작업해서 암호화
                    String hashPassword = PasswordEncode.encode(password);
                    System.out.println("암호화 비밀번호 : " + hashPassword);
                    password = hashPassword;

                    System.out.println("회원 가입 성공");

                    UserDTO user = new UserDTO();
                    user.setUserLoginID(id);
                    user.setUserPw(password);
                    user.setUserEmail(email);
                    user.setNickName(nickName);
                    System.out.println(user);

                    int insert = UserDAO.insert(user);

                    if (insert > 0) {
                        JOptionPane.showMessageDialog(null, "회원 가입 성공");
                        dispose(); // 현재 창 닫기
                        mainPage.setVisible(true); // 메인 창 다시 보이기
                    } else {
                        JOptionPane.showMessageDialog(null, "회원 가입 실패");
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // 취소
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 현재 창 닫기
                mainPage.setVisible(true); // 메인 창 다시 보이기
            }
        });
    }

    // 해당 아이디로 있는지 체크
    private static boolean check(String id) {
        try {
            UserDTO select = UserDAO.select(id);
            if (select == null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("이미 존재하는 회원인지 체크 실패 : " + e.getMessage());
            return false;
        }
        return false;
    }

    // 소문자, 대문자, 0~9 숫자, 특수문자 8자리 이상!(소문자, 대문자 같이 안써도 됨!)
    // 위에서 비밀번호를 생성하기위한 조건을 주기위해서 메소드 만들었다!
    private static boolean checkPw(String userpw) {
        String passwordPolicy = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";

        Pattern pattern_pwd = Pattern.compile(passwordPolicy);
        Matcher matcher_pwd = pattern_pwd.matcher(userpw);

        return matcher_pwd.matches();
    }

    // 닉네임 검사
    private static boolean checkNickName(String nickName) {
        String regex = "^[ㄱ-ㅎ가-힣]*$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(nickName);
        if (matcher.matches() && nickName.length() < 5 || nickName.length() >= 30) {
            return true;
        }
        return false;
    }

    // 아이디 검사
    private static boolean checkId(String id) {
        String regex = "\t^[a-zA-Z0-9]*$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(id);
        if (matcher.matches() && id.length() < 5 || id.length() >= 30) {
            return true;
        }
        return false;
    }


    // 이메일 검사
    private static boolean checkEmail(String email) {
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
