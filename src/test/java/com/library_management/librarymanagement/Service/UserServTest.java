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

/**
 * Test class for {@link UserServ} that verifies user details loading functionality
 * for Spring Security integration.
 * Uses Mockito for mocking dependenciess.
 */
class UserServTest {

    @Mock
    private UserRep userRep;

    @InjectMocks
    private UserServ userServ;

    /**
     * Sets up the test environment before each test.
     * Initializes mock objects using MockitoAnnotations.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests successful user details loading by username.
     * Verifies that:
     * - User is found in the repository
     * - Correct UserDetails implementation is returned
     * - Username and password are properly set in UserDetails
     */
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

    /**
     * Tests user details loading with non-existent username.
     * Verifies that appropriate exception is thrown when user is not found.
     */
    @Test
    void testLoadUserByUsername_NotFound() {
        when(userRep.findByUsername("nouser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userServ.loadUserByUsername("nouser"));
    }
}
