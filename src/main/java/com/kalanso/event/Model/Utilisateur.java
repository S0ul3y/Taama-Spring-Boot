package com.kalanso.event.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity

@Inheritance(strategy = InheritanceType.JOINED)
//@Data
@Getter
@Setter
@NoArgsConstructor
public class Utilisateur  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    private String nom;
    private String prenom;


    //private String email;

    @Column(unique = true)
    private String telephone;

    @Column(nullable = false)
    private String password;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.Active;

    //@JsonIgnore
    //@OneToMany(mappedBy = "utilisateur")
    //private List<Compagnie> agence;

    //@OneToMany(mappedBy = "utilisateur")
    //private List<Agence> agence;
    //@JsonIgnore
    //@OneToMany(mappedBy = "utilisateur")
    //private List<Voyage> voyages;


    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                // Ajoutez toutes les autres propriétés que vous voulez afficher
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((new SimpleGrantedAuthority("ROLE_" + this.getRole().getRole())));
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //@OneToMany(mappedBy = "utilisateur")
    //private List<Notification> notification;


}