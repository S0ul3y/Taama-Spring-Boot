package com.kalanso.event.Repository;

import com.kalanso.event.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Utilisateur_repo extends JpaRepository<Utilisateur, Long> {
    //Optional<Utilisateur> findByusername(String username);
    Optional<Utilisateur> findByusername(String username);
    //List<Utilisateur> findByRole(RoleUser roleUser);
}
