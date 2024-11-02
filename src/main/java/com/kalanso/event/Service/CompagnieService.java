package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompagnieService extends CrudService<Compagnie, Long>{
    @Autowired
    ContexHolder contexHolder;
    @Autowired
    AdminC_Repo adminCRepo;
    @Autowired
    Utilisateur_repo utilisateurRepo;
    @Autowired
    CompagnieRepo compagnieRepo;
    @Autowired
    Rolerepo rolerepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public CompagnieService(CompagnieRepo compagnieRepo) {
        super(compagnieRepo);
    }

    /*@Override
    public Compagnie create(Compagnie entity) {
        //AdminComp adminComp = (AdminComp) contexHolder.utilisateur();
        entity.setAdmin((Admin)contexHolder.utilisateur());

        return super.create(entity);
    }*/

    public List<Compagnie> ClientCompagnie(){
        Statut statut = Statut.Active;
        return compagnieRepo.findByStatut(statut);
    }



    public String createCompagnie(Compagnie entity) {

        Compagnie comp = compagnieRepo.findByNom(entity.getNom());
        AdminComp adminComp = (AdminComp) utilisateurRepo.findByusername(entity.getAdminComp().getUsername()).orElse(null);

        if (comp == null) { // On vérifie d'abord si la compagnie n'existe pas déjà
            if (adminComp == null) { // Vérifie aussi que l'AdminComp n'existe pas

                // Créer et attribuer les rôles à l'admin
                Role role = rolerepo.findByRole("AdminC");

                // Récupérer l'adminComp à partir de l'entité si l'utilisateur est nouveau
                adminComp = entity.getAdminComp();
                if (adminComp != null) {
                    adminComp.setRole(role);
                    adminComp.setAdmin((Admin) contexHolder.utilisateur());
                    adminComp.setPassword(passwordEncoder.encode(adminComp.getPassword()));

                    // Il faut d'abord sauvegarder l'instance AdminComp avant de l'associer à la Compagnie
                    adminCRepo.save(adminComp); // Sauvegarder AdminComp ici
                }

                // Associer l'admin à la compagnie
                entity.setAdmin((Admin) contexHolder.utilisateur());
                entity.setAdminComp(adminComp); // Associer adminComp à la compagnie après l'avoir sauvegardé
                compagnieRepo.save(entity); // Sauvegarder la compagnie

                return "Bien fait";

            } else {
                return "L'AdminComp existe déjà";
            }
        } else {
            return "La Compagnie existe déjà";
        }
    }

    @Override
    public Compagnie update(Long aLong, Compagnie entity, String... ignoreProperties) {
        return super.update(aLong, entity, "id","admin","adminComp");
    }
}
