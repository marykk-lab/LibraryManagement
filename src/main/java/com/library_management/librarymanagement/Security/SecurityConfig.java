package com.library_management.librarymanagement.Security;

import com.library_management.librarymanagement.Service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Configuration class for Spring Security settings.
 * Defines security rules, authentication providers, and other security-related beans.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserServ userServ;

    /**
     * Constructs SecurityConfig with user service.
     *
     * @param userServ The user service for authentication
     */
    public SecurityConfig(UserServ userServ) {
        this.userServ = userServ;
    }

    /**
     * Default constructor required by Spring.
     */
    public SecurityConfig() {
    }

    /**
     * Creates a password encoder bean for secure password handling.
     *
     * @return BCryptPasswordEncoder instance for password encryption
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an authentication manager bean.
     *
     * @param authenticationConfiguration The authentication configuration
     * @return AuthenticationManager instance
     * @throws Exception if authentication manager cannot be created
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates and configures the authentication provider.
     * Sets up the user details service and password encoder.
     *
     * @return Configured DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userServ);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configures the security filter chain with specific security rules.
     * Sets up:
     * - CSRF protection
     * - CORS configuration
     * - Login/logout behavior
     * - URL-based security rules
     * - Session management
     * - Authentication provider
     *
     * @param http HttpSecurity to be configured
     * @return Configured SecurityFilterChain
     * @throws Exception if security configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.applyPermitDefaultValues();
                    config.setAllowCredentials(true);
                    return config;
                }))
                .formLogin(form -> form
                        .loginPage("/auth/signin")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/signin", "/signup", "/auth/**", "/auth/rest/signup", "/auth/rest/signin").permitAll()
                        .requestMatchers("/api/book/admin/**", "/api/author/admin/**", "/api/borrow/admin", "/admin/**",
                                "/book/admin/**", "/author/admin/**", "/borrow/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .authenticationProvider(authenticationProvider());

        return http.build();
    }
}