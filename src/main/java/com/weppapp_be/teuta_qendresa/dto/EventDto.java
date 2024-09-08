package com.weppapp_be.teuta_qendresa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;
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
