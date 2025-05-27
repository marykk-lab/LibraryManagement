package com.library_management.librarymanagement.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


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

    @Column(nullable = false,unique = true)
    private String email;

    private String phone;
    private String city;

    private String role;
    @OneToMany(mappedBy = "user")
    private Set<Borrow> borrowSet = new HashSet<>();

    public User(Long userID, String username, String password, String email, String phone, String city, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.role = role;
    }

    public User(String username, String password, String email, String phone, String city, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.role = role;
    }

    public User() {
    }

    public boolean addBorrow(Borrow borrow) {
        return borrowSet.add(borrow);
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Borrow> getBorrowSet() {
        return borrowSet;
    }

    public void setBorrowSet(Set<Borrow> borrowSet) {
        this.borrowSet = borrowSet;
    }

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
