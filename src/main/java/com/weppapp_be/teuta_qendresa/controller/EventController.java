package com.weppapp_be.teuta_qendresa.controller;

import com.weppapp_be.teuta_qendresa.dto.EventDto;
import com.weppapp_be.teuta_qendresa.dto.TopSellingEventDto;
import com.weppapp_be.teuta_qendresa.dto.request.EventRequest;
import com.weppapp_be.teuta_qendresa.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventDto> create(@RequestBody EventRequest request) {
        return new ResponseEntity<>(eventService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventDto>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping("/by-location/{id}")
    public ResponseEntity<List<EventDto>> getAllByLocationId(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getAllByLocationId(id));
    }

    @GetMapping("/by-category/{id}")
    public ResponseEntity<List<EventDto>> getAllByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getAllByCategoryId(id));
    }

    @GetMapping("/top-selling")
    public ResponseEntity<List<TopSellingEventDto>> getTopSellingEvents() {
        List<TopSellingEventDto> topSellingEvents = eventService.getTopSellingEvents();
        return ResponseEntity.ok(topSellingEvents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(eventService.update(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
