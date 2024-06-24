package dao;

import config.JDBCConfig;
import dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet rs;

    // 조회
    public static UserDTO select(String userId) {
        String sql = "SELECT * FROM users WHERE user_login_id = ?";
        UserDTO userDTO = null;
        try {
            connection = JDBCConfig.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            rs = preparedStatement.executeQuery();
            userDTO = new UserDTO();
            while (rs.next()) {
                userDTO.setUserId(rs.getInt("user_id"));
                userDTO.setUserLoginID(rs.getString("user_login_id"));
                userDTO.setUserPw(rs.getString("user_pw"));
                userDTO.setUserEmail(rs.getString("user_email"));
                userDTO.setNickName(rs.getString("nickname"));
                System.out.println(userDTO);
            }
        } catch (Exception e) {
            System.out.println("에러 발생 : " + e.getMessage());
        } finally {
            try {
                JDBCConfig.close(rs, preparedStatement, connection);
            } catch (Exception e) {
                System.out.println("에러 발생 : " + e.getMessage());
            }
        }
        return userDTO;
    }

    // 이메일로 조회
    public static UserDTO selectByEmail(String email) {
        String sql = "SELECT * FROM users WHERE user_email = ?";
        UserDTO userDTO = null;
        try {
            connection = JDBCConfig.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            userDTO = new UserDTO();
            while (rs.next()) {
                userDTO.setUserId(rs.getInt("user_id"));
                userDTO.setUserLoginID(rs.getString("user_login_id"));
                userDTO.setUserPw(rs.getString("user_pw"));
                userDTO.setUserEmail(rs.getString("user_email"));
                userDTO.setNickName(rs.getString("nickname"));
                System.out.println(userDTO);
            }
        } catch (Exception e) {
            System.out.println("에러 발생 : " + e.getMessage());
        } finally {
            try {
                JDBCConfig.close(rs, preparedStatement, connection);
            } catch (Exception e) {
                System.out.println("에러 발생 : " + e.getMessage());
            }
        }
        return userDTO;
    }


    // 유저 등록
    public static int insert(UserDTO user) {
        String sql = "INSERT INTO users (user_login_id, user_pw, user_email, nickname) VALUES (?, ?, ?, ?)";
        return connectDB(sql, user);
    }

    // 유저 수정
    public static int update(UserDTO user, int userId) {
        String sql = "UPDATE users SET user_pw = ?, user_email = ?, nickname = ? WHERE user_id = ?";
        return connectDB(sql, user, userId);
    }

    // DB에 등록, 수정, 삭제하는 메서드
    private static int connectDB(String sql, Object... params) {
        try {
            connection = JDBCConfig.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            int re = preparedStatement.executeUpdate();
            if (re > 0) {
                System.out.println("실행 성공");
                return re;
            } else {
                System.out.println("실행 실패");
                return -1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCConfig.close(preparedStatement, connection);
            } catch (Exception e) {
                System.out.println("에러 발생 : " + e.getMessage());
            }
        }
    }
}
