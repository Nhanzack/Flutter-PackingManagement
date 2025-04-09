package com.project.backendProjectFinal.repositories;

import com.project.backendProjectFinal.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}
