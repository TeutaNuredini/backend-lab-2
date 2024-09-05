package com.weppapp_be.teuta_qendresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopSellingEventDto {
    private Long eventId;
    private String eventName;
    private Long reservationCount;
}
