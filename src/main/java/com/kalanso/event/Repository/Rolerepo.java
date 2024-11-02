package com.kalanso.event.Repository;

import com.kalanso.event.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rolerepo extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
