package com.kalanso.event.Controller;

import com.kalanso.event.Model.AdminComp;
import com.kalanso.event.Model.Compagnie;
import com.kalanso.event.Model.Voyage;
import com.kalanso.event.Repository.VoyageRepo;
import com.kalanso.event.Service.CompagnieService;
import com.kalanso.event.Service.VoyageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CompagnieController {

    private final VoyageRepo voyageRepo;
    @Autowired
    private CompagnieService compagnieService;

    @PostMapping("/compagnie/ajout")
    public String AjoutCompanie(@RequestBody Compagnie V) {
        return compagnieService.createCompagnie(V) ;
    }


    @GetMapping("/compagnie/get")
    public Iterable<Compagnie> getCompanie() {
        return compagnieService.getAll() ;
    }

    @PutMapping("/compagnie/modif/{id}")
    public Compagnie ModifierCompanie(@PathVariable Long id, @RequestBody Compagnie V) {
        return compagnieService.update(id,V) ;
    }

    @DeleteMapping("/compagnie/sup/{id}")
    public void SupprimerCompagnie(@PathVariable Long id) {
        compagnieService.delete(id);
    }
}
