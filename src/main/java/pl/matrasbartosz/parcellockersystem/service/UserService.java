package pl.matrasbartosz.parcellockersystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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


    public List<User> getUsers() {
        return userRepository.findAllUsers(PageRequest.of(0, 5));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User createUser(UserDTO userDTO) throws UserAlreadyExistsException{
        Optional<User> userInDb = userRepository.findUserByEmail(userDTO.getEmail());
        if (userInDb.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }else {
            return userRepository.save(mapUserDTOToUser(userDTO));
        }
    }

}
