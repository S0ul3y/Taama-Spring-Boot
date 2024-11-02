package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "jour")
@Data
@NoArgsConstructor
public class jour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String jour;

    //@JsonIgnore
    //@OneToMany(mappedBy = "jour")
    //private List<Voyage> voyage;
}
