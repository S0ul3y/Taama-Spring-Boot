package com.kalanso.event.Controller;

import com.kalanso.event.Repository.VoyageRepo;
import com.kalanso.event.Service.VoyageService;
import com.kalanso.event.Model.Voyage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class voyageController {

    private final VoyageRepo voyageRepo;
    @Autowired
    VoyageService voyageService;

    @PostMapping("/voyage/ajout")
    public Voyage AjoutVoyage(@RequestBody Voyage V) {
        return voyageService.create(V);
    }


    @GetMapping("/voyage/get")
    public Iterable<Voyage> getVoyage() {
        return voyageService.getVoyageComp() ;
    }



    @PutMapping("/voyage/modif/{id}")
    public Voyage ModifierVoyage(@PathVariable Long id,@RequestBody Voyage V) {
        return voyageService.update(id,V) ;
    }

    @DeleteMapping("/voyage/sup/{id}")
    public void SupprimerVoyage(@PathVariable Long id) {
         voyageService.delete(id);
    }

    //@GetMapping("/voyage/compagnie")
    //List<Voyage> GetVoyageComp(){
        //return voyageService.getVoyageComp();
    //}


}
