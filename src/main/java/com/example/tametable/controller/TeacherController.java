package com.example.tametable.controller;

import com.example.tametable.entity.Group;
import com.example.tametable.entity.Lesson;
import com.example.tametable.entity.Permission;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final WeekDayService weekDayService;
    private final TimeLessonsService timeLessonsService;
    private final GroupService groupService;
    private final DisciplineService disciplineService;
    private final LessonService lessonService;

    @GetMapping("/lessons")
    public String getLessons(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("user", userPrincipal.getUser());
        Set<Group> groups;
        if (userPrincipal.getUser().getRole().name().equals("ADMIN")) {
            groups = lessonService.getLessons().stream().map(Lesson::getGroup).collect(Collectors.toSet());
        } else {
            groups = lessonService.findAllByUser(userPrincipal.getUser()).stream().map(Lesson::getGroup).collect(Collectors.toSet());
        }
        model.addAttribute("groups", groups);
        model.addAttribute("permission", Permission.ACTION_LESSONS);
        return "lessons";
    }

    @GetMapping("/lesson/create")
    public String createLesson(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("weekDays", weekDayService.findWeekDayAll());
        model.addAttribute("timeLessons", timeLessonsService.findExerciseTimeAll());
        model.addAttribute("groups", groupService.findAllGroups());
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("teachers", lessonService.getAllTeachers());
        model.addAttribute("disciplines", disciplineService.findDisciplineAll());
        return "createLesson";
    }

    @GetMapping("/lesson/update/{id}")
    public String updateLesson(@PathVariable(name = "id") Lesson lesson, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("lesson", lesson);
        model.addAttribute("weekDays", weekDayService.findWeekDayAll());
        model.addAttribute("timeLessons", timeLessonsService.findExerciseTimeAll());
        model.addAttribute("groups", groupService.findAllGroups());
        model.addAttribute("teachers", lessonService.getAllTeachers());
        model.addAttribute("disciplines", disciplineService.findDisciplineAll());
        model.addAttribute("user", userPrincipal.getUser());
        return "changeLesson";
    }

    @GetMapping("/lesson/delete/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
        return "redirect:/teacher/lessons";
    }
}
