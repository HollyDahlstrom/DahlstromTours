/**
 * User.java - DahlstromTours
 * 
 * Represents a user of the DahlstromTours web application.
 * This class contains user information such as email, hashed password,
 * first and last names, and a unique user ID.
 * 
 * Constructors:
 *   - User(int userId, String email, String passwordHash, String firstName, String lastName):
 *       Used when fetching an existing user from the database, including their ID.
 *   - User(String email, String passwordHash, String firstName, String lastName):
 *       Used when creating a new user for registration (userId is not yet assigned).
 * 
 * Getters:
 *   - getUserId(): Returns the user's unique ID.
 *   - getEmail(): Returns the user's email address.
 *   - getPasswordHash(): Returns the user's hashed password.
 *   - getFirstName(): Returns the user's first name.
 *   - getLastName(): Returns the user's last name.
 * 
 * This class is primarily used for handling user registration, login, and session management.
 */

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