package com.kalanso.event.Controller;

import com.kalanso.event.Model.Ville;
import com.kalanso.event.Model.Voyage;
import com.kalanso.event.Model.jour;
import com.kalanso.event.Repository.JourRepo;
import com.kalanso.event.Repository.VilleRepo;
import com.kalanso.event.Service.VoyageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class JourController {

    private JourRepo jourRepo;
    private VilleRepo villeRepo;


    @GetMapping("/jour/get")
    public Iterable<jour> getjour() {
       return jourRepo.findAll() ;
    }

    @GetMapping("/ville/get")
    public Iterable<Ville> getville() {
        return villeRepo.findAll() ;
    }

}
