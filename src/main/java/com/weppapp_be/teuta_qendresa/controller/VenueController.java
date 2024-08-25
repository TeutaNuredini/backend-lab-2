package com.weppapp_be.teuta_qendresa.controller;

import com.weppapp_be.teuta_qendresa.dto.VenueDto;
import com.weppapp_be.teuta_qendresa.dto.request.VenueRequest;
import com.weppapp_be.teuta_qendresa.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<VenueDto> create(@RequestBody VenueRequest request) {
        return new ResponseEntity<>(venueService.creat(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VenueDto>> getAll() {
        return ResponseEntity.ok(venueService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDto> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(venueService.update(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        venueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

