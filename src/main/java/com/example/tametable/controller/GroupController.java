package com.example.tametable.controller;

import com.example.tametable.entity.Group;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.FacultyService;
import com.example.tametable.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final FacultyService facultyService;
    private final GroupService groupService;

    @GetMapping()
    public String getAllGroups(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("user", userPrincipal.getUser());
        return "groups";
    }

    @GetMapping("/create")
    public String createGroup(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("faculties", facultyService.findAllFaculties());
        return "addGroup";
    }

    @PostMapping("/create")
    public String createGroup(@RequestParam int facultyId, @RequestParam String group) {
        groupService.save(facultyId, group);
        return "redirect:/group";
    }

    @GetMapping("/update/{id}")
    public String updateGroup(@PathVariable int id, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Group group = groupService.findGroupById(id);
        model.addAttribute("group", group);
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("faculties", facultyService.findAllFaculties());
        return "changeGroup";
    }

    @PostMapping("/update")
    public String updateGroup(@RequestParam int id, @RequestParam int facultyId, @RequestParam String name) {
        groupService.updateGroup(id, facultyId, name);
        return "redirect:/group";
    }
}
