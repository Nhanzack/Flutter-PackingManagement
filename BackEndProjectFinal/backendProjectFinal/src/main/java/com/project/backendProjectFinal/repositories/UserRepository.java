package com.project.backendProjectFinal.repositories;

import com.project.backendProjectFinal.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Users> findByPhoneNumber(String phoneNumber);
    //SELECT * FROM users WHERE phoneNumber=?
}
