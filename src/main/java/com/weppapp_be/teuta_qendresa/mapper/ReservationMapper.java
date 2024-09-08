package com.weppapp_be.teuta_qendresa.mapper;

import com.weppapp_be.teuta_qendresa.dto.ReservationDto;
import com.weppapp_be.teuta_qendresa.dto.request.ReservationRequest;
import com.weppapp_be.teuta_qendresa.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper implements GenericMapper<Reservation, ReservationDto, ReservationRequest>{
    private final ModelMapper mapper;

    @Override
    public ReservationDto toDto(Reservation entity) {
        if (entity == null) {
            return null;
        }
        ReservationDto dto = mapper.map(entity, ReservationDto.class);
        dto.setEventId(entity.getEvent() != null ? entity.getEvent().getId() : null);
        return dto;
    }
    @Override
    public Reservation toEntity(ReservationRequest request) {
        return mapper.map(request, Reservation.class);
    }
}
