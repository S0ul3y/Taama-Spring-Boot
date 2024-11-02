package com.kalanso.event.Security;

import com.kalanso.event.Model.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JwtUtile {

    private SecretKey Key;
    private  static  final long EXPIRATION_TIME = 86400000;//24h

    public  JwtUtile(){
        String secreteString = "4fbd406c98fc746f70cc6a3309e650873a04b7284cdfa182ec216eb37809fa2a";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(UserDetails userDetails) {
        // Cast pour récupérer les informations supplémentaires
        Utilisateur utilisateur = (Utilisateur) userDetails;

        // Crée un objet HashMap pour stocker les claims
        HashMap<String, Object> claims = new HashMap<>();

        // Ajout des informations utilisateur dans les claims
        claims.put("username", utilisateur.getUsername());
        claims.put("nom", utilisateur.getNom());
        claims.put("prenom", utilisateur.getPrenom());
        claims.put("telephone", utilisateur.getTelephone());
        claims.put("roles", utilisateur.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)  // Ajoute les claims avec toutes les informations
                .setSubject(utilisateur.getUsername())  // Utilise le username comme sujet
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Date d'expiration
                .signWith(Key)  // Signature avec la clé secrète
                .compact();  // Génère et retourne le token
    }

    public  String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }

    public  String extractUsername(String token){
        return  extractClaims(token, Claims::getSubject);
    }

    //    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
//        return claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
//    }
// Méthode pour extraire les claims du token
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Méthode pour obtenir les claims à partir du token
    Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Key)  // Utilisez la clé secrète ici
                    .build()
                    .parseClaimsJws(token)     // Vérifie et analyse le JWT signé
                    .getBody();
        } catch (SignatureException e) {
            // Gérer les erreurs liées à la signature
            throw new RuntimeException("JWT signature does not match", e);
        }
    }

    public  boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public  boolean isTokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
