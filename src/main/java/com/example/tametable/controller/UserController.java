package com.example.tametable.controller;

import com.example.tametable.entity.Role;
import com.example.tametable.entity.User;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.FacultyService;
import com.example.tametable.service.GroupService;
import com.example.tametable.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final FacultyService facultyService;
    private final GroupService groupService;
    private final UserService userService;

    @GetMapping("/registration")
    public String addUser(Model model) {
        model.addAttribute("roles", List.of(Role.STUDENT, Role.TEACHER));
        model.addAttribute("faculties", facultyService.findAllFaculties());
        model.addAttribute("groups", groupService.findAllGroups());
        return "addUser";
    }

    @GetMapping()
    public String main(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal.getUser());
        return "schedule";
    }

    @GetMapping("/students")
    public String getStudents(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("users", userService.findAllStudentsByRole(Role.STUDENT));
        return "students";
    }

    @GetMapping("/teachers")
    public String getTeacher(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("users", userService.findAllStudentsByRole(Role.TEACHER));
        return "teachers";
    }

    @GetMapping("/change/{id}")
    public String changeStudent(@PathVariable int id, Model model) {
        User user = userService.findUserById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("user with id %s doesn't exists", id)));
        model.addAttribute("groups", groupService.findAllGroups());
        model.addAttribute("user", user);
        model.addAttribute("faculties", facultyService.findAllFaculties());
        model.addAttribute("roles", List.of(Role.STUDENT, Role.TEACHER));
        return "changeUser";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam String token, Model model) {
        Optional<User> user = userService.findUserByVerifyUserToken(token);
        if (user.isPresent()) {
            model.addAttribute("available", true);
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("available", false);
        }
        return "resetPassword";
    }


    @GetMapping("/sendEmail")
    public String sendEmail() {
        return "sendEmail";
    }
}
