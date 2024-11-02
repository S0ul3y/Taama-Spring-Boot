package com.kalanso.event.Repository;

import com.kalanso.event.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifRepo extends JpaRepository<Notification,Long> {
}
