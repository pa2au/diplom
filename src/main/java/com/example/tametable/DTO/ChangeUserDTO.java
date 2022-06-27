package com.example.tametable.DTO;

import lombok.Data;

@Data
public class ChangeUserDTO {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String mobilePhoneNumber;
    private String username;
    private String email;
    private Integer facultyId;
    private Integer groupId;
    private String role;
}
