package project;

public abstract class User {

    private String username;
    private String password;
    private String name;
    private String email;

    public User(String username, String password, String name, String email) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

public void setEmail(String email) {
    if (email.contains("@")) {
        this.email = email;
    } else {
        throw new IllegalArgumentException("Invalid email address.");
    }
}

public boolean login(String inputUsername, String inputPassword) {
    return username.equals(inputUsername) && password.equals(inputPassword);
}

    @Override
    public String toString() {
        return "username: " + username + ", password: " + password + ", name: " + name + ", email: " + email + '.';
    }

    public abstract void menu();

}
