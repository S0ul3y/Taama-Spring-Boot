package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.JourRepo;
import com.kalanso.event.Repository.ReservationRepo;
import com.kalanso.event.Repository.VoyageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class VoyageService extends CrudService<Voyage, Long>{

    @Autowired
    ContexHolder contexHolder;
    @Autowired
    private VoyageRepo voyageRepo;
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    private JourRepo jourRepo;

    LocalDate today = LocalDate.now();
    String dayOfWeek = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH);
    String aujourdhui = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1).toLowerCase();


    public List<Voyage> AccueilVoyage(){

        //jour jour = jourRepo.findByjour(aujourdhui);

        LocalTime currentTime = LocalTime.now();
        Statut statut = Statut.Active;
        //System.out.println(currentTime);
        System.out.println(aujourdhui);

        return voyageRepo.findByJourAndStatut(aujourdhui,statut,currentTime);
    }


    @Override
    public Voyage create(Voyage entity) {
        AdminAgence adminAgence = (AdminAgence) contexHolder.utilisateur();
        //AdminAgence adminA = (AdminAgence) user;
        entity.setAdminAgence(adminAgence);
        entity.setCompagnie(adminAgence.getCompagnie());
        entity.setAgence(adminAgence.getAgence());

        return super.create(entity);
    }

    public VoyageService(VoyageRepo voyageRepo) {
        super(voyageRepo);
    }

   // @Override
    //public Voyage update(Long aLong, Voyage entity) {
       // return super.update(aLong, entity);
    //}

    public List<Voyage> getVoyageComp(){
        Utilisateur user = contexHolder.utilisateur();

        if(Objects.equals(user.getRole().getRole(), "AdminC")){
            AdminComp adminComp = (AdminComp) user;
            return voyageRepo.findByCompagnie(adminComp.getCompagnie());
        }
        else if (Objects.equals(user.getRole().getRole(), "AdminA")) {
            System.out.println("Je suis un Admin A");
            AdminAgence adminAgence = (AdminAgence) user;
            return voyageRepo.findByAgence(adminAgence.getAgence());
        }
        else {
            return voyageRepo.findAll();
        }

    }

    @Override
    public Voyage update(Long aLong, Voyage entity, String... ignoreProperties) {
        return super.update(aLong, entity, "id","adminAgence","agence","compagnie");
    }


}
