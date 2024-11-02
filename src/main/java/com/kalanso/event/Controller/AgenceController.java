package com.kalanso.event.Controller;

import com.kalanso.event.Model.Agence;
import com.kalanso.event.Model.Compagnie;
import com.kalanso.event.Service.AgenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AgenceController {

    @Autowired
    private AgenceService agenceService;

    @PostMapping("/agence/ajout")
    public String AjoutAgence(@RequestBody Agence A) {
        return agenceService.createAgence(A) ;
    }

    @GetMapping("/agence/get")
    public Iterable<Agence> getVoyage(Agence A) {
        return agenceService.GetAgence() ;
    }

    @GetMapping("/client/agence/nbr/get")
    public Long geNbrAgence() {
        return agenceService.GetNbrAgence() ;
    }


    @PutMapping("/agence/modif/{id}")
    public Agence ModifierAgence(@PathVariable Long id,@RequestBody Agence A) {
        return agenceService.update(id,A) ;
    }

    @DeleteMapping("/agence/sup/{id}")
    public void SupprimerAgence(@PathVariable Long id) {
        agenceService.delete(id);
    }
}
