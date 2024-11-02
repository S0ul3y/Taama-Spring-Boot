package com.kalanso.event.Model;

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
@NoArgsConstructor
public class AdminAgence extends Utilisateur {

    //@JsonIgnore
    /*@OneToMany(mappedBy = "user")
    private List<Agence> agence;*/

    //@OneToMany(mappedBy = "adminComp")
    //private List<Agence> agences;


    @JsonManagedReference("agence")
    @OneToOne(mappedBy = "adminAgence", cascade = CascadeType.ALL)
    private Agence agence;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "AdminComp_Id")
    private AdminComp adminComp;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "compagnie_id", referencedColumnName = "Id", nullable = false)
    private Compagnie compagnie;

    @JsonIgnore
    @OneToMany(mappedBy = "adminAgence")
    private List<Voyage> voyages;

}
