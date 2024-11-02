package com.kalanso.event.Service;

import com.kalanso.event.Model.Utilisateur;
import com.kalanso.event.Repository.Utilisateur_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService extends CrudService<Utilisateur, Long>{

    @Autowired
    private final Utilisateur_repo utilisateur_repo;

    public UtilisateurService(Utilisateur_repo utilisateurRepo, Utilisateur_repo utilisateur_repo) {
        super(utilisateurRepo);
        this.utilisateur_repo = utilisateur_repo;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateur_repo.findByusername(username).orElseThrow();
    }

}
