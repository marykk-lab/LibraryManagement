package com.library_management.librarymanagement.Security;

import com.library_management.librarymanagement.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementation of Spring Security's UserDetails interface.
 * Provides core user information for authentication and authorization.
 */
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    /**
     * Constructs a new UserDetailsImpl with all attributes specified.
     *
     * @param id The unique identifier of the user
     * @param username The username for authentication
     * @param email The user's email address
     * @param password The user's encrypted password
     * @param role The user's role for authorization
     */
    public UserDetailsImpl(Long id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Creates a UserDetailsImpl instance from a User entity.
     *
     * @param user The User entity to convert
     * @return A new UserDetailsImpl instance with the user's details
     */
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getUserID(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    /**
     * Gets the user's ID.
     *
     * @return The unique identifier of the user
     */
    public Long getId() {
        return id;
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
     * Gets the authorities granted to the user.
     * Converts the user's role into a Spring Security GrantedAuthority.
     *
     * @return A collection containing the user's authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    /**
     * Gets the user's password.
     *
     * @return The password used for authentication
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets the username.
     *
     * @return The username used for authentication
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true as account expiration is not implemented
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     *
     * @return true as account locking is not implemented
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials have expired.
     *
     * @return true as credential expiration is not implemented
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     *
     * @return true as user disabling is not implemented
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}