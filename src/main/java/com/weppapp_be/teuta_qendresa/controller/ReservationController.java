package com.weppapp_be.teuta_qendresa.controller;

import com.weppapp_be.teuta_qendresa.dto.ReservationDto;
import com.weppapp_be.teuta_qendresa.dto.request.ReservationRequest;
import com.weppapp_be.teuta_qendresa.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> create(@RequestBody ReservationRequest request) {
        return new ResponseEntity<>(reservationService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAll() {
        return ResponseEntity.ok(reservationService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(reservationService.update(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

