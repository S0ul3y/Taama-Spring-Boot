package com.kalanso.event.Controller;


import com.kalanso.event.DTO.ReqRep;
import com.kalanso.event.Model.Utilisateur;
import com.kalanso.event.Repository.Utilisateur_repo;
import com.kalanso.event.Security.JwtUtile;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//@CrossOrigin(origins = "http://localhost:4200")

@CrossOrigin(origins="*")

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class loginController {

    //@Autowired
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;
    //@Autowired
    private Utilisateur_repo utilisateurRepository;
    private JwtUtile jwtUtile;

    @PostMapping("/login")
    public ReqRep login(@RequestBody ReqRep loginRequest) {
        ReqRep response = new ReqRep();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            Utilisateur user = utilisateurRepository.findByusername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String jwt = jwtUtile.generateToken(user);
            String refreshToken = jwtUtile.generateRefreshToken(new HashMap<>(), user);

            // Ajouter les détails de l'utilisateur dans la réponse
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole().getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setNom(user.getNom());
            response.setPrenom(user.getPrenom());
            response.setStatut(user.getStatut());
            response.setUsername(user.getUsername());
            response.setTelephone(user.getTelephone());
            response.setMessage("Successfully Logged In");

        } catch (Exception e) {
            e.printStackTrace();  // Afficher l'erreur dans la console
            response.setStatusCode(500);
            response.setMessage("Authentication failed: " + e.getMessage());
        }
        return response;
    }
}