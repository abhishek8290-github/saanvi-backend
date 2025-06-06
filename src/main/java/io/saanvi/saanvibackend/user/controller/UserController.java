
package io.saanvi.saanvibackend.user.controller;


import io.saanvi.saanvibackend.user.dto.UserRequestDto;
import io.saanvi.saanvibackend.user.mapper.UserMapper;
import io.saanvi.saanvibackend.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.saanvi.saanvibackend.user.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUsers(@PathVariable String id) throws Exception {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public Optional<User> createUser(@RequestBody UserRequestDto request) throws Exception {
        User userToCreate = UserMapper.toEntity(request);
        userService.save(userToCreate); 
        return Optional.of(userToCreate);
    }






}
