package com.weppapp_be.teuta_qendresa.repository;

import com.weppapp_be.teuta_qendresa.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.id = :id AND e.deletedAt IS NULL")
    Optional<Event> findById(Long id);

    @Query("SELECT e FROM Event e WHERE e.deletedAt IS NULL")
    List<Event> findAll();
}
