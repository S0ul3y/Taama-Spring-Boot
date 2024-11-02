package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AgenceService extends CrudService<Agence, Long> {

    @Autowired
    AgenceRepo agenceRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ContexHolder contexHolder;
    @Autowired
    Utilisateur_repo utilisateurRepo;
    @Autowired
    Rolerepo rolerepo;
    @Autowired
    AdminA_Repo adminARepo;
    @Autowired
    CompagnieRepo compagnieRepo;

    public AgenceService(AgenceRepo agenceRepo) {
        super(agenceRepo);
        //this.gestionnaireRepo = gestionnaireRepo;


    }

    public List<Agence> AgenceCompagnie(Long compId){
        Statut statut = Statut.Active;
        return agenceRepo.findByCompagnieIdAndStatut(compId,statut);
    }



    @Override
    public Agence update( Long aLong, Agence entity, String... ignoreProperties) {
        return super.update(aLong, entity, "id","adminAgence","compagnie");
    }

//@Override
    //public Compagnie update(Long aLong, Agence entity, String... ignoreProperties) {
     //   return super.update(aLong, entity, "id","admin","adminComp");
    //}

    public List<Agence> GetAgence(){
        Utilisateur user = contexHolder.utilisateur();

        if ( Objects.equals(user.getRole().getRole(), "AdminC")){
            AdminComp adminComp = (AdminComp) user;
            System.out.println(user);
            return agenceRepo.findByCompagnie(adminComp.getCompagnie());
        }else {
            return agenceRepo.findAll();
        }
    }

    public Long GetNbrAgence(){
        Utilisateur user = contexHolder.utilisateur();

        if ( Objects.equals(user.getRole().getRole(), "AdminC")){
            AdminComp adminComp = (AdminComp) user;
            System.out.println(user);
            return agenceRepo.findByCompagnie(adminComp.getCompagnie()).stream().count();
        }else {
            return agenceRepo.findAll().stream().count();
        }
    }

    public String createAgence(Agence entity) {

        Agence comp = agenceRepo.findByNom(entity.getNom());
        AdminAgence adminA = (AdminAgence) utilisateurRepo.findByusername(entity.getAdminAgence().getUsername()).orElse(null);

        AdminComp adminComp = (AdminComp) contexHolder.utilisateur();

        if (comp == null) { // On vérifie d'abord si la compagnie n'existe pas déjà
            if (adminA == null) { // Vérifie aussi que l'AdminComp n'existe pas

                // Créer et attribuer les rôles à l'admin
                Role role = rolerepo.findByRole("AdminA");

                // Récupérer l'adminComp à partir de l'entité si l'utilisateur est nouveau
                AdminAgence adminAgence = entity.getAdminAgence();
                if (adminAgence != null) {
                    adminAgence.setRole(role);
                    //adminAgence.setAdmin((Admin) contexHolder.utilisateur());
                    adminAgence.setPassword(passwordEncoder.encode(adminAgence.getPassword()));
                    adminAgence.setCompagnie(adminComp.getCompagnie());
                    adminAgence.setAdminComp(adminComp);

                    // Il faut d'abord sauvegarder l'instance AdminComp avant de l'associer à la Compagnie
                    adminARepo.save(adminAgence); // Sauvegarder AdminComp ici
                }

                // Associer l'admin à la compagnie
                //entity.setAdmin((Admin) contexHolder.utilisateur());
                entity.setAdminAgence(adminAgence); // Associer adminComp à la compagnie après l'avoir sauvegardé
                entity.setCompagnie(adminComp.getCompagnie());
                agenceRepo.save(entity); // Sauvegarder la compagnie

                return "Bien fait";

            } else {
                return "L'AdminComp existe déjà";
            }
        } else {
            return "La Compagnie existe déjà";
        }
    }
}
