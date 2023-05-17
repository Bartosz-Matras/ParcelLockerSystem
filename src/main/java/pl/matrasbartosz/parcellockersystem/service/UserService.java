package pl.matrasbartosz.parcellockersystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matrasbartosz.parcellockersystem.entity.user.User;
import pl.matrasbartosz.parcellockersystem.entity.user.UserDTO;
import pl.matrasbartosz.parcellockersystem.entity.user.UserDTOMapper;
import pl.matrasbartosz.parcellockersystem.exceptions.user.UserAlreadyExistsException;
import pl.matrasbartosz.parcellockersystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static pl.matrasbartosz.parcellockersystem.entity.user.UserDTOMapper.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

//    public List<User> getUsers() {
//        return userRepository.findAllUsers(PageRequest.of(0, 5));
//    }

    @Transactional
    public User createUser(UserDTO userDTO) throws UserAlreadyExistsException{
        Optional<User> userInDb = userRepository.findUserByEmailAndPhoneNumber(userDTO.getEmail(), userDTO.getPhoneNumber());
        if (userInDb.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }else {
            User user = mapUserDTOToUser(userDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

}
