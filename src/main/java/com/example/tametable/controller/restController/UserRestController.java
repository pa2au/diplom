package com.example.tametable.controller.restController;

import com.example.tametable.DTO.ChangeUserDTO;
import com.example.tametable.DTO.CreateUserDTO;
import com.example.tametable.DTO.ResetPasswordUserDTO;
import com.example.tametable.entity.Role;
import com.example.tametable.entity.User;
import com.example.tametable.service.UserService;
import com.example.tametable.service.VerifyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final VerifyUserService verifyUserService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers(@RequestParam Role role) {
        List<User> users = userService.findAllStudentsByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody CreateUserDTO userDTO) {

        Optional<User> userByUsername = userService.findUserByUsername(userDTO);
        Optional<User> userByEmail = userService.findUserByEmail(userDTO);

        if (userByEmail.isPresent()) {
            return new ResponseEntity<>("Пользователь с таким email уже существует!", HttpStatus.BAD_REQUEST);
        } else if (userByUsername.isPresent()) {
            return new ResponseEntity<>("Пользователь с таким логином уже существует!", HttpStatus.BAD_REQUEST);
        }

        if (userDTO.getRole().equals("TEACHER")) {
            userService.saveTeacher(userDTO);
        } else {
            userService.saveStudent(userDTO);
        }

        return new ResponseEntity<>("Регистрация прошла успешна!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activateUser(@PathVariable int id) {
        userService.activateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable int id) {
        userService.deactivateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> changeUser(@RequestBody ChangeUserDTO userDTO) {
        if (userDTO.getRole().equals("TEACHER")) {
            userService.changeTeacher(userDTO);
        } else {
            userService.changeStudent(userDTO);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<Void> sendEmail(@RequestParam String email) {
        Optional<User> user = userService.findUserByEmail(email);
        if (user.isPresent()) {
            verifyUserService.resetPassword(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/resetPassword")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordUserDTO userDTO) {
        userService.resetPassword(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

