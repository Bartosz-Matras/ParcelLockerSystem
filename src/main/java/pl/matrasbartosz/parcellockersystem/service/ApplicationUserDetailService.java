package pl.matrasbartosz.parcellockersystem.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matrasbartosz.parcellockersystem.entity.user.ApplicationUserDetails;
import pl.matrasbartosz.parcellockersystem.entity.user.User;
import pl.matrasbartosz.parcellockersystem.repository.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(ApplicationUserDetailService.class);

    private final UserRepository userRepository;
    private final AttemptService attemptService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Optional<User> userByEmail = this.userRepository.findUserByEmail(email);
        if (userByEmail.isPresent()) {
            this.attemptService.updateAttempt(email);
            return new ApplicationUserDetails(userByEmail.get());
        }
        logger.error("User not found: {}", email);
        throw new UsernameNotFoundException("User not found: " + email);
    }
}
