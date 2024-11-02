package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "StatutReservation")
public class ReservationStatut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String statut;

    @JsonIgnore
    @OneToMany(mappedBy = "statut")
    private List<Reservation> reservations;

}
