package com.kalanso.event.Controller;

import com.kalanso.event.Model.Utilisateur;
import com.kalanso.event.Service.ContexHolder;
import com.kalanso.event.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UtilisateurController {

    ContexHolder contexHolder;
    UtilisateurService utilisateurService ;

    @CrossOrigin(origins="http://localhost:4200/")
    @GetMapping("Utilisateur/get")
    public Iterable getUtilisateur() {
        return utilisateurService.getAll();
    }

    @GetMapping("Utilisateur/currentSession")
    public Utilisateur getCurrentUser() {
        return contexHolder.utilisateur();
    }
}
