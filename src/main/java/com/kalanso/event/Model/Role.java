package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;

    //@OneToMany(mappedBy = "role" ,fetch = FetchType.EAGER)
    //private List<Utilisateur> utilisateur;
}
