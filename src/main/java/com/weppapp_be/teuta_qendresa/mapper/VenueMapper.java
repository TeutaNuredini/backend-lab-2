package com.weppapp_be.teuta_qendresa.mapper;

import com.weppapp_be.teuta_qendresa.dto.VenueDto;
import com.weppapp_be.teuta_qendresa.dto.request.VenueRequest;
import com.weppapp_be.teuta_qendresa.entity.Venue;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VenueMapper implements GenericMapper<Venue, VenueDto, VenueRequest>{
    private final ModelMapper mapper;

    @Override
    public VenueDto toDto(Venue entity) {
        return mapper.map(entity, VenueDto.class);
    }

    @Override
    public Venue toEntity(VenueRequest request) {
        return mapper.map(request, Venue.class);
    }
}
