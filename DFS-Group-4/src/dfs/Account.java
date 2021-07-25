package dfs;
public class Account {

    private String userName;
    private String password;
    private String securityQuestion;

    public Account(String userName, String password, String securityQuestion) {
        this.userName = userName;
        this.password = password;
        this.securityQuestion = securityQuestion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    @Override
    public String toString() {
        return userName+" "+password+" "+securityQuestion;
    }
}
