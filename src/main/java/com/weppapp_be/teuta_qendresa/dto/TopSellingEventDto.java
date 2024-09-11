package com.weppapp_be.teuta_qendresa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopSellingEventDto {
    private Long eventId;
    private String eventName;
    private Long reservationCount;
    private Long locationId;
    private Long categoryId;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private String paragraph;
    private String duration;
    private Long capacity;
    private Double price;
    private String imageUrl;
    private LocalDateTime createdAt;
}
