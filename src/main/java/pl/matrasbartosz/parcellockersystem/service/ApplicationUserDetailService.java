package pl.matrasbartosz.parcellockersystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matrasbartosz.parcellockersystem.entity.user.ApplicationUserDetails;
import pl.matrasbartosz.parcellockersystem.entity.user.Attempts;
import pl.matrasbartosz.parcellockersystem.entity.user.User;
import pl.matrasbartosz.parcellockersystem.repository.UserRepository;
import pl.matrasbartosz.parcellockersystem.repository.user.AttemptsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AttemptsRepository attemptsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new ApplicationUserDetails(optionalUser.get());
    }
}
