package config;

// 세션 역할
public class SessionManager {
    private static String currentUser;

    // 로그인 시 아이디 정보를 넣어줌
    public static void loginUser(String userName){
        currentUser = userName;
    }

    // 로그아웃시 접속중인 아이디를 null로 바꿔줌
    public static void logoutUser() {
        currentUser = null;
    }

    // 현재 로그인 중인지 확인
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    // 현재 접속중인 아이디가 뭔지 확인
    public static String getCurrentUser() {
        return currentUser;
    }
}
