package pl.matrasbartosz.parcellockersystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.matrasbartosz.parcellockersystem.entity.user.mappers.UserDTO;
import pl.matrasbartosz.parcellockersystem.service.UserService;

import java.net.URI;

import static pl.matrasbartosz.parcellockersystem.entity.user.mappers.UserMapper.mapUserToUserDTO;

@RestController
@RequestMapping("/api/v1/workers")
@RequiredArgsConstructor
@Tag(name = "Workers")
@SecurityRequirement(name = "parcellockersystem")
public class WorkerController {

    private final Logger logger = LoggerFactory.getLogger(WorkerController.class);

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create worker - WORKER")
    public ResponseEntity<UserDTO> createWorker(@RequestBody UserDTO userDTO) {
        UserDTO createdUserDTO = mapUserToUserDTO(this.userService.createWorker(userDTO));
        logger.info("Created user: {}", createdUserDTO);
        return ResponseEntity.created(URI.create("api/v1/worker/" + createdUserDTO.id())).body(createdUserDTO);
    }

}
