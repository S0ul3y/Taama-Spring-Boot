package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;
    private int numeropayement;
    @Column(unique = true)
    private int code;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "StatutId")
    private ReservationStatut statut;


    //@JsonBackReference("reservation")
    @ManyToOne()
    @JoinColumn(name = "ClientId")
    private Client client;


    @ManyToOne()
    @JoinColumn(name = "VoyageId")
    private Voyage voyage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "compagnie_id", referencedColumnName = "Id", nullable = false)
    private Compagnie compagnie;

}
