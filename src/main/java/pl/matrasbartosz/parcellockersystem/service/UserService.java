package pl.matrasbartosz.parcellockersystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matrasbartosz.parcellockersystem.entity.user.User;
import pl.matrasbartosz.parcellockersystem.entity.user.mappers.UserDTO;
import pl.matrasbartosz.parcellockersystem.exceptions.user.UserAlreadyExistsException;
import pl.matrasbartosz.parcellockersystem.repository.user.UserRepository;
import pl.matrasbartosz.parcellockersystem.repository.user.roles.RolesRepository;

import java.util.Set;

import static pl.matrasbartosz.parcellockersystem.entity.user.mappers.AttemptMapper.createAttempt;
import static pl.matrasbartosz.parcellockersystem.entity.user.mappers.UserMapper.mapUserDTOToUser;
import static pl.matrasbartosz.parcellockersystem.entity.user.roles.ApplicationUserRole.*;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String USER_ALREADY_EXISTS = "User with given email or phone number already exists.";
    private final UserRepository userRepository;
    private final AttemptService attemptService;
    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createCustomer(UserDTO userDTO) {
        this.userRepository.findUserByEmailAndPhoneNumber(userDTO.email(), userDTO.phoneNumber()).ifPresent(user -> {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        });
        User user = mapUserDTOToUser(userDTO);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(this.rolesRepository.findRolesByName(CUSTOMER.name())));
        this.attemptService.createAttempt(createAttempt(userDTO.email(), 0));
        return userRepository.save(user);
    }

    @Transactional
    public User createWorker(UserDTO userDTO) {
        this.userRepository.findUserByEmailAndPhoneNumber(userDTO.email(), userDTO.phoneNumber()).ifPresent(user -> {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        });
        User user = mapUserDTOToUser(userDTO);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(this.rolesRepository.findRolesByName(WORKER.name())));
        this.attemptService.createAttempt(createAttempt(userDTO.email(), 0));
        return userRepository.save(user);
    }

}
