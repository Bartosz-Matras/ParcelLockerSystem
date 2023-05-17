package pl.matrasbartosz.parcellockersystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.matrasbartosz.parcellockersystem.service.ApplicationUserDetailService;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity // true by default
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailService applicationUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
            )
            .authorizeHttpRequests().anyRequest().authenticated().and()
                .formLogin()
                .permitAll()
            .and()
            .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
        return http.build();
    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        builder.authenticationProvider(daoAuthenticationProvider());
//        return builder.build();
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserDetailService);
        return provider;
    }
}
