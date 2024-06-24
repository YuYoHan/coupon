package dto;

public class UserDTO {
    private int userId;
    private String userLoginID;
    private String userPw;
    private String userEmail;
    private String nickName;

    public UserDTO() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLoginID() {
        return userLoginID;
    }

    public void setUserLoginID(String userLoginID) {
        this.userLoginID = userLoginID;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userLoginID='" + userLoginID + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
