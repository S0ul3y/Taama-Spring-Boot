package com.kalanso.event.Repository;

import com.kalanso.event.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation,Long>{

 Reservation findByClientAndVoyageAndDateAndStatut(Client client, Voyage voyage, Date date, ReservationStatut statut);

 List<Reservation> findByClientAndStatut(Client client, ReservationStatut statut);

 //List<Reservation> findByI(Client client, ReservationStatut statut);

 @Query("SELECT r FROM Reservation r WHERE r.voyage.id = :voyageId AND r.date <= :today")
 List<Reservation> findByVoyageAndDate(@Param("voyageId") int voyageId, @Param("today")Date today);

 @Query("SELECT r FROM Reservation r WHERE (r.statut = :active or r.statut = :expirer) and r.compagnie = :compagnie ")
 List<Reservation> findByStatutAndCompagnie(@Param("active") ReservationStatut active, @Param("expirer") ReservationStatut expirer, @Param("compagnie") Compagnie compagnie);

 //@Query("SELECT r FROM Reservation r WHERE (r.statut = :active or r.statut = :expirer) and r.compagnie.agences = :agence ")
 //List<Reservation> findByStatutA(@Param("active") ReservationStatut active, @Param("expirer") ReservationStatut expirer, @Param("compagnie") Agence agence);

 @Query("SELECT r FROM Reservation r WHERE (r.statut = :active or r.statut = :expirer)")
 List<Reservation> findByStatut(@Param("active") ReservationStatut active, @Param("expirer") ReservationStatut expirer);


 //List<Reservation> findByVoyage(Voyage voyage);
 //Reservation findById(Long id);
}
