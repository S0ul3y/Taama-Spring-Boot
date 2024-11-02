package com.kalanso.event.Service;

import com.kalanso.event.Model.Sous_Agence;
import com.kalanso.event.Repository.SousAgenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SousAgenceService extends CrudService<Sous_Agence, Long> {

    @Autowired
    SousAgenceRepo sousAgenceRepo;
    @Autowired
    PasswordEncoder passwordEncoder;



    public SousAgenceService(SousAgenceRepo sousAgenceRepo) {
        super(sousAgenceRepo);
        //this.gestionnaireRepo = gestionnaireRepo;


    }

}
