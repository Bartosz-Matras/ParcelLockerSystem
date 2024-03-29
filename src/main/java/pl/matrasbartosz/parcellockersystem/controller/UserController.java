package pl.matrasbartosz.parcellockersystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.matrasbartosz.parcellockersystem.entity.user.mappers.UserDTO;
import pl.matrasbartosz.parcellockersystem.service.UserService;

import java.net.URI;

import static pl.matrasbartosz.parcellockersystem.entity.user.mappers.UserMapper.mapUserToUserDTO;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users")
@SecurityRequirement(name = "parcellockersystem")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create user - CUSTOMER")
    public ResponseEntity<UserDTO> createCustomer(@RequestBody UserDTO userDTO) {
        UserDTO createdUserDTO = mapUserToUserDTO(this.userService.createCustomer(userDTO));
        logger.info("Created user: {}", createdUserDTO);
        return ResponseEntity.created(URI.create("/api/v1/users/" + createdUserDTO.id())).body(createdUserDTO);
    }

}
