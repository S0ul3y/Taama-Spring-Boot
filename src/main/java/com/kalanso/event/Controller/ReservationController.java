package com.kalanso.event.Controller;

import com.kalanso.event.Model.Client;
import com.kalanso.event.Model.Reservation;
import com.kalanso.event.Model.Voyage;
import com.kalanso.event.Service.ClientService;
import com.kalanso.event.Service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping("/reservation/ajout")
    public int AjoutReservation(@RequestBody Reservation V) {
        return reservationService.createR(V) ;
    }
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/reservation/agence/ajout")
    public int AgenceReservation(@RequestBody Reservation V) {
        return reservationService.AgenceRervt( V) ;
    }
    @GetMapping("/reservation/get")
    public Iterable<Reservation> getReservation() {
        return reservationService.getAll() ;
    }

    @GetMapping("/reservation/nbr/get")
    public Long getNbrReservation() {
        return reservationService.GetNbrReservation() ;
    }



    @CrossOrigin(origins="http://localhost:8100")
    @GetMapping("/reservation/voyage/{V}/get")
    public List<Reservation> getReservationByVoyage(@PathVariable int V) {
        return reservationService.GetR_byVoyage(V) ;
    }

    @CrossOrigin(origins="http://localhost:8100")
    @GetMapping("/reservation/{V}/get")
    public Optional<Reservation> getReservationByVoyage(@PathVariable long V) {
        return reservationService.getById(V) ;
    }

    @PutMapping("/reservation/modif/{id}")
    public Reservation ModifierReservation(@PathVariable Long id, @RequestBody Reservation V) {
        return reservationService.update(id,V) ;
    }

    @DeleteMapping("/reservation/sup/{id}")
    public void SupprimerReservation(@PathVariable Long id) {
        reservationService.delete(id);
    }
}
