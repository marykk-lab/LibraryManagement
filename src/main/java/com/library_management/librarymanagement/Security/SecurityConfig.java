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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserServ userServ;
    public SecurityConfig(UserServ userServ) {
        this.userServ = userServ;
    }

    public SecurityConfig() {
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userServ);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


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
                                            "/book/admin/**").hasRole("ADMIN")
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