/*package com.taama.projet.Security;

import com.sene.backend.entity.personne.Utilisateur;
import com.sene.backend.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Utilisateur loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur trouvé avec cet email : " + email));

        // Vérifiez si le compte est actif
        /*if (utilisateur.getStatutCompte() == null || !utilisateur.getStatutCompte()) {
            throw new RuntimeException("Votre compte n'est pas activé");
        }*/
/*
        return utilisateur;
    }
}*/
