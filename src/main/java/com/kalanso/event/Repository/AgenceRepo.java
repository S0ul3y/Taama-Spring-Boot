package com.kalanso.event.Repository;

import com.kalanso.event.Model.Agence;
import com.kalanso.event.Model.Compagnie;
import com.kalanso.event.Model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgenceRepo extends JpaRepository<Agence,Long> {
    Agence findByNom(String nom);
    List<Agence> findByCompagnieIdAndStatut(Long compagnieId, Statut statut);
    List<Agence> findByCompagnie(Compagnie compagnie);
}
