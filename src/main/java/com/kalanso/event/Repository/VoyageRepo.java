package com.kalanso.event.Repository;

import com.kalanso.event.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface VoyageRepo extends JpaRepository<Voyage, Long> {
    //Voyage findBydefsh(Long comp);
   List <Voyage> findByCompagnie(Compagnie Compagnie);
    List <Voyage> findByAgence(Agence agence);

    List <Voyage> findByAgence_Id(Long id);

    @Query("SELECT v FROM Voyage v WHERE v.jour = :jour AND v.statut = :statut AND SIZE(v.reservations) < v.Nbr_place AND v.Heure >= :heure")
    List<Voyage> findByJourAndStatut(@Param("jour") String jour, @Param("statut") Statut statut , @Param("heure") LocalTime heure);


}
