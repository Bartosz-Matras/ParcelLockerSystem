package pl.matrasbartosz.parcellockersystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import pl.matrasbartosz.parcellockersystem.jwt.JwtConfig;
import pl.matrasbartosz.parcellockersystem.jwt.JwtTokenVerifier;
import pl.matrasbartosz.parcellockersystem.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import pl.matrasbartosz.parcellockersystem.service.ApplicationUserDetailService;

import javax.crypto.SecretKey;

import static pl.matrasbartosz.parcellockersystem.entity.user.roles.ApplicationUserRole.ADMIN;
import static pl.matrasbartosz.parcellockersystem.entity.user.roles.ApplicationUserRole.CUSTOMER;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity // true by default
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailService applicationUserDetailService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(authenticationConfiguration), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                )
                .permitAll()

                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAnyAuthority(CUSTOMER.name(), ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/users/customer").hasAuthority(CUSTOMER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/users/admin").hasAuthority(ADMIN.name())

                .anyRequest().authenticated();
        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserDetailService);
        return provider;
    }
}
