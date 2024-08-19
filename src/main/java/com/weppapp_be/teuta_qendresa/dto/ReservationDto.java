package com.weppapp_be.teuta_qendresa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private Long id;
    private Long eventId;
    private Long userId;
    private String reservationDate;
    private Long numberOfPeople;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
