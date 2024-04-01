package vn.hanu.fit.sqa.group3.act1.model;

public class UserDto {
    public UserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    private String username;
    private String password;
    private String email;
}
