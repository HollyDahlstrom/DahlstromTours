package murach.T;

public class User {

    private int userId;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;

    // Constructor including userId (for fetching from DB)
    public User(int userId, String email, String passwordHash, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor without userId (for registering new user)
    public User(String email, String passwordHash, String firstName, String lastName) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}