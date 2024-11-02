package com.kalanso.event.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalanso.event.Model.*;
import com.kalanso.event.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;
    @Autowired
    VoyageService voyageService;
    @Autowired
    AgenceService agenceService;
    @Autowired
    CompagnieService compagnieService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ObjectMapper objectMapper;

    /*@PostMapping("/client/ajout")
    public Client AjoutAdminA(@RequestBody Client V) {
        return clientService.create(V) ;
    }*/

    @CrossOrigin(origins="http://localhost:8100")
    @PostMapping(value = "/client/ajout", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public int ajouterEvenement(@RequestPart("client") String clientJson,
                                                      @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            // Log input data
            System.out.println("client JSON: " + clientJson);
            if (image != null) {
                System.out.println("Image Original Filename: " + image.getOriginalFilename());
            } else {
                System.out.println("Aucune image fournie.");
            }
            // Convertir la chaîne JSON en objet Evenement
            Client client = objectMapper.readValue(clientJson, Client.class);

            // Log the converted object
            System.out.println("Cient Object: " + client);

            return clientService.ajouterClient(client, image);

            // Log the created event
            //System.out.println("Nouvel Evenement: " + nouvelClient);

            //return ResponseEntity.status(HttpStatus.CREATED).body(nouvelClient);
        } catch (IOException e) {
            // Log the error
            e.printStackTrace();
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return 10;
        }
    }




    @GetMapping("/client/nbr/get")
    public long nbreUser() {
        return clientService.NbrUser() ;
    }

    @GetMapping("/client/get")
    public Iterable<Client> getAdminA() {
        return clientService.getAll() ;
    }

    @PutMapping("/client/modif/{id}")
    public Client ModifierAdminA(@PathVariable Long id,@RequestBody Client V) {
        return clientService.update(id,V) ;
    }

    @DeleteMapping("/client/sup/{id}")
    public void SupprimerAdminA(@PathVariable Long id) {
        clientService.delete(id);
    }


    @GetMapping("/client/voyage/get")
    public Iterable<Voyage> getVoyageClient() {
        return voyageService.AccueilVoyage() ;
    }

    @GetMapping("/client/voyage/get/{A}")
    public Iterable<Voyage> getVoyageAgence(@PathVariable Long A) {
        return clientService.GetvoyageAgence(A) ;
    }

    @CrossOrigin(origins="http://localhost:8100")
    @GetMapping("/client/reservation/get")
    public Iterable<Reservation> getReservation() {
        return clientService.getReservation() ;
    }

    @GetMapping("/client/agence/get/{id}")
    public Iterable<Agence> getAgenceComp(@PathVariable Long id) {
        return agenceService.AgenceCompagnie(id) ;
    }

    @GetMapping("/client/compagnie/get")
    public Iterable<Compagnie> getCompagnie() {
        return compagnieService.ClientCompagnie() ;
    }

    @GetMapping("/client/reservation/get/{id}")
    public Optional<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.getById(id) ;
    }

    //@CrossOrigin(origins="http://localhost:8100")
    @GetMapping("/client/getusername")
    public Client getByUsername() {
        return clientService.getByUsername() ;
    }

    @PostMapping("/client/annulerReservation/{ReserId}")
    public int AnnulerBillet(@PathVariable Long ReserId, @RequestBody Long numero) {
        return reservationService.AnnulerReservation(numero, ReserId) ;
    }


}
