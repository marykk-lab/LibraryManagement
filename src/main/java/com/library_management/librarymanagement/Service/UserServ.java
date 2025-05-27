package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServ implements UserDetailsService {
    @Autowired
    private UserRep userRep;
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
