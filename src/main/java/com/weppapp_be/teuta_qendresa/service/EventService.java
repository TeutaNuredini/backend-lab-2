package com.weppapp_be.teuta_qendresa.service;

import com.weppapp_be.teuta_qendresa.dto.EventDto;
import com.weppapp_be.teuta_qendresa.dto.TopSellingEventDto;
import com.weppapp_be.teuta_qendresa.dto.request.EventRequest;
import com.weppapp_be.teuta_qendresa.entity.Event;
import com.weppapp_be.teuta_qendresa.entity.Location;
import com.weppapp_be.teuta_qendresa.entity.Category;
import com.weppapp_be.teuta_qendresa.exception.ResourceNotFoundException;
import com.weppapp_be.teuta_qendresa.mapper.EventMapper;
import com.weppapp_be.teuta_qendresa.repository.CategoryRepository;
import com.weppapp_be.teuta_qendresa.repository.EventRepository;
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
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final CategoryRepository categoryRepository;
    private final EventMapper eventMapper;
    private final UserService userService;

    public EventDto create(EventRequest request) {

        Location location = venueRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Venue with id %s not found", request.getLocationId())));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %s not found", request.getCategoryId())));

        Event event = eventMapper.toEntity(request);
        event.setCreatedBy(userService.getCurrentUser().getId());
        event.setCreatedAt(LocalDateTime.now());
        event.setLocation(location);
        event.setCategory(category);
        Event eventInDb = eventRepository.save(event);
        return eventMapper.toDto(eventInDb);
    }

    public EventDto getById(Long id) {
        Event eventInDb = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Event with id %s not found", id))
        );
        return eventMapper.toDto(eventInDb);
    }

    public List<EventDto> getAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    public List<EventDto> getAllByLocationId(Long locationId) {
        List<Event> events = eventRepository.findEventByLocationId(locationId);
        return events.stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    public List<EventDto> getAllByCategoryId(Long categoryId) {
        List<Event> events = eventRepository.findEventByCategoryId(categoryId);
        return events.stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    public List<TopSellingEventDto> getTopSellingEvents() {
        return eventRepository.findTopSellingEvents();
    }

    public EventDto update(Long id, Map<String, Object> fields) {
        Event eventInDb = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Event with id %s not found", id)));
        fields.forEach((key, value) -> {
            ReflectionUtil.setFieldValue(eventInDb, key, value);
        });
        return eventMapper.toDto(eventRepository.save(eventInDb));
    }

    public void delete(Long id) {
        Event eventInDb = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Event with id %s not found", id)));
        eventInDb.setDeletedAt(LocalDateTime.now());
        eventRepository.save(eventInDb);
    }
}
