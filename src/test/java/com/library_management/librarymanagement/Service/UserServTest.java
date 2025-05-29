package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServTest {

    @Mock
    private UserRep userRep;

    @InjectMocks
    private UserServ userServ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_Found() {
        User user = new User();
        user.setUserID(1L);
        user.setUsername("test");
        user.setPassword("test");
        user.setRole("USER");
        when(userRep.findByUsername("test")).thenReturn(Optional.of(user));

        UserDetails details = userServ.loadUserByUsername("test");
        assertTrue(details instanceof UserDetailsImpl);
        assertEquals("test", details.getUsername());
        assertEquals("test", details.getPassword());
    }

    @Test
    void testLoadUserByUsername_NotFound() {
        when(userRep.findByUsername("nouser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userServ.loadUserByUsername("nouser"));
    }
}
