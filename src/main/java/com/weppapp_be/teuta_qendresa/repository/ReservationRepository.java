package com.weppapp_be.teuta_qendresa.repository;

import com.weppapp_be.teuta_qendresa.entity.Event;
import com.weppapp_be.teuta_qendresa.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT e FROM Reservation e WHERE e.id = :id AND e.deletedAt IS NULL AND e.createdBy = :createdBy")
    Optional<Reservation> findById(Long id, Long createdBy);

    @Query("SELECT e FROM Reservation e WHERE e.deletedAt IS NULL AND e.createdBy = :createdBy ORDER BY e.createdAt DESC ")
    List<Reservation> findAll(Long createdBy);

    boolean existsByUserIdAndEventId(Long userId, Long eventId);
}
