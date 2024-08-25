package com.weppapp_be.teuta_qendresa.service;

import com.weppapp_be.teuta_qendresa.dto.UserDto;
import com.weppapp_be.teuta_qendresa.dto.VenueDto;
import com.weppapp_be.teuta_qendresa.dto.request.VenueRequest;
import com.weppapp_be.teuta_qendresa.entity.User;
import com.weppapp_be.teuta_qendresa.entity.Venue;
import com.weppapp_be.teuta_qendresa.exception.ResourceNotFoundException;
import com.weppapp_be.teuta_qendresa.mapper.VenueMapper;
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
    private final VenueMapper venueMapper;
    private UserService userService;

    public VenueDto creat(VenueRequest request){
        Venue venue = venueMapper.toEntity(request);
        venue.setCreatedBy(userService.getCurrentUser().getId());
        venue.setCreatedAt(LocalDateTime.now());
        Venue venueInDb = venueRepository.save(venue);
        return venueMapper.toDto(venueInDb);
    }

    public VenueDto getById(Long id){
        Venue venueInDb = venueRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Venue with id %s not found", id)));
        return venueMapper.toDto(venueInDb);
    }

    public List<VenueDto> getAll(){
        List<Venue> venues = venueRepository.findAll();
        return venues.stream().map(venueMapper::toDto).collect(Collectors.toList());
    }

    public VenueDto update(Long id, Map<String, Object> fields){
        Venue venueInDb = venueRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(String.format("Venue with id %s not found", id)));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(venueInDb, key, value);
        });
        return venueMapper.toDto(venueRepository.save(venueInDb));
    }

    public void delete(Long Id){
        Venue venueInDb = venueRepository.findById(Id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Venue with id %s not found", Id)));
        venueInDb.setDeletedAt(LocalDateTime.now());
        venueRepository.save(venueInDb);
    }

}
