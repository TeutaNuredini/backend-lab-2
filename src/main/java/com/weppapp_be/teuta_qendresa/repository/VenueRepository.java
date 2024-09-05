package com.weppapp_be.teuta_qendresa.repository;

import com.weppapp_be.teuta_qendresa.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VenueRepository extends JpaRepository<Location, Long> {

    @Query("SELECT v FROM Location v WHERE v.id = ?1 AND v.deletedAt IS NULL")
    Optional<Location> findById(Long id);

    @Query("SELECT v FROM Location v WHERE v.deletedAt IS NULL")
    List<Location> findAll();
}
