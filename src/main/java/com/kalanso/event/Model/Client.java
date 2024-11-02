package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Client extends Utilisateur{

    @Column(name = "image", columnDefinition="LONGBLOB"  )
    @Lob
    private byte[] image;

    //@JsonManagedReference("reservation")
    //@OneToMany(mappedBy = "client")
    //private List<Reservation> reservations;



}
