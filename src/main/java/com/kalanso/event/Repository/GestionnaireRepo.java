package com.kalanso.event.Repository;

import com.kalanso.event.Model.AdminAgence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionnaireRepo extends JpaRepository<AdminAgence, Long> {

}
