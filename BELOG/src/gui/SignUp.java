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
    // 로그인 유저 아이디
    private HintTextField userLoginId;
    // 비밀번호
    private HintPasswordField userPassword;
    // 비밀번호 확인
    private HintPasswordField confirmPasswordField;
    // 유저 이메일
    private HintTextField userEmail;
    // 유저 닉네임
    private HintTextField userNickName;
    // 아이디와 이메일 체크시 확인하는 변수들
    private boolean idAvailable = false;
    private boolean emailAvailable = false;

    public SignUp(Index mainPage) {

        // 회원가입 구현할 패널 생성
        JPanel mainPanel = new JPanel();
        // 세로로 쌓이도록 설정
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // 로그인 부분
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        JLabel loginLabel = new JLabel("<HTML><U>로그인 화면</U></HTML>");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topPanel.add(loginLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);


        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new Login(mainPage);
            }
        });
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        // 제목 추가
        JLabel titleLabel = new JLabel("BELOG");
        titleLabel.setFont(new Font("Spoqa Sans", Font.BOLD, 24));
        // 가운데 정렬
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        // 공백 추가
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // 회원 가입할 때 입력할 칸
        JPanel fieldPanel =
                new JPanel(new GridBagLayout());
        fieldPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // 여백 설정

        userLoginId = new HintTextField("아이디");
        // 예시로 너비 170, 높이 30 설정
        userLoginId.setPreferredSize(new Dimension(170, 30));
        gbc.gridwidth = 2; // 아이디 필드는 2개의 열을 차지하도록 설정
        fieldPanel.add(userLoginId, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1; // 다음 요소는 한 열만 차지하도록 다시 설정
        JButton checkIdButton = new JButton("중복 검사");
        checkIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userLoginId.getText();
                if (check(id)) {
                    JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
                    idAvailable = true;
                } else if (id == null || id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
                    idAvailable = false;
                } else {
                    JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
                    idAvailable = false;
                }
            }
        });
        checkIdButton.setBackground(new Color(128, 0, 128));
        checkIdButton.setForeground(Color.WHITE);
        checkIdButton.setFocusPainted(false);
        fieldPanel.add(checkIdButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userPassword = new HintPasswordField("비밀번호");
        userPassword.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userPassword, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        confirmPasswordField = new HintPasswordField("비밀번호 확인");
        confirmPasswordField.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userEmail = new HintTextField("이메일");
        userEmail.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userEmail, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        JButton checkEmailButton = new JButton("중복 검사");
        checkEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userEmail.getText();
                if (checkEmail(email)) {
                    JOptionPane.showMessageDialog(null, "사용가능한 이메일입니다.");
                    emailAvailable = true;
                } else if (email == null || email.isEmpty()) {
                    System.out.println(email);
                    JOptionPane.showMessageDialog(null, "이메일을 입력하세요");
                    emailAvailable = false;
                } else {
                    JOptionPane.showMessageDialog(null, "이미 존재하는 이메일입니다.");
                    emailAvailable = false;
                }
            }
        });
        checkEmailButton.setBackground(new Color(128, 0, 128));
        checkEmailButton.setForeground(Color.WHITE);
        checkEmailButton.setFocusPainted(false);
        fieldPanel.add(checkEmailButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        userNickName = new HintTextField("닉네임");
        userNickName.setPreferredSize(new Dimension(170, 30));
        fieldPanel.add(userNickName, gbc);


        mainPanel.add(fieldPanel, BorderLayout.CENTER);


        // 확인 버튼 추가
        JButton signUpButton = new JButton("등록");
        signUpButton.setBackground(new Color(128, 0, 128)); // 보라색
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(signUpButton);

        JLabel label = new JLabel();
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.add(label);

        // 패널을 프레임에 추가
        add(mainPanel);
        add(footerPanel, BorderLayout.SOUTH);

        // JFrame 설정
        setTitle("회원가입");
        setSize(400, 500);
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


                    if (!checkNickName(id)) {
                        JOptionPane.showMessageDialog(null, "한글로 5~30글자 이내로 해주세요");
                        return;
                    }

                    // 아이디 중복 체크
                    if (!idAvailable) {
                        JOptionPane.showMessageDialog(null, "아이디를 확인해주세요.");
                        return; // 중복되는 아이디일 경우 회원가입 중지
                    }

                    // 이메일 중복 체크
                    if (!emailAvailable || email == null) {
                        JOptionPane.showMessageDialog(null, "이메일를 확인해주세요.");
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

                    // 닉네임 체크
                    if (!checkNickName(nickName)) {
                        JOptionPane.showMessageDialog(null, "닉네임을 확인하세요");
                        return;
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

                    // DB에 insert문
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

    }


    // 해당 아이디가 있는지 그리고 조건에 맞는지 체크
    private static boolean check(String id) {
        try {
            // DB에 해당 아이디가 있는지 조회
            UserDTO findUser = UserDAO.select(id);
            // 영어와 숫자로만 아이디를 작성하게 정규식 설정
            String regex = "\t^[a-zA-Z0-9]*$";
            Pattern compile = Pattern.compile(regex);
            Matcher matcher = compile.matcher(id);

            // 이메일이 없고 정규식 조건에 맞고 5~30글자만 true
            if (findUser == null) {
                if (matcher.matches() &&
                        id.length() <= 5 ||
                        id.length() >= 30) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("이미 존재하는 회원인지 체크 실패 : " + e.getMessage());
            return false;
        }
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

        UserDTO findNickName = UserDAO.selectByNickName(nickName);

        if (findNickName == null) {
            if (matcher.matches() &&
                    nickName.length() <= 5 ||
                    nickName.length() >= 30) {
                return true;
            }
        }
        return false;
    }


    // 이메일 검사
    private static boolean checkEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern patternEmail = Pattern.compile(emailPattern);
        Matcher matcherEmail = patternEmail.matcher(email);
        // 해당 이메일이 있는지 체크
        UserDTO findEmail = UserDAO.selectByEmail(email);

        if (matcherEmail.matches() && findEmail == null) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new SignUp(new Index());
    }
}
