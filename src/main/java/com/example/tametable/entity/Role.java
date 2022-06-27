package com.example.tametable.entity;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.ROLE_ADMIN, Permission.ACTION_LESSONS)),
    TEACHER(Set.of(Permission.ROLE_TEACHER, Permission.ACTION_LESSONS)),
    STUDENT(Set.of(Permission.ROLE_STUDENT));

    private final Set<Permission> permission;

    Role(Set<Permission> permission) {
        this.permission = permission;
    }

    public Set<Permission> getPermission() {
        return permission;
    }


}
