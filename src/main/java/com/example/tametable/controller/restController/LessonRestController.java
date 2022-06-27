package com.example.tametable.controller.restController;

import com.example.tametable.DTO.LessonAddDto;
import com.example.tametable.DTO.ListTeacher;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonRestController {
    private final LessonService lessonService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createLesson(@RequestBody LessonAddDto lessonAddDto, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        String msg;
        if (userPrincipal.getUser().getRole().name().equals("ADMIN")) {
            msg = lessonService.createLessonFromAdmin(lessonAddDto);
        } else {
            msg = lessonService.createLesson(lessonAddDto);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLesson(@RequestBody LessonAddDto lessonAddDto, @PathVariable(name = "id") Long id) {
        String msg = lessonService.updateLesson(id, lessonAddDto);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("all-teachers")
    public List<ListTeacher> getTeachers(){
        return lessonService.getAllTeachers();
    }

//    @PostMapping("/create-admin")
//    public ResponseEntity<String> createLessonAdmin(@RequestBody LessonAddAdminDto dto) {
//        lessonService.createLessonFromAdmin(dto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
