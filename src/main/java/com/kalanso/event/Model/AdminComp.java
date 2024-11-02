package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
//@Table(name = "")
@NoArgsConstructor
public class AdminComp extends Utilisateur{

    @JsonIgnore
    @OneToMany(mappedBy = "adminComp")
    private List<AdminAgence> adminAgences;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Admin_Id")
    private Admin admin;



    //@OneToMany(mappedBy = "adminComp")
    //private List<Compagnie> compagnies;
    //@JsonIgnore
    //@JsonBackReference("comp")
    @JsonManagedReference("comp")
    @OneToOne(mappedBy = "adminComp", cascade = CascadeType.ALL)
    private Compagnie compagnie;
}
