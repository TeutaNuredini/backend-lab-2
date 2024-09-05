package com.weppapp_be.teuta_qendresa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;
    private Long venueId;
    private Long categoryId;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private String duration;
    private LocalDateTime createdAt;
}
