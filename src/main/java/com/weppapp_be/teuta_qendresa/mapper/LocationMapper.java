package com.weppapp_be.teuta_qendresa.mapper;

import com.weppapp_be.teuta_qendresa.dto.LocationDto;
import com.weppapp_be.teuta_qendresa.dto.request.VenueRequest;
import com.weppapp_be.teuta_qendresa.entity.Location;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VenueMapper implements GenericMapper<Location, LocationDto, VenueRequest>{
    private final ModelMapper mapper;

    @Override
    public LocationDto toDto(Location entity) {
        return mapper.map(entity, LocationDto.class);
    }

    @Override
    public Location toEntity(VenueRequest request) {
        return mapper.map(request, Location.class);
    }
}
