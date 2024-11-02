package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;
    //@JsonIgnore
    //@ManyToOne
    //@JoinColumn(name = "evenement_id")
    //private Evenement evenement;

    //private String sujet;
    //private Date dateEnvoi = new Date();

    //@ManyToOne
    //@JoinColumn(name = "statutEnvoi_id")
    //private StatutEnvoi status;

    //private String message;
    //private String dest_email;
}
