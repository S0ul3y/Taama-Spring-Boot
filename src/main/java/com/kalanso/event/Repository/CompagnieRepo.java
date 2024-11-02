package com.kalanso.event.Repository;

import com.kalanso.event.Model.Compagnie;
import com.kalanso.event.Model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompagnieRepo extends JpaRepository<Compagnie, Long> {
    Compagnie findByNom(String nom);

    List<Compagnie> findByStatut(Statut statut);
}
