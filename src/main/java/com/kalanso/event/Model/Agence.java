package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "Agence")
@Data
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String telephone;
    private String region;  

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.Active;
    /*@ManyToOne
    @JoinColumn(name = "couleur")
    private Couleur couleur;*/

    @JsonBackReference("agence")
    @OneToOne
    @JoinColumn(name = "adminAgence", referencedColumnName = "id")
    private AdminAgence adminAgence;

    //@JsonIgnore
    @JsonBackReference("compAgence")
    @ManyToOne
    @JoinColumn(name = "compagnie_id", referencedColumnName = "Id")
    private Compagnie compagnie;

    /*@ManyToOne
    @JoinColumn(name = "AdminAgence")
    private AdminAgence adminAgence;*/



    //@OneToMany(mappedBy = "agence")
    //private List<Voyage> voyages;
}
