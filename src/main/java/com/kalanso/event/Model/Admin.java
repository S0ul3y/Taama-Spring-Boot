package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Admin extends Utilisateur{

    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private List<AdminComp> adminComp;

    //@JsonIgnore
    //@OneToMany(mappedBy = "admin")
    //private List<Compagnie> compagnies;
}
