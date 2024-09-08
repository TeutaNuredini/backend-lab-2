package com.weppapp_be.teuta_qendresa.mapper;

import com.weppapp_be.teuta_qendresa.dto.EventDto;
import com.weppapp_be.teuta_qendresa.dto.request.EventRequest;
import com.weppapp_be.teuta_qendresa.entity.Event;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper implements GenericMapper<Event, EventDto, EventRequest>{
    private final ModelMapper mapper;

    @Override
    public EventDto toDto(Event entity) {
        if (entity == null) {
            return null;
        }
        EventDto dto = mapper.map(entity, EventDto.class);
        dto.setLocationId(entity.getLocation() != null ? entity.getLocation().getId() : null);
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        return dto;
    }

    @Override
    public Event toEntity(EventRequest request) {
        return mapper.map(request, Event.class);
    }
}
