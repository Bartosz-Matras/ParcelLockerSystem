package pl.matrasbartosz.parcellockersystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.matrasbartosz.parcellockersystem.entity.user.UserDTO;
import pl.matrasbartosz.parcellockersystem.exceptions.user.UserAlreadyExistsException;
import pl.matrasbartosz.parcellockersystem.service.UserService;

import static pl.matrasbartosz.parcellockersystem.entity.user.UserDTOMapper.mapUserToUserDTO;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Add user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUserDTO = mapUserToUserDTO(this.userService.createUser(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleNumberFormatException() {
        return ResponseEntity.badRequest().body("User with given email already exists.");
    }
}
