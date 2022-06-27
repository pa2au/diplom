package com.example.tametable.repository;

import com.example.tametable.entity.VerifyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyUserRepository extends JpaRepository<VerifyUser, Integer> {
    Optional<VerifyUser> findByToken(String token);
}
