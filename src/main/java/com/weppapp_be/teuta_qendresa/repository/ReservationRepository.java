package com.weppapp_be.teuta_qendresa.repository;

import com.weppapp_be.teuta_qendresa.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
}
