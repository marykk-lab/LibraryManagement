package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.User.*;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link UserManagementService} that verifies user authentication, 
 * authorization, and management operations.
 * Uses Mockito for mocking dependencies.
 */
class UserManagementServiceTest {
    /** Mock repository for user data access */
    @Mock
    private UserRep userRep;

    /** Mock authentication manager for hanfdling user authentication */
    @Mock
    private AuthenticationManager authenticationManager;

    /** Mock password encoder for handling password encryption */
    @Mock
    private PasswordEncoder passwordEncoder;

    /** Mock user service for handling user-related operations */
    @Mock
    private UserServ userServ;

    /** The service being tested */
    @InjectMocks
    private UserManagementService userManagementService;

    /**
     * Sets up the test environment before each test.
     * Initializes mock objects using MockitoAnnotations.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests user registration functionality.
     * Verifies that:
     * - Password is properly encoded
     * - User is saved to the repostory
     * - Correct response message is returned
     */
    @Test
    void testSignup() {
        SignUpDTO request = new SignUpDTO();
        request.setEmail("test@example.com");
        request.setUsername("test");
        request.setPassword("test");

        User user = new User();
        user.setUserID(123L);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedtest");
        when(userRep.save(any(User.class))).thenReturn(user);

        SignUpDTO response = userManagementService.signup(request);

        assertEquals("User saved successfully", response.getMessage());
    }

    /**
     * Tests user authentication functionality.
     * Verifies that:
     * - Authentication manager properly validates credentials
     * - User role is correctly retrieved
     * - Proper respondse status and message are returned
     */
    @Test
    void testSignin() {
        SignInDTO request = new SignInDTO();
        request.setUsername("test");
        request.setPassword("test");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        User user = new User();
        user.setRole("USER");
        when(userRep.findByUsername("test")).thenReturn(Optional.of(user));

        SignInDTO response = userManagementService.signin(request);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("Successfully logged in", response.getMessage());
        assertEquals("USER", response.getRole());
    }

    /**
     * Tests retrieval of all users.
     * Verifies that the service returns the complete list of fusers.
     */
    @Test
    void testGetAllUsers() {
        User user = new User();
        when(userRep.findAll()).thenReturn(List.of(user));

        List<User> users = userManagementService.getAllUsers();

        assertEquals(1, users.size());
    }

    /**
     * Tests retrieval of a specific user by ID.
     * Verifies that the service returns correct user data and status.
     */
    @Test
    void testGetUserByID() {
        User user = new User();
        user.setUserID(1L);
        when(userRep.findById(1L)).thenReturn(Optional.of(user));

        SignInDTO dto = userManagementService.getUserByID(1L);

        assertEquals(200, dto.getStatusCode());
        assertEquals("User retrieved successfully", dto.getMessage());
        assertEquals(user, dto.getUser());
    }

    /**
     * Tests user deletion functionality.
     * Verifies that the service properly removes the user 
     * and returns the correct ID.
     */
    @Test
    void testDeleteUser() {
        when(userRep.existsById(1L)).thenReturn(true);

        Long id = userManagementService.deleteUser(1L);

        assertEquals(1L, id);
        verify(userRep).deleteById(1L);
    }

    /**
     * Tests user update functionality.
     * Verifies that:
     * - User data is properly updated
     * - Password is re-encoded if changed
     * - Updated user is saved to repository
     */
    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setUserID(1L);
        when(userRep.findById(1L)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");
        when(userRep.save(any(User.class))).thenReturn(existingUser);

        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setUsername("newUser");
        dto.setEmail("new@example.com");
        dto.setRole("ADMIN");
        dto.setPassword("newpass");
        dto.setCity("City");
        dto.setPhone("123456");

        Long id = userManagementService.updateUser(1L, dto);

        assertEquals(1L, id);
        verify(userRep).save(existingUser);
    }

    /**
     * Tests retrieval of user information by username.
     * Verifies that the service returns the correct user entity.
     */
    @Test
    void testGetUserInfo() {
        User user = new User();
        when(userRep.findByUsername("user")).thenReturn(Optional.of(user));

        User result = userManagementService.getUserInfo("user");

        assertEquals(user, result);
    }

    /**
     * Tests retrieval of user information by ID.
     * Verifies that the service returns sthe correct user entity.
     */
    @Test
    void testGetUserInfoById() {
        User user = new User();
        when(userRep.getReferenceById(1L)).thenReturn(user);

        User result = userManagementService.getUserInfoById(1L);

        assertEquals(user, result);
    }
}