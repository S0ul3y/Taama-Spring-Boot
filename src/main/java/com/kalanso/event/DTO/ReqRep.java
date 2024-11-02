package com.kalanso.event.DTO;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kalanso.event.Model.Role;
import com.kalanso.event.Model.Statut;
import com.kalanso.event.Model.Utilisateur;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRep {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String nom;
    private String prenom;
    private String username;
    private String telephone;
    private String password;
    private String role;
    private Statut statut;
    private Utilisateur utilisateur;
    private List<Utilisateur> utilisateursList;
}
