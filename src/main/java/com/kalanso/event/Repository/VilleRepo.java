package com.kalanso.event.Repository;

import com.kalanso.event.Model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VilleRepo extends JpaRepository<Ville,Long> {

    Ville findByVille(String ville);
}
