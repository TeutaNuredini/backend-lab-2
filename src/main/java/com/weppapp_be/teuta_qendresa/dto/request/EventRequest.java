package com.weppapp_be.teuta_qendresa.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private Long venueId;
    private Long categoryId;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private String duration;
    private String eventType;
}

