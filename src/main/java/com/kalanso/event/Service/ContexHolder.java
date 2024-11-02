package com.kalanso.event.Service;

import com.kalanso.event.Model.Utilisateur;
import com.kalanso.event.Repository.Utilisateur_repo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContexHolder {

    private final Utilisateur_repo utilisateur_repo;

    public Utilisateur utilisateur() {
        // Récupérer l'utilisateur authentifié du SecurityContextHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String) {
            // Si principal est un String (comme le username dans JWT)
            String username = (String) principal;
            return utilisateur_repo.findByusername(username).orElse(null);
        } else if (principal instanceof UserDetails) {
            // Si principal est un UserDetails
            String username = ((UserDetails) principal).getUsername();
            return utilisateur_repo.findByusername(username).orElse(null);
        }

        return null; // Si aucun utilisateur n'est trouvé
    }
}

