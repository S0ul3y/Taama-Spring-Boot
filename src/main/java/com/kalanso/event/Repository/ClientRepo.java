package com.kalanso.event.Repository;

import com.kalanso.event.Model.Client;
import com.kalanso.event.Model.Statut;
import com.kalanso.event.Model.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByUsername(String username);

    @Query("SELECT c FROM Client c WHERE c.username != 'Fuctif'")
    List<Client> findByUsernameNF();
}
