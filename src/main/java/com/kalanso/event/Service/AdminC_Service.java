package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AdminC_Service extends CrudService<AdminComp, Long>{

    //private final Rolerepo rolerepo;

    @Autowired
    ContexHolder contexHolder;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminC_Repo adminCRepo;
    @Autowired
    Rolerepo rolerepo;
    @Autowired
    AdminA_Repo adminARepo;
    @Autowired
    AgenceRepo agenceRepo;
    @Autowired
    VoyageRepo voyageRepo;

    public AdminC_Service(AdminC_Repo adminCRepo) {
        super(adminCRepo);

    }


    @Override
    public AdminComp update(Long aLong, AdminComp entity, String... ignoreProperties ) {
        return super.update(aLong, entity, "id", "password", "role");
    }


    public String Bloquer(Long id) {
        Statut bloquer = Statut.Bloque;
        Statut active = Statut.Active;

        Optional<AdminAgence> adminAOpt = adminARepo.findById(id);
        if (adminAOpt.isPresent()) {
            AdminAgence adminAgence = adminAOpt.get();

            Statut statutCourant = adminAgence.getStatut();

            // Si le statut actuel est différent d'Actif, on l'active
            if (statutCourant != Statut.Active) {
                adminAgence.setStatut(active);
                adminARepo.save(adminAgence);  // Sauvegarder l'AdminAgence

                // Activer la compagnie associée
                if (adminAgence.getAgence() != null) {
                    adminAgence.getAgence().setStatut(active);
                    agenceRepo.save(adminAgence.getAgence());  // Sauvegarder la Compagnie
                }

                // Activer tous les voyages créés par cet AdminAgence
                List<Voyage> voyages = adminAgence.getVoyages();
                if (voyages != null) {
                    for (Voyage voyage : voyages) {
                        voyage.setStatut(active);  // Activer le voyage
                        voyageRepo.save(voyage);  // Sauvegarder le Voyage
                    }
                }
                return "AdminAgence et voyages activés avec succès.";
            }
            // Sinon, on le bloque
            else {
                adminAgence.setStatut(bloquer);
                adminARepo.save(adminAgence);  // Sauvegarder l'AdminAgence

                // Bloquer la compagnie associée
                if (adminAgence.getAgence() != null) {
                    adminAgence.getAgence().setStatut(bloquer);
                    agenceRepo.save(adminAgence.getAgence());  // Sauvegarder la Compagnie
                }

                // Bloquer tous les voyages créés par cet AdminAgence
                List<Voyage> voyages = adminAgence.getVoyages();
                if (voyages != null) {
                    for (Voyage voyage : voyages) {
                        voyage.setStatut(bloquer);  // Bloquer le voyage
                        voyageRepo.save(voyage);  // Sauvegarder le Voyage
                    }
                }
                return "AdminAgence, compagnie associée, et voyages bloqués avec succès.";
            }
        }
        return "AdminAgence non trouvé.";
    }
}
