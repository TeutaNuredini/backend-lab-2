package com.weppapp_be.teuta_qendresa.mapper;

import com.weppapp_be.teuta_qendresa.dto.LocationDto;
import com.weppapp_be.teuta_qendresa.dto.request.LocationRequest;
import com.weppapp_be.teuta_qendresa.entity.Location;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationMapper implements GenericMapper<Location, LocationDto, LocationRequest>{
    private final ModelMapper mapper;

    @Override
    public LocationDto toDto(Location entity) {
        return mapper.map(entity, LocationDto.class);
    }

    @Override
    public Location toEntity(LocationRequest request) {
        return mapper.map(request, Location.class);
    }
}
