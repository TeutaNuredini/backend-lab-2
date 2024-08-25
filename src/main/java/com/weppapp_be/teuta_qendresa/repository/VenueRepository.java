package com.weppapp_be.teuta_qendresa.repository;

import com.weppapp_be.teuta_qendresa.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    @Query("SELECT v FROM Venue v WHERE v.id = ?1 AND v.deletedAt IS NULL")
    Optional<Venue> findById(Long id);

    @Query("SELECT v FROM Venue v WHERE v.deletedAt IS NULL")
    List<Venue> findAll();
}
