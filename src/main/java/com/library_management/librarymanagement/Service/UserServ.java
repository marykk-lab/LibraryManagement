package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class implementing Spring Security's UserDetailsService.
 * Provides user authentication and retrieval functionality.
 */
@Service
public class UserServ implements UserDetailsService {
    @Autowired
    private UserRep userRep;

    /**
     * Loads user details by username for Spring Security authentication.
     * Converts the application's User entity to Spring Security's UserDetails.
     *
     * @param username The username to search for
     * @return UserDetails implementation containing the user's security information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new UserDetailsImpl(
                user.getUserID(),
                user.getUsername(),
                user.getPassword(),
                user.getPassword(),
                user.getRole()
        );
    }
}