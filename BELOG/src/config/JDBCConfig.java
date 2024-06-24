package config;

import java.sql.*;

public class JDBCConfig {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    public static final String USERNAME = "c##madang";
    public static final String PASSWORD = "madang";

    // static 메서드이며 매개변수는 없고 커넥션을 변환하는 메서드
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("에러 발생 : " + e.getMessage());
        }
        return connection;
    }

    // 매개변수 connection, statement, resultset을 받아서 클로즈, 반환 없음
    public static void close(ResultSet resultSet,
                             PreparedStatement preparedStatement,
                             Connection connection
    ) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("에러 발생 : " + e.getMessage());
        }
    }

    public static void close(PreparedStatement preparedStatement,
                             Connection connection) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("에러 발생 : " + e.getMessage());
        }
    }
}
