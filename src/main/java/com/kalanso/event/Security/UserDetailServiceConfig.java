package com.kalanso.event.Security;

import com.kalanso.event.Repository.Utilisateur_repo;
import com.kalanso.event.Model.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailServiceConfig implements UserDetailsService {

    private Utilisateur_repo utilisateur_repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("hello");
        Optional<Utilisateur> utilisateur = utilisateur_repo.findByusername(username);
        System.out.println("helloAlpha");
        if (utilisateur.isPresent()) {
            System.out.println("helloAlpha2");
            System.out.println(utilisateur.get().getRole().getRole());
            System.out.println(utilisateur.get().getUsername());
            System.out.println(utilisateur.get().getPassword());
        }
        System.out.println("helloAlpha1");
        return User
                .withUsername(utilisateur.get().getUsername())
                .password(utilisateur.get().getPassword())
                .roles(utilisateur.get().getRole().getRole())
                .build();
    }
}
