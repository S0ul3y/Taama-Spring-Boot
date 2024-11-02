package com.kalanso.event.Service;

import com.kalanso.event.Model.AdminAgence;
import com.kalanso.event.Repository.GestionnaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GestionnaireService extends CrudService<AdminAgence, Long> {

    @Autowired
    GestionnaireRepo gestionnaireRepo;
    @Autowired
    PasswordEncoder passwordEncoder;



    public GestionnaireService(GestionnaireRepo gestionnaireRepo) {
        super(gestionnaireRepo);
        //this.gestionnaireRepo = gestionnaireRepo;


    }

    @Override
    public AdminAgence create(AdminAgence entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.create(entity);
    }

}
