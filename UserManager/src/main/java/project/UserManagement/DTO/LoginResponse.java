package project.UserManagement.DTO;

public class LoginResponse {
    private String token;
    private int userId;

    public LoginResponse(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
