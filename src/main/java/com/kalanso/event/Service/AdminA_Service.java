package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.AdminA_Repo;
import com.kalanso.event.Repository.AgenceRepo;
import com.kalanso.event.Repository.Rolerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminA_Service extends CrudService<AdminAgence, Long>{

    private final Rolerepo rolerepo;
    @Autowired
    ContexHolder contexHolder;
    @Autowired
     PasswordEncoder passwordEncoder;
    @Autowired
    private AdminA_Repo adminA_Repo;

    public AdminA_Service(AdminA_Repo adminARepo, Rolerepo rolerepo) {
        super(adminARepo);
        this.rolerepo = rolerepo;
    }

    public List<AdminAgence> GetAdminA(){
        Utilisateur user = contexHolder.utilisateur();

        if ( Objects.equals(user.getRole().getRole(), "AdminC")){
            AdminComp adminComp = (AdminComp) user;
            //System.out.println(user);
            return adminA_Repo.findByCompagnie(adminComp.getCompagnie());
        }else {
            return adminA_Repo.findAll();
        }
    }

    @Override
    public AdminAgence create(AdminAgence entity) {
        AdminComp adminComp = (AdminComp) contexHolder.utilisateur();
        Role role = rolerepo.findByRole("AdminA");
        entity.setAdminComp(adminComp);
        entity.setCompagnie(adminComp.getCompagnie());
        entity.setRole(role);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.create(entity);
    }


}
