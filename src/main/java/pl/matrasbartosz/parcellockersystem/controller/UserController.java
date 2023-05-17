package pl.matrasbartosz.parcellockersystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.matrasbartosz.parcellockersystem.entity.user.User;
import pl.matrasbartosz.parcellockersystem.entity.user.UserDTO;
import pl.matrasbartosz.parcellockersystem.exceptions.user.UserAlreadyExistsException;
import pl.matrasbartosz.parcellockersystem.service.UserService;


import java.net.URI;

import static pl.matrasbartosz.parcellockersystem.entity.user.UserDTOMapper.mapUserToUserDTO;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users")
@SecurityRequirement(name = "parcellockersystem")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;


    @PostMapping
    @Operation(summary = "Create user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User returnedUser = this.userService.createUser(userDTO);
        UserDTO createdUserDTO = mapUserToUserDTO(returnedUser);
        logger.info("Created user: {}", createdUserDTO);
        return ResponseEntity.created(URI.create("/api/v1/users/" + returnedUser.getId())).body(createdUserDTO);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleNumberFormatException() {
        logger.info("User with given email or phone number already exists.");
        return ResponseEntity.badRequest().body("User with given email or phone number already exists.");
    }
}
