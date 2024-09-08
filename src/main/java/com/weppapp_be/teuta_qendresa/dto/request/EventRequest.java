package com.weppapp_be.teuta_qendresa.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

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
}

