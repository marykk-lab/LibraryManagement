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

class UserManagementServiceTest {
    @Mock
    private UserRep userRep;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserServ userServ;

    @InjectMocks
    private UserManagementService userManagementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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


    @Test
    void testGetAllUsers() {
        User user = new User();
        when(userRep.findAll()).thenReturn(List.of(user));

        List<User> users = userManagementService.getAllUsers();

        assertEquals(1, users.size());
    }


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


    @Test
    void testDeleteUser() {
        when(userRep.existsById(1L)).thenReturn(true);

        Long id = userManagementService.deleteUser(1L);

        assertEquals(1L, id);
        verify(userRep).deleteById(1L);
    }


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


    @Test
    void testGetUserInfo() {
        User user = new User();
        when(userRep.findByUsername("user")).thenReturn(Optional.of(user));

        User result = userManagementService.getUserInfo("user");

        assertEquals(user, result);
    }


    @Test
    void testGetUserInfoById() {
        User user = new User();
        when(userRep.getReferenceById(1L)).thenReturn(user);

        User result = userManagementService.getUserInfoById(1L);

        assertEquals(user, result);
    }

}