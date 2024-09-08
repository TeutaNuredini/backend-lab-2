package com.weppapp_be.teuta_qendresa.controller;

import com.weppapp_be.teuta_qendresa.dto.LocationDto;
import com.weppapp_be.teuta_qendresa.dto.request.LocationRequest;
import com.weppapp_be.teuta_qendresa.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class VenueController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDto> create(@RequestBody LocationRequest request) {
        return new ResponseEntity<>(locationService.creat(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAll() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(locationService.update(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

