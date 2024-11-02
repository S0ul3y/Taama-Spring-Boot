package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "voyage")
@Data
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ville_depart;
    private String ville_arrivee;
    private long Nbr_place;
    private LocalTime Heure;
    private int prix;
    private String jour;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.Active;



    //@JsonIgnore
    //@ManyToOne
    //@JoinColumn(name = "jourId")
    //private jour jour;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "AdminA_Id")
    private AdminAgence adminAgence;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "agence_Id", nullable = false)
    private Agence agence;

    @JsonIgnore
    @OneToMany(mappedBy = "voyage")
    private List<Reservation> reservations;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "compagnie_id", referencedColumnName = "Id", nullable = false)
    private Compagnie compagnie;

}
