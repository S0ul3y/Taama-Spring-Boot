package com.kalanso.event.Repository;

import com.kalanso.event.Model.ReservationStatut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutRsvtRepo extends JpaRepository<ReservationStatut,Long> {
    ReservationStatut findByStatut(String statut);
}
