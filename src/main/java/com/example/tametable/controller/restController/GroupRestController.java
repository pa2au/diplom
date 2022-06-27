package com.example.tametable.controller.restController;

import com.example.tametable.DTO.ListLessonGroup;
import com.example.tametable.DTO.ListLessonTeacher;
import com.example.tametable.entity.Group;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.GroupService;
import com.example.tametable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GroupRestController {
    private final GroupService groupService;

    private final LessonService lessonService;

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(groupService.findAllGroups(), HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-lesson-by-group/{groupId}")
    public List<ListLessonGroup> getLessonsByGroupId(@PathVariable Integer groupId) {
        return lessonService.getAllLessonsByGroupId(groupId);
    }

    @GetMapping("/all-lesson-teacher")
    public List<ListLessonTeacher> getLessonByTeacher(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return lessonService.getAllTeacherLessons(userPrincipal.getUser());
    }

    @GetMapping("/all-lessons-by-week/{weekId}")
    public List<ListLessonGroup> getAllLessonsByWeekId(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Integer weekId) {
        if (userPrincipal.getUser().getRole().name().equals("ADMIN")) {
            return lessonService.getAllLessonsByWeekId(weekId);
        } else {
            return lessonService.getAllLessonsByUserGroupIdAndWeekId(userPrincipal.getUser(), weekId);
        }

    }

    @GetMapping("/all-lesson-by-week-groupId/{weekId}/{groupId}")
    public List<ListLessonGroup> getAllLessonsByWeekIdAndGroupId(@PathVariable int weekId, @PathVariable int groupId) {
        return lessonService.getAllLessonsByGroupIdAndWeekId(groupId, weekId);
    }
}
