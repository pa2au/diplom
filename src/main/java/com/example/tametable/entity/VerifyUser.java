package com.example.tametable.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "verify_users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String token;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = false;
}
