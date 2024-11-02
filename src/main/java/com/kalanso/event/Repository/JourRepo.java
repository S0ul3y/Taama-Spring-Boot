package com.kalanso.event.Repository;

import com.kalanso.event.Model.jour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourRepo extends JpaRepository<jour, Long> {
    jour findByjour(String jour);
}
