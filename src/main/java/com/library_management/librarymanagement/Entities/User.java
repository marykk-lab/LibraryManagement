package com.library_management.librarymanagement.Entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a user in the library management system.
 * Maps to the "users" table in the database.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String city;

    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Borrow> borrowSet = new HashSet<>();

    /**
     * Constructs a new User with all attributes specified.
     *
     * @param userID The unique identifier for the user
     * @param username The user's username (must be unique)
     * @param password The user's password
     * @param email The user's email address (must be unique)
     * @param phone The user's phone number
     * @param city The user's city of residence
     * @param role The user's role in the system
     */
    public User(Long userID, String username, String password, String email, String phone, String city, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.role = role;
    }

    /**
     * Constructs a new User without specifying an ID.
     *
     * @param username The user's username (must be unique)
     * @param password The user's password
     * @param email The user's email address (must be unique)
     * @param phone The user's phone number
     * @param city The user's city of residence
     * @param role The user's role in the system
     */
    public User(String username, String password, String email, String phone, String city, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.role = role;
    }

    /**
     * Default constructor required by JPA.
     */
    public User() {
    }

    /**
     * Adds a borrow record to this user's borrow history.
     *
     * @param borrow The borrow record to add
     * @return true if the borrow was successfully added, false otherwise
     */
    public boolean addBorrow(Borrow borrow) {
        return borrowSet.add(borrow);
    }

    /**
     * Gets the user's ID.
     *
     * @return The unique identifier of the user
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Sets the user's ID.
     *
     * @param userID The new ID for the user
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * Gets the user's username.
     *
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The new username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The new password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's email address.
     *
     * @return The email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The new email address for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's role.
     *
     * @return The role of the user in the system
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The new role for the user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the user's phone number.
     *
     * @return The phone number of the user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phone The new phone number for the user
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the user's city of residence.
     *
     * @return The city where the user resides
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the user's city of residence.
     *
     * @param city The new city for the user
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the set of borrow records associated with this user.
     *
     * @return A set of Borrow entities associated with this user
     */
    public Set<Borrow> getBorrowSet() {
        return borrowSet;
    }

    /**
     * Sets the collection of borrow records for this user.
     *
     * @param borrowSet The new set of borrow records for this user
     */
    public void setBorrowSet(Set<Borrow> borrowSet) {
        this.borrowSet = borrowSet;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string containing the user's ID, username, password, email, phone, city, and role
     */
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}