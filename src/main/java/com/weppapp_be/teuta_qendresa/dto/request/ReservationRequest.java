package com.weppapp_be.teuta_qendresa.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private Long eventId;
    private Long userId;
    private String reservationDate;
    private Long numberOfPeople;
    private String email;
}
