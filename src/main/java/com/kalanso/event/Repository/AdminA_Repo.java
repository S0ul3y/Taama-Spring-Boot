package com.kalanso.event.Repository;

import com.kalanso.event.Model.AdminAgence;
import com.kalanso.event.Model.Compagnie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminA_Repo extends JpaRepository<AdminAgence, Long> {
    List<AdminAgence> findByCompagnie(Compagnie compagnie);
}
