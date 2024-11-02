package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends CrudService<Admin, Long>{

    @Autowired
    AdminC_Repo adminCRepo;
    @Autowired
    VoyageRepo voyageRepo;
    @Autowired
    CompagnieRepo compagnieRepo;
    @Autowired
    AdminA_Repo adminARepo;
    @Autowired
    AgenceRepo agenceRepo;
    @Autowired
    private ContexHolder contexHolder;
    @Autowired
    private Rolerepo rolerepo;

    public AdminService(AdminRepo adminRepo) {
        super(adminRepo);
    }

    public Utilisateur getCurrentUser(){
        Utilisateur user = contexHolder.utilisateur();

        Role admin = rolerepo.findByRole("Admin");
        Role adminA = rolerepo.findByRole("AdminA");
        Role adminC = rolerepo.findByRole("AdminC");

        if(user.getRole() == admin){
            return (Admin) user;
        }else if (user.getRole() == adminC){
            return (AdminComp) user;
        }else {
            return (AdminAgence) user;
        }
    }


    public String Bloquer(Long id) {
        // Définir le statut bloqué

        Statut bloquer = Statut.Bloque;
        Statut active = Statut.Active;

        Optional<AdminComp> adminCOpt = adminCRepo.findById(id);
        if(adminCOpt.isPresent()) {

            AdminComp adminComp = adminCOpt.get();

            Statut stautCourant = adminComp.getStatut();

            if(stautCourant != Statut.Active){


                adminComp.setStatut(active);
                adminCRepo.save(adminComp);  // Sauvegarder l'AdminCompagnie

                // Bloquer la compagnie associée
                if (adminComp.getCompagnie() != null) {
                    adminComp.getCompagnie().setStatut(active);
                    compagnieRepo.save(adminComp.getCompagnie());  // Sauvegarder la Compagnie
                }

                // Bloquer toutes les agences et leurs AdminAgences
                List<Agence> agences = adminComp.getCompagnie().getAgences();
                if (agences != null) {
                    for (Agence agence : agences) {
                        agence.setStatut(active);  // Bloquer l'agence
                        agenceRepo.save(agence);  // Sauvegarder l'Agence

                        // Bloquer l'AdminAgence associé à l'agence
                        AdminAgence adminAgence = agence.getAdminAgence();
                        if (adminAgence != null) {
                            adminAgence.setStatut(active);
                            adminARepo.save(adminAgence);  // Sauvegarder l'AdminAgence

                            // Bloquer tous les voyages créés par cet AdminAgence
                            List<Voyage> voyages = adminAgence.getVoyages();
                            if (voyages != null) {
                                for (Voyage voyage : voyages) {
                                    voyage.setStatut(active);  // Bloquer le voyage
                                    voyageRepo.save(voyage);  // Sauvegarder le Voyage
                                }
                            }
                        }
                    }
                }
            }else {

                    adminComp.setStatut(bloquer);
                    adminCRepo.save(adminComp);  // Sauvegarder l'AdminCompagnie

                    // Bloquer la compagnie associée
                    if (adminComp.getCompagnie() != null) {
                        adminComp.getCompagnie().setStatut(bloquer);
                        compagnieRepo.save(adminComp.getCompagnie());  // Sauvegarder la Compagnie
                    }

                    // Bloquer toutes les agences et leurs AdminAgences
                    List<Agence> agences = adminComp.getCompagnie().getAgences();
                    if (agences != null) {
                        for (Agence agence : agences) {
                            agence.setStatut(bloquer);  // Bloquer l'agence
                            agenceRepo.save(agence);  // Sauvegarder l'Agence

                            // Bloquer l'AdminAgence associé à l'agence
                            AdminAgence adminAgence = agence.getAdminAgence();
                            if (adminAgence != null) {
                                adminAgence.setStatut(bloquer);
                                adminARepo.save(adminAgence);  // Sauvegarder l'AdminAgence

                                // Bloquer tous les voyages créés par cet AdminAgence
                                List<Voyage> voyages = adminAgence.getVoyages();
                                if (voyages != null) {
                                    for (Voyage voyage : voyages) {
                                        voyage.setStatut(bloquer);  // Bloquer le voyage
                                        voyageRepo.save(voyage);  // Sauvegarder le Voyage
                                    }
                                }
                            }
                        }
                    }
            }

        }

        return "AdminCompagnie, compagnies, agences, adminAgences, et voyages associés ont été bloqués";

    }

            // Bloquer l'AdminCompagnie


}
