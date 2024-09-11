package com.weppapp_be.teuta_qendresa.repository;

import com.weppapp_be.teuta_qendresa.dto.TopSellingEventDto;
import com.weppapp_be.teuta_qendresa.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.id = :id AND e.deletedAt IS NULL")
    Optional<Event> findById(Long id);

    @Query("SELECT e FROM Event e WHERE e.deletedAt IS NULL ORDER BY e.createdAt DESC")
    List<Event> findAll();

    @Query("SELECT e FROM Event e WHERE e.location.id = :locationId AND e.deletedAt IS NULL")
    List<Event> findEventByLocationId(Long locationId);

    @Query("SELECT e FROM Event e WHERE e.category.id = :categoryId AND e.deletedAt IS NULL")
    List<Event> findEventByCategoryId(Long categoryId);


    @Query("SELECT new com.weppapp_be.teuta_qendresa.dto.TopSellingEventDto(e.id, e.name, COUNT(r.id), " +
            "e.location.id, e.category.id, e.name, e.description, e.startTime, e.paragraph, e.duration, " +
            "e.capacity, e.price, e.imageUrl, e.createdAt) " +
            "FROM Event e LEFT JOIN Reservation r ON r.event.id = e.id " +
            "WHERE e.deletedAt IS NULL " +
            "GROUP BY e.id, e.name, e.location.id, e.category.id, e.description, e.startTime, e.paragraph, " +
            "e.duration, e.capacity, e.price, e.imageUrl, e.createdAt " +
            "ORDER BY COUNT(r.id) DESC LIMIT 3")
    List<TopSellingEventDto> findTopSellingEvents();

}
