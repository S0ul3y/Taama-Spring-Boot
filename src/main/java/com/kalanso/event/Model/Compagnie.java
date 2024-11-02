package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Compagnie")
@Data
public class Compagnie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nom;
    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.Active;

    @ManyToOne
    @JoinColumn(name = "Admin")
    private Admin admin;

    //@JsonIgnore
    //@JsonManagedReference("comp")
    @JsonBackReference("comp")
    @OneToOne
    @JoinColumn(name = "adminComp", referencedColumnName = "id", nullable = false, unique = true)
    private AdminComp adminComp;

    @JsonIgnore
    @OneToMany(mappedBy = "compagnie", cascade = CascadeType.ALL)
    private List<Voyage> voyages;

    @JsonManagedReference("compAgence")
    @OneToMany(mappedBy = "compagnie", cascade = CascadeType.ALL)
    private List<Agence> agences;

    //@JsonIgnore
    //@OneToMany(mappedBy = "compagnie", cascade = CascadeType.ALL)
    //private List<AdminAgence> adminAgences;

    @JsonIgnore
    @OneToMany(mappedBy = "compagnie", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    //@OneToOne
    //@JoinColumn(name = "adminAgence", referencedColumnName = "id")
    //private AdminAgence adminAgence;


}
