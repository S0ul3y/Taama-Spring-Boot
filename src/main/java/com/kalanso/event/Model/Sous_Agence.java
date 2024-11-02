package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sous_Agence extends Utilisateur {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String couleur;

    @Column(nullable = false)
    private String mdp;


    @ManyToOne
    @JoinColumn(name = "agence")
    private Agence agence;*/

    @ManyToOne
    @JoinColumn(name = "UtilisateurId")
    private Utilisateur utilisateur;

}
