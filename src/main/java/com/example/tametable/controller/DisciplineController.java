package com.example.tametable.controller;

import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/discipline")
@RequiredArgsConstructor
public class DisciplineController {
    private final DisciplineService disciplineService;

    @GetMapping()
    public String getAllDiscipline(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("disciplines", disciplineService.findDisciplineAll());
        return "disciplines";
    }

    @GetMapping("/create")
    public String createDiscipline(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal.getUser());
        return "createDiscipline";
    }

    @PostMapping("/create")
    public String createDiscipline(@RequestParam String name, @RequestParam String description) {
        disciplineService.saveDiscipline(name, description);
        return "redirect:/discipline";
    }

    @GetMapping("/update/{id}")
    public String updateDiscipline(@PathVariable int id, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("discipline", disciplineService.findDisciplineById(id));
        return "changeDiscipline";
    }

    @PostMapping("/update/{id}")
    public String updateDiscipline(@PathVariable int id, @RequestParam String name, @RequestParam String description, Model model) {
        disciplineService.updateDiscipline(name, description, id);
        return "redirect:/discipline";
    }

    @GetMapping("/delete/{id}")
    public String deleteDiscipline(@PathVariable int id) {
        disciplineService.delete(id);
        return "redirect:/discipline";
    }
}
