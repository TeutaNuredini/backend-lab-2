package com.weppapp_be.teuta_qendresa.service;

import com.weppapp_be.teuta_qendresa.dto.LocationDto;
import com.weppapp_be.teuta_qendresa.dto.request.VenueRequest;
import com.weppapp_be.teuta_qendresa.entity.Location;
import com.weppapp_be.teuta_qendresa.exception.ResourceNotFoundException;
import com.weppapp_be.teuta_qendresa.mapper.LocationMapper;
import com.weppapp_be.teuta_qendresa.repository.VenueRepository;
import com.weppapp_be.teuta_qendresa.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;
    private final LocationMapper locationMapper;
    private UserService userService;

    public LocationDto creat(VenueRequest request){
        Location location = locationMapper.toEntity(request);
        location.setCreatedBy(userService.getCurrentUser().getId());
        location.setCreatedAt(LocalDateTime.now());
        Location locationInDb = venueRepository.save(location);
        return locationMapper.toDto(locationInDb);
    }

    public LocationDto getById(Long id){
        Location locationInDb = venueRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Venue with id %s not found", id)));
        return locationMapper.toDto(locationInDb);
    }

    public List<LocationDto> getAll(){
        List<Location> locations = venueRepository.findAll();
        return locations.stream().map(locationMapper::toDto).collect(Collectors.toList());
    }

    public LocationDto update(Long id, Map<String, Object> fields){
        Location locationInDb = venueRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(String.format("Venue with id %s not found", id)));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(locationInDb, key, value);
        });
        return locationMapper.toDto(venueRepository.save(locationInDb));
    }

    public void delete(Long Id){
        Location locationInDb = venueRepository.findById(Id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Venue with id %s not found", Id)));
        locationInDb.setDeletedAt(LocalDateTime.now());
        venueRepository.save(locationInDb);
    }

}
